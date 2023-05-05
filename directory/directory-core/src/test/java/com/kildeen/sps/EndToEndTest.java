package com.kildeen.sps;

import com.kildeen.sps.SubscriptionReader.Subscribers.Subscriber;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class EndToEndTest {

    @Test
    void test() {
        Subscriber schemaCreator = new Subscriber("schemaCreator", Map.of());
        EventPublisher ep = new EventPublisher(new SubscriptionReader(schemaCreator));

        serverSendsEventsForTheFirstTime(ep, "id", 2L, "eventKey1", 99L);

        someOtherSystemSubscribes();

        //reload subscribers
        ep = new EventPublisher(new SubscriptionReader(schemaCreator));

        ep.publish(Map.of("id", 2L), "eventKey1");
        ep.publish(Map.of("id-zz", 99L), "eventKey2");
        ep.publish(Map.of("id-zz", 100L), "eventKey2");
        ep.publish(Map.of("id-zz", 101L), "eventKey2");

    }

    private void someOtherSystemSubscribes() {
        SchemaReadApi schemaReadApi = new SchemaReadApi();

        Map<String, Map<String, Object>> allEventsSchema = schemaReadApi.read();

        WriteSubscriptionApi writeSubscriptionApi = new WriteSubscriptionApi();
        Map.Entry<String, Map<String, Object>> firstEvent = allEventsSchema.entrySet().stream().findFirst().get();

        Map<String, String> withChangedKey = new HashMap<>();
        firstEvent.getValue().forEach((key, value) -> withChangedKey.put(key, key + "sub1"));
        writeSubscriptionApi.subscribe("Sub1",firstEvent.getKey(), withChangedKey);
    }
    private void serverSendsEventsForTheFirstTime(EventPublisher ep, String id, long v1, String eventKey1, long v11) {
        ep.publish(Map.of(id, v1), eventKey1);
        ep.publish(Map.of("id-zz", v11), "eventKey2");
    }
}