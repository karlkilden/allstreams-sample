package com.kildeen.sps;

public interface EventSubscriber {

    public int receive(String subId, String eventKey, String json);
    }
