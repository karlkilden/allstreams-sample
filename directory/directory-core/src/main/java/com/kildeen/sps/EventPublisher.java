package com.kildeen.sps;

import java.util.Map;

public class EventPublisher {

    EventQueue eventQueue;

    public EventPublisher(SubscriptionReader subscriptionReader) {
        this.subscriptionReader = subscriptionReader;
    }

    SubscriptionReader subscriptionReader;

    void publish(Map<String, Object> eventData, String eventKey) {

        while (!EventQueue.failedEvents.isEmpty()) {
            EventHttpSender peek = EventQueue.failedEvents.peek();
            if (peek != null) {
                System.out.println("Retry for:" + peek);
                int send = peek.send();
                if (send == 200) {
                    EventQueue.failedEvents.pop();
                }
            }
        }

        SubscriptionReader.Subscribers s = subscriptionReader.subs(eventKey);

        EventFork eventFork = new EventFork(eventData, s.subs());

        for (EventFork.ForkedEvents.Fork fork : eventFork.fork()
                .forks()) {

            EventHttpSender eventHttpSender = new EventHttpSender(eventKey, fork);
            int response = eventHttpSender.send();
            if (response != 200) {
                EventQueue.failedEvents.add(eventHttpSender);
            }
        }
    }
}
