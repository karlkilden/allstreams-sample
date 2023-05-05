package com.kildeen.sps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class SchemaReadApi {
    private static final ObjectMapper mapper = new ObjectMapper();

    public Map<String, Map<String, Object>> read() {
        Map<String, Map<String, Object>> eventKeysToSchema = new HashMap<>();
        SchemaDb.SINGLETON.schemaTable.forEach((key, value) -> {
            try {
                eventKeysToSchema.put(key, mapper.readValue(value, Map.class));
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        });
        return eventKeysToSchema;
    }
}
