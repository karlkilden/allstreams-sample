package com.kildeen.sps;

public class SchemaSubscriber implements EventSubscriber {

    private final SubscriptionPersistentWriter persistentWriter;

    public SchemaSubscriber(SubscriptionPersistentWriter persistentWriter) {
        this.persistentWriter = persistentWriter;
    }

    @Override
    public int receive(String subId, String eventKey, String json) {
        persistentWriter.insert(eventKey, json);
        System.out.println("Inserting new schema:"+ eventKey);
        return 200;
    }
}
