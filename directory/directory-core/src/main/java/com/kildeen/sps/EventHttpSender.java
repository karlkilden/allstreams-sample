package com.kildeen.sps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class EventHttpSender {
    private static final ObjectMapper mapper = new ObjectMapper();
    private final String eventKey;
    private final EventFork.ForkedEvents.Fork fork;

    private final Map<String, EventSubscriber> subKeyToEventSubscribers = new HashMap<>();

    public EventHttpSender(String eventKey, EventFork.ForkedEvents.Fork fork) {
        this.eventKey = eventKey;
        this.fork = fork;

        subKeyToEventSubscribers.put("Sub1", new DefaultEventSubscriber());
        subKeyToEventSubscribers.put("Sub2", new DefaultEventSubscriber());
        subKeyToEventSubscribers.put("schemaCreator", new SchemaSubscriber(new SubscriptionPersistentWriter()));
    }

    int send() {
        try {
            String json = mapper.writeValueAsString(fork.forkedEvent());
            return subKeyToEventSubscribers.get(fork.subscriber().subId()).receive(fork.subscriber().subId(), eventKey, json);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        catch (Exception e) {
            return 400;
        }
    }

    @Override
    public String toString() {
        return "EventHttpSender{" +
                "eventKey='" + eventKey + '\'' +
                ", fork=" + fork +
                '}';
    }
}
