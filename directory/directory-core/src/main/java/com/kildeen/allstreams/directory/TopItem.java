package com.kildeen.allstreams.directory;

import com.kildeen.allstreams.*;

record TopTitle(long id, String title, int views, Highlights highlights) {
     TopTitle(LongTuple<String, Integer> titleAndViews, Highlights highlights) {
         this(titleAndViews.getId(), titleAndViews.getOne(), titleAndViews.getTwo(), highlights);
    }
    public record Highlights(String producer, String rating, String genre) {};
}
