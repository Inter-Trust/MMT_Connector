/*
 * This is part of MMT monitoring suite.
 * All rights reserved. Monitmage (www.montimage.com).
 */
package com.montimage.mmt.client.connector;

/**
 * This class represents the MMT event. It specifies the protocol identifier and
 * name and the content of the event itself.
 *
 * @author Wissam Mallouli
 */
public class MMTEvent {

    private String protoName;
    private int protoId;
    private Event event;

    /**
     * Default constructor.
     */
    public MMTEvent() {
    }

    /**
     * Returns the protocol name.
     *
     * @return the protocol name.
     */
    public String getProtoName() {
        return protoName;
    }

    /**
     * Sets the protocol name.
     *
     * @param protoName the protocol name.
     */
    public void setProtoName(String protoName) {
        this.protoName = protoName;
    }

    /**
     * Returns the protocol identifier.
     *
     * @return the protocol identifier.
     */
    public int getProtoId() {
        return protoId;
    }

    /**
     * Sets the protocol identifier.
     *
     * @param protoId the protocol identifier.
     */
    public void setProtoId(int protoId) {
        this.protoId = protoId;
    }

    /**
     * Returns the event object.
     *
     * @return the event object.
     */
    public Event getEvent() {
        return event;
    }

    /**
     * Sets the event object.
     *
     * @param event the event object.
     */
    public void setEvent(Event event) {
        this.event = event;
    }
}
