package com.kildeen.sps;

import java.util.ArrayDeque;

public enum EventQueue {

    SINGLETON;

    static final ArrayDeque<EventHttpSender> failedEvents = new ArrayDeque<EventHttpSender>();

    record FailedEvent() {}

}
