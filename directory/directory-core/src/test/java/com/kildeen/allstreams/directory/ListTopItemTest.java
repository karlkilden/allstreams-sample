package com.kildeen.allstreams.directory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListTopItemTest {

    ListTopItemMockAccess mockAccess = new ListTopItemMockAccess();
    ListTopItem listTopItem = new ListTopItem(mockAccess, mockAccess);

    @Test
    void fetch() {
        listTopItem.list();
    }
}