/*
 * This is part of MMT monitoring suite.
 * All rights reserved. Monitmage (www.montimage.com).
 */

package com.montimage.mmt.client.connector;

/**
 * Interface of a field-value header. An implementing class MUST have the methods to get the header field and header value.
 * @author Bachar Wehbi (bachar.wehbi@montimage.com)
 */
public interface MMTFieldValueHeader {

    /**
     * Returns a String representation of the header filed.
     * @return header field as a String.
     */
    public String getHeaderField();

    /**
     * Returns a String representation of the header value.
     * @return header value as a String.
     */
    public String getHeaderValue();

}
