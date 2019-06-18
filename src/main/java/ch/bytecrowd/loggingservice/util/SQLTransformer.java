package ch.bytecrowd.loggingservice.util;

import java.util.Map;

import com.google.gson.internal.LinkedTreeMap;

public final class SQLTransformer {

	public static String jsonToMySqlInsert(LinkedTreeMap<String, Map<String, Object>> json) {
		String tableName = (String) json.get("logger").get("tableName");
		if (tableName != null) {
			StringBuilder keys = new StringBuilder();
			StringBuilder values = new StringBuilder();
			for (Map.Entry<String, Object> entry : json.get("results").entrySet()) {
				if (entry.getValue() != null) {
					keys.append(entry.getKey()).append(",");
					values.append("'").append(entry.getValue().toString().replace("'", "''")).append("',");
				}
			}
			if (keys.length() >= 2 && values.length() >= 2) {
				keys.replace(keys.length() - 1, keys.length(), "");
				values.replace(values.length() - 1, values.length(), "");
				return new StringBuffer().append("INSERT INTO ").append(tableName).append(" (").append(keys.toString())
						.append(") VALUES (").append(values.toString()).append(");").toString();
			}
			throw new IllegalArgumentException("Parameter enthält keine Columns bzw. Values");
		} else {
			throw new IllegalArgumentException("Parameter tableName ist nicht definiert");
		}
	}
}
