/*
 * This is part of MMT monitoring suite.
 * All rights reserved. Monitmage (www.montimage.com).
 */
package com.montimage.mmt.client.connector;

import com.montimage.mmt.client.exception.MMTConnectorException;
import com.montimage.mmt.client.exception.MMTInitializationException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.List;
import org.apache.commons.codec.binary.Base64;

/**
 * This is the main access point to MMTClient. It allows to remotely communicate with the MMT-Probe.
 * @author Bachar Wehbi (bachar.wehbi@montimage.com)
 */
public class MMTClientConnector {

    private MMTClientConnectorConfig connectorConfig;
    private MMTProtocolConfig protoConfig;
    private int serviceID;
    private static final String RemoteProcessRequestType = "Process";
    private static final String TimestampHeaderField = "MMT-Timestamp";
    private static final String ServiceIDHeaderField = "Service-ID";
    private static final String EventParameterName = "Event";
    private static final String CRLF = "\r\n";
    private static final String SP = " ";
    private static final String CL = ":";

    /**
     * Default constructor.
     */
    public MMTClientConnector(int serviceID) {
        this.connectorConfig = null;
        this.protoConfig = null;
        this.serviceID = serviceID;
    }

    /**
     * Returns the clients connector configuration. The connector configuration is the place holder for the connection details.
     * @return the clients connector configuration.
     */
    public MMTClientConnectorConfig getConnectorConfig() {
        return connectorConfig;
    }

    /**
     * Sets the clients connector configuration.
     * @param connectorConfig the connector configuration.
     */
    public void setConnectorConfig(MMTClientConnectorConfig connectorConfig) {
        this.connectorConfig = connectorConfig;
    }

    /**
     * Returns the clients protocol configuration.
     * @return the clients protocol configuration. 
     */
    public MMTProtocolConfig getProtoConfig() {
        return protoConfig;
    }

    /**
     * Sets the clients protocol configuration.
     * @param protoConfig the clients protocol configuration.
     */
    public void setProtoConfig(MMTProtocolConfig protoConfig) {
        this.protoConfig = protoConfig;
    }

    /**
     *
     * This method prepares the event to be sent to the server for processing.
     * @param time the timestamp in milliseconds when the event was recorded.
     * @param eventName the event name to process.
     * @param fieldValueElements the event attributes constituted of a list of field-value elements to process.
     * @throws MMTInitializationException if the client connector is badly configured.
     * @throws MMTConnectorException if a connection problem is encountered while connecting to the server.
     * @throws IOException if an IO problem (like network connection problem) is encountered.
     */
    public synchronized void processEvent(long time, String eventName, List<MMTFieldValueHeader> fieldValueElements) throws MMTInitializationException, MMTConnectorException, IOException {
        String eventMessage;
        if (validateEvent(eventName, fieldValueElements)) {
            eventMessage = this.createEventMessage(time, eventName, fieldValueElements);
            sendEvent(Base64.encodeBase64String(eventMessage.getBytes()));
        }
    }

    /**
     * Verifies if the protocol identified by its name is valid. A valid protocol has a corresponding MMT_plugin on the server side.
     * @param protocolName the name of the protocol to be verified.
     * @return true if the protocol has a corresponding plugin on the MMT_server side; false otherwise.
     */
    public boolean isValidProtocol(String protocolName) {
        return true;
    }

    /**
     * Verifies if the protocol identified by its id is valid. A valid protocol has a corresponding plugin on the MMT_server side.
     * @param protocolId the ID of the protocol to be verified.
     * @return true if the protocol has a corresponding plugin on the MMT_server side; false otherwise.
     */
    public boolean isValidProtocol(int protocolId) {
        return true;
    }

    /**
     * This method validates the structure of a field-value event. An evant is valid if it contains all the required headers defined in the protocol config,
     * and if all of its field-value elements exists in the protocol config lists of optional or required header fields.
     * @param eventName the event name.
     * @param fieldValueElements the event content constituted of a list of field-value elements to validate.
     * @throws MMTInitializationException the protocol config is not configured, or, the event does not contain all the required header fields,
     * or, the event contains undefined header fields.
     */
    public void validateEventStructure(String eventName, List<MMTFieldValueHeader> fieldValueElements) throws MMTInitializationException {
        if (this.getProtoConfig() == null) {
            throw new MMTInitializationException("Initialization Exception: getProtoConfig() returns null. A valid MMTRemoteProtocolConfig must be set.");
        } else {
            MMTEventConfig event = this.getProtoConfig().getEventByName(eventName);
            if (event == null) {
                throw new MMTInitializationException("Initialization Exception: Event name \"" + eventName + "\" is not registered. Each Event MUST be registered in order to be used.");
            } else {
                for (MMTFieldValueHeader fvh : fieldValueElements) {
                    if (!event.isHeaderField(fvh.getHeaderField())) {
                        throw new MMTInitializationException("Initialization Exception: Unkown header field \"" + fvh.getHeaderField() + "\" in event \"" + eventName + "\". All header fields MUST be configured.");
                    }
                }

                List<String> requiredHeaderFields = event.getRequiredHeaderFields();
                for (String requiredField : requiredHeaderFields) {
                    boolean fieldExists = false;
                    for (MMTFieldValueHeader fvh : fieldValueElements) {
                        if (requiredField.equals(fvh.getHeaderField())) {
                            fieldExists = true;
                            break;
                        }
                    }
                    if (!fieldExists) {
                        throw new MMTInitializationException("Initialization Exception: required header field \"" + requiredField + "\" is missing in event \"" + eventName + "\". All required headers MUST be present.");
                    }
                }
            }
        }
    }

    private Boolean validateEvent(String eventName, List<MMTFieldValueHeader> fieldValueElements) throws MMTInitializationException {
        Boolean retval = true;
        if (this.getProtoConfig() == null) {
            throw new MMTInitializationException("Initialization Exception: getProtoConfig() returns null. A valid MMTRemoteProtocolConfig must be set.");
        } else if (this.getProtoConfig().getEventValidationLevel() != MMTProtocolConfig.NoEventValidation) {
            MMTEventConfig event = this.getProtoConfig().getEventByName(eventName);
            if (event == null) {
                throw new MMTInitializationException("Initialization Exception: Event name \"" + eventName + "\" is not registered. Each Event MUST be registered in order to be used.");
            } else if (this.getProtoConfig().getEventValidationLevel() == MMTProtocolConfig.CompleteEventValidation) {
                this.validateEventStructure(eventName, fieldValueElements);
            }
        }
        return retval;
    }

    private String createHeaderLine(MMTFieldValueHeader fvh) {
        String retval = fvh.getHeaderField() + CL + SP + fvh.getHeaderValue() + CRLF;
        return retval;
    }

    private String createRequestLine(long time, String eventName) {
        String retval = RemoteProcessRequestType + SP + this.getProtoConfig().getProtocolName() + SP + this.getProtoConfig().getProtocolID() + SP + eventName + CRLF;
        retval = retval + TimestampHeaderField + CL + SP + time + CRLF;
        retval = retval + ServiceIDHeaderField + CL + SP + serviceID + CRLF;
        return retval;
    }

    private String createEventMessage(long time, String eventName, List<MMTFieldValueHeader> fieldValueElements) {
        String retval = createRequestLine(time, eventName);
        for (MMTFieldValueHeader fvh : fieldValueElements) {
            retval = retval + createHeaderLine(fvh);
        }
        retval = retval + CRLF; //We end the Message with an extra CRLF
        return retval;
    }

    private void sendEvent(String eventMessage) throws MMTConnectorException, IOException {
        String postMessage = EventParameterName + "=" + eventMessage;
        HttpURLConnection connection = (HttpURLConnection) this.getConnectorConfig().getServerURL().openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setReadTimeout(15000);

        connection.setRequestProperty("charset", "utf-8");

        if (this.getConnectorConfig().isKeepAlive()) {
            connection.setRequestProperty("connection", "keep-alive");
        } else {
            connection.setRequestProperty("connection", "close");
        }

        connection.setRequestProperty("Content-Length", "" + Integer.toString(postMessage.getBytes().length));
        connection.setUseCaches(false);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(postMessage);
        wr.flush();
        wr.close();

        //connection.connect();
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            String responseMessage = new String();
            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String responseLine;
            while ((responseLine = responseBuffer.readLine()) != null) {
                responseMessage = responseMessage.concat(responseLine + "\n");
            }
            //System.out.println("The response of the probe is:" + responseMessage);
            responseBuffer.close();
            connection.disconnect();
        } else {
            connection.disconnect();
            throw new MMTConnectorException("Connector Exception: Connection returned a response code of \"" + connection.getResponseCode() + "\".", connection.getResponseCode());
        }
    }
    
}
