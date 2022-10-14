package com.kildeen.allstreams.directory;

import com.kildeen.allstreams.*;
import java.util.*;

class ListTopTitle {

    private final SearchTopTitles searchTopTitles;
    private final FetchTopTitles fetchTopTitles;

    ListTopTitle(SearchTopTitles searchTopTitles, FetchTopTitles fetchTopTitles) {
        this.searchTopTitles = searchTopTitles;
        this.fetchTopTitles = fetchTopTitles;
    }

    List<TopTitle> list() {
        List<LongTuple<String, Integer>> titleAndViewByIds = searchTopTitles.search();
        return titleAndViewByIds.stream().map(TopTitle::new).toList();
    }
}
