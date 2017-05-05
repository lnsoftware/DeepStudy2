package com.zhiyin.event.route;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class EventRouteConfigUtil {
	public static Properties prop = new Properties();
	private static Map<String, HashSet<String>> setMap = new HashMap<String, HashSet<String>>();

	static {
		try {
			InputStream input = EventRouteConfigUtil.class
					.getResourceAsStream("/event_queue_config.properties");
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		Iterator<Entry<Object, Object>> it = prop.entrySet().iterator();

		while (it.hasNext()) {
			Entry<Object, Object> entry = it.next();
			String key = (String) entry.getKey();
			
			String value = (String) entry.getValue();
			System.out.println(key+value);
			String[] codes = value.split(",");
			HashSet<String> list = new HashSet<String>();
			// list.containsAll(Arrays.asList(val));
			for (String tmp : codes) {
				list.add(tmp.trim());
			}

			setMap.put(key.trim(), list);
		}
	}

	public static boolean contain(String key,String typeCode){
		
		 HashSet<String> set = setMap.get(key.trim());
		 boolean c  = false;
		if( set !=null){
			c = set.contains(typeCode.trim());
		}
		return c;
	}

	public static void main(String[] args) {
	boolean re = EventRouteConfigUtil.contain("ElasticSearchIndexUpdate", "0001_0001");
		System.out.println(re);
	}
}
