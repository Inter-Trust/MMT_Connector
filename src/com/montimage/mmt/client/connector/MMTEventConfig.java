/*
 * This is part of MMT monitoring suite.
 * All rights reserved. Monitmage (www.montimage.com).
 */

package com.montimage.mmt.client.connector;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of event configuration. An event is identified by a name. It is part of a protocol. It is a collection of required and optional fields.
 * @author Bachar Wehbi (bachar.wehbi@montimage.com)
 */
public class MMTEventConfig {
    private String eventName;
    private List<String> requiredHeaderFields;
    private List<String> optionalHeaderFields;

    public MMTEventConfig(String eventName) {
        this.eventName = eventName;
        this.requiredHeaderFields = new ArrayList<String>();
        this.optionalHeaderFields = new ArrayList<String>();
    }

    /**
     * Returns the event name.
     * @return the event name.
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Returns the number of required header for this event.
     * @return the number of required headers.
     */
    public int getRequiredHeaderFieldsNb() {
        return this.requiredHeaderFields.size();
    }

    /**
     * Returns the number of optional headers for this event.
     * @return the number of optional headers.
     */
    public int getOptionalHeaderFieldssNb() {
        return this.optionalHeaderFields.size();
    }

    /**
     * Returns the list of optional headers (identified by the header field) of this event.
     * @return the list of optional header fields
     */
    public List<String> getOptionalHeaderFields() {
        return optionalHeaderFields;
    }

    /**
     * Returns the list of required header fields of this event.
     * @return the list of required header fields.
     */
    public List<String> getRequiredHeaderFields() {
        return requiredHeaderFields;
    }

    /**
     * Adds a required header field to the list of required headers.
     * If the header field already exists in the required header fields list, nothing will be done.
     * If the optional header fields list contains the given header, it will be removed.
     * @param requiredHeader the required header field to add.
     */
    public void addRequiredHeaderField(String requiredHeader) {
        if (!this.requiredHeaderFields.contains(requiredHeader)) {
            this.requiredHeaderFields.add(requiredHeader);
        }

        if(this.optionalHeaderFields.contains(requiredHeader)) {
            this.optionalHeaderFields.remove(requiredHeader);
        }
    }

    /**
     * Adds an optional header field to the list of optional headers.
     * If the header field already exists in the optional header fields list, nothing will be done.
     * @param optionalHeader the optional header field to add.
     */
    public void addOptionalHeaderField(String optionalHeader) {
        if(!this.optionalHeaderFields.contains(optionalHeader)) {
            this.optionalHeaderFields.add(optionalHeader);
        }
    }

    /**
     * Removes the header field from both the required and optional header fields lists if it already exists.
     * @param headerField the header field to remove from this event.
     */
    public void removeHeaderField(String headerField) {
        this.optionalHeaderFields.remove(headerField);
        this.requiredHeaderFields.remove(headerField);
    }

    /**
     * Indicates if the given header field exists in the list of optional header fields.
     * @param headerField the header field to check.
     * @return true if the given header field exists in the list of optional header fields, false otherwise.
     */
    public boolean isOptionalHeaderField(String headerField) {
        for(String field : this.optionalHeaderFields) {
            if(field.equals(headerField))
                return true;
        }
        return false;
    }

    /**
     * Indicates if the given header field exists in the list of required header fields.
     * @param headerField the header field to check.
     * @return true if the given header field exists in the list of required header fields, false otherwise.
     */
    public boolean isRequiredHeaderField(String headerField) {
        for(String field : this.requiredHeaderFields) {
            if(field.equals(headerField))
                return true;
        }
        return false;
    }

    /**
     * Indicates if the given header field exists either in the required or the optional header fields lists.
     * @param headerField the header field to check.
     * @return true if the given header field exists in the optional or required header fields lists, false otherwise.
     */
    public boolean isHeaderField(String headerField) {
        return (this.isOptionalHeaderField(headerField) || this.isRequiredHeaderField(headerField));
    }

}
