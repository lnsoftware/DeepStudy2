package com.zhiyin.jagent.scan;

import com.zhiyin.jagent.transformer.handler.ClassInstrumentationHandler;
import com.zhiyin.jagent.transformer.handler.SubTypeInstrumentationHandler;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ScanHandler {

	private static final Map<String, Class<? extends ClassInstrumentationHandler>> agents = new HashMap<String, Class<? extends ClassInstrumentationHandler>>();

	static {
		Reflections reflections = new Reflections(
				"com.zhiyin");

		Set<Class<? extends ClassInstrumentationHandler>> subTypes = reflections
				.getSubTypesOf(ClassInstrumentationHandler.class);

		for (Class<? extends ClassInstrumentationHandler> subType : subTypes) {

			if( subType.getName().equals(SubTypeInstrumentationHandler.class.getName())){
				continue;
			}

			agents.put(subType.getSimpleName().toLowerCase(),subType);
		}
	}

	public static void printHandler(){

		for( String tmp : agents.keySet()){
			System.out.println(agents.get(tmp).getName());
		}
	}
	public static ClassInstrumentationHandler getHandler(String agentName) {
		Class<? extends ClassInstrumentationHandler> agentCreatorClass = agents.get(agentName
				.toLowerCase());

		if (agentCreatorClass == null) {
			return null;
		}

		try {
			return agentCreatorClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
	}

	public static void main(String[] args) {

		printHandler();
	}
}
