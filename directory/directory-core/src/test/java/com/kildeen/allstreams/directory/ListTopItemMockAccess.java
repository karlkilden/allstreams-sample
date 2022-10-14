package com.kildeen.allstreams.directory;

import com.kildeen.allstreams.LongTuple;
import com.kildeen.allstreams.LongTuples;

import java.util.List;

public class ListTopItemMockAccess implements SearchTopItems, FetchTopItems {


    @Override
    public LongTuples<Long, TopItemTuple> fetch(List<Long> itemIds) {
        return new LongTuples<>(itemIds.stream().map(this::createTuple).toList());
    }

    private LongTuple<Long, TopItemTuple> createTuple(Long id) {
        return LongTuple.of(id, id, new TopItemTuple("producer" + id,
                "rating" + id,
                "genre" + id));
    }

    @Override
    public LongTuples<String, Integer> search() {
        return new LongTuples<>(List.of(99L, 100L, 1001L).stream()
                .map(id -> LongTuple.of(id, "title" + id, id.intValue() + 200)).toList());
    }
}
