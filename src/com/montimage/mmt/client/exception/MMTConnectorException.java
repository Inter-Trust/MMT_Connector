/*
 * This is part of MMT monitoring suite.
 * All rights reserved. Monitmage (www.montimage.com).
 */
package com.montimage.mmt.client.exception;

/**
 * This exception is thrown by the {@link com.montimage.mmt.client.connector.MMTClientConnector} when a network connection error is encountered.
 * @author Bachar Wehbi (bachar.wehbi@montimage.com)
 */
public class MMTConnectorException extends Exception {

    //TODO: Why not to create another exception for invalidated event structure format.
    private String exce;
    private int errCode;

    /**
     * Creates a new instance with no error message.
     */
    public MMTConnectorException() {
        super();
        exce = "Unknown Initialization Exception";
    }

    /**
     * Creates a new instance with the given error message and error code.
     * @param err the reported error message.
     * @param code the reported error code.
     */
    public MMTConnectorException(String err, int code) {
        super(err);
        exce = err;
        errCode = code;
    }

    /**
     * Return the reported error of this exception instance.
     * @return the reported error.
     */
    public String getError() {
        return exce;
    }

    /**
     * Returns the reported error code of this exception instance.
     * @return the reported error code.
     */
    public int getErrorCode() {
        return errCode;
    }


}
