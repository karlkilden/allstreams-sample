package com.kildeen.sps;

import java.util.HashMap;
import java.util.Map;

public enum SchemaDb {

    SINGLETON;

    Map<String, String> schemaTable = new HashMap<>();
    Map<String, String> eventToSubscriberTable = new HashMap<>();
    Map<String, Map<String, String>> schemaMapping = new HashMap<>();

}
