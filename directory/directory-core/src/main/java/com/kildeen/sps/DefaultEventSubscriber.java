package com.kildeen.sps;

public class DefaultEventSubscriber implements EventSubscriber{

    private boolean fail =true ;

    public DefaultEventSubscriber() {
    }

    @Override
    public int receive(String subId, String eventKey, String json) {
        if (json.contains("100") && fail) {
            fail = false;
            throw new RuntimeException("Not 100!");
        }
        System.out.print(subId + " ");
        System.out.print(eventKey + " ");
        System.out.println(json);
        return 200;
    }
}
