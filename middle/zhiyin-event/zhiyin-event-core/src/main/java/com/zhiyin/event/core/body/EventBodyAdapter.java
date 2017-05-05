package com.zhiyin.event.core.body;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class EventBodyAdapter implements JsonSerializer<BasicEventBody>, JsonDeserializer<BasicEventBody> {
	private static final String CLASSNAME = "className";
	private static final String INSTANCE  = "instance";
	
	public BasicEventBody deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

		JsonObject jsonObj = json.getAsJsonObject();
		JsonPrimitive prim = (JsonPrimitive)jsonObj.get(CLASSNAME);
		String className = prim.getAsString();
		
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new JsonParseException(e.getMessage());
		}
		return context.deserialize(jsonObj.get(INSTANCE), clazz);
	}

	public JsonElement serialize(BasicEventBody src, Type typeOfSrc, JsonSerializationContext context) {
		
		JsonObject ret = new JsonObject();
		String classname = src.getClass().getCanonicalName();
		ret.addProperty(CLASSNAME, classname);
		JsonElement el = context.serialize(src);
		ret.add(INSTANCE, el);
		
		return ret;
	}
}
