package com.kildeen.allstreams.directory;

import com.kildeen.allstreams.LongTuple;
import com.kildeen.allstreams.LongTuples;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;

import java.util.List;

public class SearchTopItemsImpl implements SearchTopItems {

    private SolrClient solrClient;

    public SearchTopItemsImpl(SolrClient solrClient) {
        this.solrClient = solrClient;
    }
    @Override
    public LongTuples<String, Integer> search() {
        SolrQuery query = new SolrQuery("title:long");
        query.setFields("title");
        return new LongTuples<>(List.of(LongTuple.of(100L, "Title", 200)));
    }
}
