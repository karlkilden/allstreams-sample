package com.kildeen.allstreams.directory;

import com.kildeen.allstreams.*;
import java.util.*;

class ListTopTitle {

    private final SearchTopTitles searchTopTitles;

    ListTopTitle(SearchTopTitles searchTopTitles) {
        this.searchTopTitles = searchTopTitles;
    }

    List<TopTitle> list() {
        List<LongTuple<String, Integer>> result = searchTopTitles.search();
        return result.stream().map(TopTitle::new).toList();


    }
}
