package cs.pub.web.content.extractor.utils;

import com.google.gson.Gson;

public class JsonUtils {
	private static Gson gson = new Gson();
	
	public static String toJson(Object value){
		return gson.toJson(value);
	}
}
