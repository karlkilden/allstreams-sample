package com.kildeen.allstreams.directory;

import com.kildeen.allstreams.*;
import org.apache.solr.client.solrj.SolrClient;
import org.eclipse.collections.impl.list.mutable.*;
import java.util.*;

public class SearchTopTitlesImpl implements SearchTopTitles {

    private SolrClient solrClient;

    public SearchTopTitlesImpl(SolrClient solrClient) {
        this.solrClient = solrClient;
    }

    @Override
    public List<LongTuple<String, Integer>> search() {
        return s
    }
}
