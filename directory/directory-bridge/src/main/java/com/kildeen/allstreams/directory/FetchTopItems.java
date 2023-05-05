package com.kildeen.allstreams.directory;

import com.kildeen.allstreams.LongTuples;

import java.util.List;

public interface FetchTopItems {

    LongTuples<Long, TopItemTuple> fetch(List<Long> itemIds);
}
