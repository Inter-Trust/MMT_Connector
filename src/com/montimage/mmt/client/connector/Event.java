/*
 * This is part of MMT monitoring suite.
 * All rights reserved. Monitmage (www.montimage.com).
 */
package com.montimage.mmt.client.connector;

import java.util.List;

/**
 * This class represents the event attributes list. Three attributes are always
 * present: the event name, the event timestamp and the service (or connector)
 * identifier.
 *
 * @author Wissam Mallouli
 */
public class Event {

    private String eventName;
    private long timestamp;
    private int serviceId;
    private List<MMTFieldValueHeader> attributes;

    /**
     * Default constructor.
     */
    public Event() {
    }

    /**
     * Returns the event name.
     *
     * @return the event name.
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Sets the event name.
     *
     * @param eventName the event name.
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Returns the event attributes list in a field/value format.
     *
     * @return the event attributes list.
     */
    public List<MMTFieldValueHeader> getAttributes() {
        return attributes;
    }

    /**
     * Sets the event attributes list.
     *
     * @param attributes the event attributes list.
     */
    public void setAttributes(List<MMTFieldValueHeader> attributes) {
        this.attributes = attributes;
    }

    /**
     * Returns the event timestamp.
     *
     * @return the event timestamp.
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the event timestamp.
     *
     * @param eventTimestamp the event timestamp.
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Returns the service (connector) identifier that generates the event.
     *
     * @return the event timestamp.
     */
    public int getServiceId() {
        return serviceId;
    }

    /**
     * Sets the event service identifier.
     *
     * @param serviceId the service identifier.
     */
    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
}
