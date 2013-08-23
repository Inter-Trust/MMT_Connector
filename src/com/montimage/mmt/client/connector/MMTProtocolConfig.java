/*
 * This is part of MMT monitoring suite.
 * All rights reserved. Monitmage (www.montimage.com).
 */
package com.montimage.mmt.client.connector;

import java.util.HashMap;

/**
 * Implementation of a protocol configuration. A protocol has an identifier, a name, and a number of events ({@link com.montimage.mmt.client.connector.MMTEventConfig}).
 * @author Bachar Wehbi (bachar.wehbi@montimage.com)
 */
public class MMTProtocolConfig {

    /**
     * Identifier of the no event validation option. 
     */
    public static final int NoEventValidation = 0;
    /**
     * Identifier of partial validation option. Only events will be validated. With this option, a protocol cannot process an event that is not registered.
     */
    public static final int PartialEventValidation = 1;
    /**
     * Identifier of the complete validation option. Events and events attributes will be validated. 
     */
    public static final int CompleteEventValidation = 2;
    private final int protocolID;
    private final String protocolName;
    private int eventValidationLevel;
    private HashMap<String, MMTEventConfig> registeredEvents;

    /**
     * Default constructor.
     * @param protocolID the identifier of the protocol.
     * @param protocolName the name of the protocol.
     */
    public MMTProtocolConfig(int protocolID, String protocolName) {
        this.protocolID = protocolID;
        this.protocolName = protocolName;
        this.eventValidationLevel = CompleteEventValidation;
        this.registeredEvents = new HashMap<String, MMTEventConfig>();
    }

    /**
     * Returns the identifier of this protocol.
     * @return the identifier of this protocol.
     */
    public int getProtocolID() {
        return protocolID;
    }

    /**
     * Returns the protocol name.
     * @return the protocol name.
     */
    public String getProtocolName() {
        return protocolName;
    }

    /**
     * Returns the level of event structure validation. It can be set to disabled, to medium validation or complete validation.
     * @return the level of event structure validation. It can be set to disabled, to medium validation or complete validation.
     */
    public int getEventValidationLevel() {
        return eventValidationLevel;
    }

    /**
     * Registers an event with the protocol. A protocol has a number of events. For a given protocol, the event name is unique.
     * @param event the event to register.
     * @return true if the event is successfully registered; false otherwise. This method fails only if the protocol already has a
     * registered event with the same name. 
     */
    public boolean registerEvent(MMTEventConfig event) {
        if (!this.registeredEvents.containsKey(event.getEventName())) {
            this.registeredEvents.put(event.getEventName(), event);
            return true;
        }
        return false;
    }

    /**
     * Indicates if an event with the given name is registered.
     * @param eventName the name of the event to check if registered.
     * @return true if an event with the given name is already registered; false otherwise.
     */
    public Boolean isRegisteredEventName(String eventName) {
        return this.registeredEvents.containsKey(eventName);
    }

    /**
     * Unregisters the event with the given name if it is already registered. If there is no such event, nothing is done.
     * @param eventName the name of the event to unregister.
     */
    public void unregisterEventByEventName(String eventName) {
        if (this.isRegisteredEventName(eventName)) {
            this.registeredEvents.remove(eventName);
        }
    }

    /**
     * Sets the event validation level to the given value.
     * Accepted values are {@linkplain #NoEventValidation}, {@linkplain #PartialEventValidation} and {@linkplain #CompleteEventValidation}.
     * <p>If any other value is given, the validation level will be set to {@linkplain #NoEventValidation}.
     * @param eventValidationLevel the event validation level value to be set.
     */
    public void setEventValidationLevel(int eventValidationLevel) {
        switch (eventValidationLevel) {
            case PartialEventValidation:
            case CompleteEventValidation:
                this.eventValidationLevel = eventValidationLevel;
                break;
            default:
                this.eventValidationLevel = NoEventValidation;
        }
    }

    /**
     * Returns the event config with the given name. If no such event is registered, null is returned.
     * @param eventName the name of the event.
     * @return the event with the given name if registered, null otherwise.
     */
    public MMTEventConfig getEventByName(String eventName) {
        MMTEventConfig retval = null;
        if (this.isRegisteredEventName(eventName)) {
            return this.registeredEvents.get(eventName);
        }
        return retval;
    }
}
