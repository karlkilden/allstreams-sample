package com.kildeen.allstreams.directory;

import com.kildeen.allstreams.LongTuple;

record TopItem(long id, String title, int views, Highlights highlights) {
    TopItem(LongTuple<String, Integer> titleAndViews, Highlights highlights) {
        this(titleAndViews.getId(), titleAndViews.getOne(), titleAndViews.getTwo(), highlights);
    }

    public record Highlights(String producer, String rating, String genre) {
    }

    ;
}
