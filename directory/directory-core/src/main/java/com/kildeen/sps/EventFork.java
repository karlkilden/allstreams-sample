package com.kildeen.sps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventFork {


    private final Map<String, Object> eventData;
    private final List<SubscriptionReader.Subscribers.Subscriber> subs;

    EventFork(Map<String, Object> eventData,
              List<SubscriptionReader.Subscribers.Subscriber> subs) {
        this.eventData = eventData;
        this.subs = subs;
    }

    ForkedEvents fork() {
        return new ForkedEvents(subs.stream()
                .map(subscriber -> {
                    if (subscriber.schema().isEmpty()) {
                        return new ForkedEvents.Fork(subscriber, eventData);
                    }
                    Map<String, Object> fork = new HashMap<>();
                    subscriber.schema().
                            keySet()
                            .forEach(key -> fork.put(subscriber.schema().get(key), eventData.get(key)));
                    return new ForkedEvents.Fork(subscriber, fork);
                }).toList());
    }

    record ForkedEvents(List<Fork> forks) {
        record Fork(
                SubscriptionReader.Subscribers.Subscriber subscriber,
                Map<String, Object> forkedEvent) {
        }
    }


}
