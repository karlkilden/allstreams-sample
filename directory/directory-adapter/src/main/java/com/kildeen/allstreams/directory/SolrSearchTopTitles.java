package com.kildeen.allstreams.directory;

import com.kildeen.allstreams.*;
import org.eclipse.collections.impl.list.mutable.*;
import java.util.*;

public class SolrSearchTopTitles implements SearchTopTitles {

    @Override
    public List<LongTuple<String, Integer>> search() {
        return FastList.newListWith(LongTuple.of(100L,"Wong", 10));
    }
}
