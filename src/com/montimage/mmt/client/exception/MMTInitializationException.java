/*
 * This is part of MMT monitoring suite.
 * All rights reserved. Monitmage (www.montimage.com).
 */
package com.montimage.mmt.client.exception;

/**
 * This exception is thrown by the {@link com.montimage.mmt.client.connector.MMTClientConnector} when an initialization error is encountered. Such error includes badly configured client connector.
 * This exception is also thrown when the reported event structure is not validated.
 * @author Bachar Wehbi (bachar.wehbi@montimage.com)
 */
public class MMTInitializationException extends Exception {

    //TODO: Why not to create another exception for invalidated event structure format.
    private String exce;
    //TODO: why not add error code?

    /**
     * Creates a new instance with no error message.
     */
    public MMTInitializationException() {
        super();
        exce = "Unknown Initialization Exception";
    }

    /**
     * Creates a new instance with the given error message.
     * @param err the reported error message.
     */
    public MMTInitializationException(String err) {
        super(err);
        exce = err;
    }

    /**
     * return the reported error of this exception instance.
     * @return the reported error.
     */
    public String getError() {
        return exce;
    }
}
