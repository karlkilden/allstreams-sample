package com.kildeen.allstreams;

import org.apache.solr.client.solrj.*;
import org.apache.solr.client.solrj.impl.*;
import org.apache.solr.client.solrj.request.*;
import org.apache.solr.client.solrj.request.schema.*;
import org.apache.solr.client.solrj.response.*;
import org.apache.solr.client.solrj.response.schema.*;
import org.apache.solr.common.*;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.*;
import org.testcontainers.utility.*;
import java.io.*;
import java.util.*;

class EmbeddedSolrContainerTest {

    @Test
    void name() {
        EmbeddedSolrContainer solrContainer = new EmbeddedSolrContainer(DockerImageName.parse("solr:slim"));
        solrContainer.start();
        SolrClient client = new Http2SolrClient.Builder(solrContainer.getHost2()).build();
        CollectionAdminRequest.Create.createCollection("allstreams", 1, 1);

        Map<String, Object> idField = new LinkedHashMap<>();
        idField.put("name", "item_id");
        idField.put("type", "plong");
        idField.put("stored", true);
        idField.put("docValues", true);

        Map<String, Object> viewsField = new LinkedHashMap<>();
        viewsField.put("name", "views");
        viewsField.put("type", "plong");
        viewsField.put("stored", true);
        viewsField.put("docValues", true);

        //SchemaRequest.AddField addTitleReq =
          //      new SchemaRequest.AddField(titleField);

        SchemaRequest.AddField addIdReq =
                new SchemaRequest.AddField(idField);

        SchemaRequest.AddField addViewsField =
                new SchemaRequest.AddField(viewsField);

        UpdateRequest r = new UpdateRequest();
        SolrInputDocument document = new SolrInputDocument();
        document.addField("title", "my long ass title");
        document.addField("item_id", 200L);

        SolrInputDocument document2 = new SolrInputDocument();
        document2.addField("title", "wong4u");
        r.add(document);
        r.add(document2);
        r.setCommitWithin(0);
        try {
            SchemaResponse.UpdateResponse addFieldResponse = null;
            addFieldResponse = addIdReq.process(client);
            addFieldResponse = addViewsField.process(client);
            SolrPingResponse response = client.ping();
            r.process(client);
            client.commit();
            SolrQuery query = new SolrQuery("item_id:200");
            var resp = client.query(query);

            System.out.println(response);
            System.out.println(resp);

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}