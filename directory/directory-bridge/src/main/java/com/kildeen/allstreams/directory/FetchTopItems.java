package com.kildeen.allstreams.directory;

import com.kildeen.allstreams.LongTuples;

import java.util.List;

public interface FetchTopTitles {

    LongTuples<Long, HighlightsTuple> fetch(List<Long> itemIds);
}
