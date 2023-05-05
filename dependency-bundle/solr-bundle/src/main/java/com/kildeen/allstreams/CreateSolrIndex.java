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
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * In this sample solr is read only. This class does what some other service is supposed to have done
 */
public class CreateSolrIndex {

    List<String> titles = List.of("The last ORM standing",
            "The history of programming",
            "Clean architecture - the Movie"
    );

    List<Long> ids = List.of(200L,
            300L,
            400L,
            500L);
    List<Long> views = List.of(20L,
            200L,
            332L);

    List<FieldType> fieldTypes = List.of(new FieldType("plong", "item_id", true, true),
            new FieldType("text_general", "title", true, false),
            new FieldType("plong", "views", true, false)
            );

    public  void create() {
        EmbeddedSolrContainer solrContainer = new EmbeddedSolrContainer(DockerImageName.parse("solr:slim"));
        solrContainer.start();
        SolrClient client = new Http2SolrClient.Builder(solrContainer.getHost2()).build();
        CollectionAdminRequest.Create.createCollection("allstreams", 1, 1);

        createSchema(client);

        UpdateRequest r = new UpdateRequest();

        for (int i = 0; i < titles.size(); i++) {

            SolrInputDocument document = new SolrInputDocument();
            document.addField("title", titles.get(i));
            document.addField("item_id", ids.get(i));
            document.addField("views", views.get(i));
            r.add(document);
        }
        try {
            r.setCommitWithin(0);
            client.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createSchema(SolrClient client) {
        for (FieldType f : fieldTypes) {
            Map<String, Object> fieldConf = new LinkedHashMap<>();
            fieldConf.put("name", f.name());
            fieldConf.put("type", f.type());
            fieldConf.put("stored", f.stored());
            fieldConf.put("docValues", f.docValues());
            try {
                SchemaResponse.UpdateResponse addTitleReq =
                        new SchemaRequest.AddField(fieldConf).process(client);
            } catch (SolrServerException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private record FieldType(String type, String name, boolean stored, boolean docValues) {};
}
