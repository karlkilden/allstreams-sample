package com.kildeen.allstreams;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.Http2SolrClient;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.request.schema.SchemaRequest;
import org.apache.solr.client.solrj.response.SolrPingResponse;
import org.apache.solr.client.solrj.response.schema.SchemaResponse;
import org.apache.solr.common.SolrInputDocument;
import org.jdbi.v3.core.Jdbi;
import org.testcontainers.utility.DockerImageName;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@ApplicationScoped
public class SolrFactory {

    private SolrClient client;

    @Produces
    SolrClient client() {
        return client;
    }

    @PostConstruct
    public void init() {
        EmbeddedSolrContainer solrContainer = new EmbeddedSolrContainer(DockerImageName.parse("solr:slim"));
        solrContainer.start();
        solrContainer.getHost();

        SolrClient client = new Http2SolrClient.Builder(solrContainer.getHost2()).build();
        CollectionAdminRequest.Create.createCollection("allstreams", 1, 1);

        Map<String, Object> titleField = new LinkedHashMap<>();
        titleField.put("name", "title_");
        titleField.put("type", "text_general");
        titleField.put("stored", true);

        Map<String, Object> idField = new LinkedHashMap<>();
        idField.put("name", "");
        idField.put("type", "text_general");
        idField.put("stored", true);

        SchemaRequest.AddField addTitleReq =
                new SchemaRequest.AddField(titleField);

        SchemaRequest.AddField addIdReq =
                new SchemaRequest.AddField(idField);

        UpdateRequest r = new UpdateRequest();
        SolrInputDocument document = new SolrInputDocument();
        document.addField("title", "my long ass title");

        SolrInputDocument document2 = new SolrInputDocument();
        document2.addField("title", "wong4u");
        r.add(document);
        r.add(document2);
        r.setCommitWithin(0);
        try {
            SchemaResponse.UpdateResponse addFieldResponse = addTitleReq.process(client);
            addFieldResponse = addIdReq.process(client);
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
