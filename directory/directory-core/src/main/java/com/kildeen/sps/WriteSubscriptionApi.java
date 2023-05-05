package com.kildeen.sps;

import java.util.Map;

public class WriteSubscriptionApi {

    public int subscribe(String subId, String eventId, Map<String, String> schemaMapping) {
        SchemaDb.SINGLETON.eventToSubscriberTable.put(subId, eventId);
        SchemaDb.SINGLETON.schemaMapping.put(subId, schemaMapping);
        return 200;
    }
}
