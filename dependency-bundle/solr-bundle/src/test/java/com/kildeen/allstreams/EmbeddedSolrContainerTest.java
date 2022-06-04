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
        solrContainer.getHost();

        SolrClient client = new Http2SolrClient.Builder(solrContainer.getHost2()).build();
        CollectionAdminRequest.Create.createCollection("allstreams", 1, 1);

        Map<String, Object> fieldAttributes = new LinkedHashMap<>();
        fieldAttributes.put("name", "title");
        fieldAttributes.put("type", "text_general");
        fieldAttributes.put("stored", true);

        SchemaRequest.AddField addFieldUpdateSchemaRequest =
                new SchemaRequest.AddField(fieldAttributes);

        UpdateRequest r = new UpdateRequest();
        SolrInputDocument document = new SolrInputDocument();
        document.addField("title", "my long ass title");

        SolrInputDocument document2 = new SolrInputDocument();
        document2.addField("title", "wong4u");
        r.add(document);
        r.add(document2);
        r.setCommitWithin(0);
        try {
            SchemaResponse.UpdateResponse addFieldResponse = addFieldUpdateSchemaRequest.process(client);
            SolrPingResponse response = client.ping();
            r.process(client);
            client.commit();
            SolrQuery query = new SolrQuery("title:long");
            query.setFields("title");
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