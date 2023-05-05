package com.kildeen.sps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriptionReader {

    private final Subscribers schemaCreator;
    Map<String, List<Subscribers.Subscriber>> eventKeyToSubscriber = new HashMap<>();

    public SubscriptionReader(Subscribers.Subscriber schemaCreator) {
        this.schemaCreator = new Subscribers(List.of(schemaCreator));

        for (Map.Entry<String, String> subIdToEventKey : SchemaDb.SINGLETON.eventToSubscriberTable.entrySet()) {
            Subscribers.Subscriber subscriber = new Subscribers.Subscriber(subIdToEventKey.getKey(),
                    SchemaDb.SINGLETON.schemaMapping.get(subIdToEventKey.getKey()));
            eventKeyToSubscriber.computeIfAbsent(subIdToEventKey.getValue(), k -> new ArrayList<>()).add(subscriber);
        }
    }

    public Subscribers subs(String eventKey) {
        List<Subscribers.Subscriber> subscribers = eventKeyToSubscriber.get(eventKey);
        if (subscribers == null) {
            return schemaCreator;
        }
        return new Subscribers(subscribers);
    }

    record Subscribers(List<Subscriber> subs) {
        record Subscriber(String subId, Map<String, String> schema) {
        }
    }
}
