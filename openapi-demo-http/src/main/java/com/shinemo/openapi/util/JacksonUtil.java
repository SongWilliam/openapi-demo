package main.java.com.shinemo.openapi.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class JacksonUtil {

	private static ObjectMapper mapper = new ObjectMapper();

	private static ObjectMapper mapperWithoutRemoveNull = new ObjectMapper();

	static {
		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
	}

	/**
	 * Object to string,不保留为null或者空的字段
	 *
	 * @param obj
	 * @return
	 */
	public static String convertFrom(Object obj) throws Exception {
		if (obj == null) {
			return "";
		}

		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			// LOG.warn("Failed to parse object to JSON. ", e);
			throw e;
		}
	}

	/**
	 * 保留为null活着空的字段
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String convertWithNull(Object obj) throws Exception {
		if (obj == null) {
			return "";
		}

		try {
			return mapperWithoutRemoveNull.writeValueAsString(obj);
		} catch (Exception e) {
			// LOG.warn("Failed to parse object to JSON. ", e);
			throw e;
		}
	}

	/**
	 * @param jsonStr
	 * @return
	 */
	public static <T> T convertFrom(String jsonStr, Class<T> clazz)
			throws Exception {
		if (jsonStr == null || jsonStr.isEmpty()) {
			jsonStr = "{}"; // to avoid JSON error
		}

		try {
			return mapper.readValue(jsonStr, clazz);
		} catch (Exception e) {
			// LOG.warn("Failed to parse JSON to object", e);
			throw e;
		}
	}

	public static String safeConvertFrom(Object obj) {
		try {
			return convertFrom(obj);
		} catch (Exception ex) {
		}
		return null;
	}
}
