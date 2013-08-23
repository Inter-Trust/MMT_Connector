/*
 * This is part of MMT monitoring suite.
 * All rights reserved. Monitmage (www.montimage.com).
 */

package com.montimage.mmt.client.connector;

/**
 * Generic implementation of {@link com.montimage.mmt.client.connector.MMTFieldValueHeader}. The header field and value are Strings.
 * @author Bachar Wehbi (bachar.wehbi@montimage.com)
 */
public class GenericFieldValueHeader implements MMTFieldValueHeader{

    private final String headerField;
    private final String headerValue;

    /**
     *
     * @param hField the header field
     * @param hValue the header value
     */
    public GenericFieldValueHeader(String hField, String hValue) {
        this.headerField = hField;
        this.headerValue = hValue;
    }

    /**
     * Returns the header field.
     * @return the header field.
     */
    @Override
    public String getHeaderField() {
        return this.headerField;
    }

    /**
     * Returns the header value.
     * @return the header value.
     */
    @Override
    public String getHeaderValue() {
        return this.headerValue;
    }
}
