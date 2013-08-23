/*
 * This is part of MMT monitoring suite.
 * All rights reserved. Monitmage (www.montimage.com).
 */
package com.montimage.mmt.client.connector;

import com.montimage.mmt.client.exception.MMTInitializationException;
import java.net.URL;

/**
 * Implementation of the client connector configuration. The connector configuration does not create or maintain a socket, rather,
 * it contains the required configuration to create the connection socket.
 * @author Bachar Wehbi (bachar.wehbi@montimage.com)
 */
public class MMTClientConnectorConfig {

    /**
     * The authentication mode is no authentication.
     */
    public static final int NoAuthentication = 0;

    /**
     * The authentication mode is "basic".
     */
    public static final int BasicAuthentication = 1;
    private String userName;
    private String userPassword;
    private URL serverURL;
    private int authMode;
    private boolean keepAlive;

    /**
     * Default constructor. The authentication mode is set to No Authentication.
     */
    public MMTClientConnectorConfig() {
        this.userName = null;
        this.userPassword = null;
        this.serverURL = null;
        this.authMode = NoAuthentication;
        this.keepAlive = false;
    }

    /**
     * Returns the authentication mode.
     * @return the authentication mode.
     */
    public int getAuthMode() {
        return authMode;
    }

    /**
     * Sets the authentication mode.
     * @param authMode the authentication mode.
     */
    public void setAuthMode(int authMode) throws MMTInitializationException {
        switch (authMode) {
            case NoAuthentication:
            case BasicAuthentication:
                this.authMode = authMode;
                break;
            default:
                throw new MMTInitializationException("Initialization Exception: Unaccepted authentication mode \"" + authMode + "\".");
        }

    }

    /**
     * Returns the configured server URL.
     * @return the configured server URL.
     */
    public URL getServerURL() {
        return serverURL;
    }

    /**
     * Sets the server URL.
     * @param serverURL The server URL.
     */
    public void setServerURL(URL serverURL) {
        this.serverURL = serverURL;
    }

    /**
     * Returns the configured user name. The configured user name and password will be used if the authentication mode is not {@linkplain #NoAuthentication}.
     * @return the configured user name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user name. The configured user name and password will be used if the authentication mode is not {@linkplain #NoAuthentication}.
     * @param userName the user name to be used.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns the configured user password. The configured user name and password will be used if the authentication mode is not {@linkplain #NoAuthentication}.
     * @return the configured user password.
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Sets the user password. The configured user name and password will be used if the authentication mode is not {@linkplain #NoAuthentication}.
     * @param userPassword the user password to be used.
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Indicates if the connection to the server will be persistent or not (Note that a persistent connection is possible only if the server supports this option).
     * If true is returned then, the connection to the server will be set with the following header: connection: keep-alive
     * If false is returned then, the connection to the server will be set with the following header: connection: close
     * @return true if the client is asking to maintain persistent connection to the server; false otherwise.
     */
    public boolean isKeepAlive() {
        return keepAlive;
    }

    /**
     * Sets the keep alive property of the client connector.
     * @param keepAlive sets or unsets keep alive property.
     */
    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }
}
