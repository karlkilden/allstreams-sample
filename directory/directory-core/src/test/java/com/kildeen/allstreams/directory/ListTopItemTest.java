package com.kildeen.allstreams.directory;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ListTopItemTest {

    ListTopItemMockAccess mockAccess = new ListTopItemMockAccess();
    ListTopItem listTopItem = new ListTopItem(mockAccess, mockAccess);

    FactSheet facts = new FactSheet();

    @Test
    void hits_are_found() {
        assertThat(listTopItem.list()).isNotEmpty();
    }

    @Test
    void topItem_is_first() {
        assertThat(listTopItem.list().get(0).id()).isEqualTo(facts.topItem());
    }
}