package com.kildeen.sps;

public class SubscriptionPersistentWriter {

    public void insert(String eventKey, String json) {
        SchemaDb.SINGLETON.schemaTable.put(eventKey, json);
    }
}
