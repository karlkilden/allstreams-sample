package com.kildeen.allstreams.directory;

import com.kildeen.allstreams.LongTuple;
import com.kildeen.allstreams.LongTuples;

import java.util.List;

class ListTopItem {

    private final SearchTopItems searchTopItems;
    private final FetchTopItems fetchTopTitles;

    ListTopItem(SearchTopItems searchTopItems, FetchTopItems fetchTopTitles) {
        this.searchTopItems = searchTopItems;
        this.fetchTopTitles = fetchTopTitles;
    }

    List<TopTitleDTO> list() {
        LongTuples<String, Integer> titleAndViewByIds = searchTopItems.search();
        LongTuples<Long, TopItemTuple> fetched = fetchTopTitles.fetch(titleAndViewByIds.ids());

        List<TopItem> topItems = titleAndViewByIds.stream().map(titleAndView -> {
            LongTuple<Long, TopItemTuple> tuple = fetched.byId(titleAndView.getId());
            TopItem.Highlights highlights = new TopItem.Highlights(tuple.getTwo().producer(),
                    tuple.getTwo().rating(),
                    tuple.getTwo().genre());
            return new TopItem(titleAndView, highlights);
        }).toList();
        return topItems.stream()
                .map(this::map)
                .toList();
    }

    private TopTitleDTO map(TopItem topItem) {
        return new TopTitleDTO(topItem.id(), topItem.title(), topItem.views());
    }
}
