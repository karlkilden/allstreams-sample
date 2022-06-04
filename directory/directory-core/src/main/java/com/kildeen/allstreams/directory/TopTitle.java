package com.kildeen.allstreams.directory;

import com.kildeen.allstreams.*;

record TopTitle(long id, String title, int views) {


     TopTitle(LongTuple<String, Integer> t) {
         this(t.getId(), t.getOne(), t.getTwo());
    }
}
