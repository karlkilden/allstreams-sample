package com.kildeen.allstreams;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.assertj.core.api.Assertions.*;

class ChallengeTest {

    @Test
    void findsRoute() {
       assertThat(Challenge.findRoutes(new ArrayList<>(List.of(get("USA", "BRA"), get("JPN", "PHL"),
               get("BRA", "UAE"), get("UAE", "JPN"))))).
               isEqualTo("USA, BRA, UAE, JPN, PHL");
    }
    @Test
    void findsRoute_when_more_than_two_locations() {
        assertThat(Challenge.findRoutes(new ArrayList<>(List.of(get("USA", "GBG", "BRA"), get("JPN", "PHL"), get("BRA", "UAE"), get("UAE", "JPN"))))).
                isEqualTo("USA, GBG, BRA, UAE, JPN, PHL");
    }

    @Test
    void finds_single() {
        assertThat(Challenge.findRoutes(new ArrayList<>(List.of(get("USA", "GBG"))))).
                isEqualTo("USA, GBG");
    }

    private ArrayList<String> get(String... locations) {
        return new ArrayList<>(List.of(locations));
    }

}