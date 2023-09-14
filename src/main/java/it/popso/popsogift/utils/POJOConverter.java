package it.popso.popsogift.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.HashMap;
import java.util.Map;

public class POJOConverter {
	private static final ObjectMapper obj;
	private static Map<Class<?>, ObjectReader> cacheReader;
	private static ObjectWriter cacheWriter;

	private POJOConverter() {
	}

	public static <T> T convert(final String json, final Class<T> type) throws JsonMappingException, JsonProcessingException {
		if (!cacheReader.containsKey(type)) {
			cacheReader.put(type, obj.readerFor(type));
		}

		return ((ObjectReader)cacheReader.get(type)).readValue(json);
	}

	public static <T> T convertValue(Object fromValue, TypeReference<T> toValueType) {
		return obj.convertValue(fromValue, toValueType);
	}

	public static String writeValueAsString(Object json) throws JsonMappingException, JsonProcessingException {
		return cacheWriter.writeValueAsString(json);
	}

	static {
		obj = (new ObjectMapper()).disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY).setSerializationInclusion(Include.NON_NULL);
		cacheReader = new HashMap();
		cacheWriter = obj.writer();
	}
}
