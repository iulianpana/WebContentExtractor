package cs.pub.web.content.extractor.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {
	private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	
	public static String toJson(Object value){
		return gson.toJson(value);
	}
}
