package com.kildeen.allstreams.directory;

import com.kildeen.allstreams.LongTuples;

public interface SearchTopItems {
    LongTuples<String, Integer> search();
}
