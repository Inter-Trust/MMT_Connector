/*
 * This is part of MMT monitoring suite.
 * All rights reserved. Monitmage (www.montimage.com).
 */

package com.montimage.mmt.client.connector;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

/**
 * This class implements the Json serialiser and allows to override the google serialization method.
 * @author Wissam Mallouli
 */
public class FieldValueAdapter implements JsonSerializer<GenericFieldValueHeader>{
    
    /**
     * This method override the serialisation method of a GenericFieldValueHeader object.
     * 
     * @param t the GenericFieldValueHeader object
     * @param type the Type
     * @param jsc the Json serialization context
     * @return the Json Element
     */
    
    @Override
    public JsonElement serialize(GenericFieldValueHeader t, Type type, JsonSerializationContext jsc) {

        JsonObject obj = new JsonObject();
        obj.addProperty(t.getHeaderField(), t.getHeaderValue());
        return obj;
    }    
    
}
