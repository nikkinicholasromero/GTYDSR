package com.gty.dsr.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public final class JSONUtility {
	private JSONUtility() {
	}
	
	public static String parseJSON(Object object) throws JsonProcessingException {
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = objectWriter.writeValueAsString(object);
		return json;
	}
}
