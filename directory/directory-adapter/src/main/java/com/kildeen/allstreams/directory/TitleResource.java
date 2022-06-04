package com.kildeen.allstreams.directory;

import java.util.*;
public class TitleResource {

    private ListTopTitle listTopTitle;
    private TopTitleMapper topTitleMapper;

    public TitleResource() {
        this.listTopTitle = new ListTopTitle(new SolrSearchTopTitles());
        this.topTitleMapper = new TopTitleMapper();
    }

    public List<TopTitleDTO> topTitles() {
        return listTopTitle.list().stream().map(topTitleMapper::map).toList();
    }
}
