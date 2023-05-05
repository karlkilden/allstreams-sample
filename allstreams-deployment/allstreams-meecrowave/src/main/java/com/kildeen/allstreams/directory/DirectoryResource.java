package com.kildeen.allstreams.directory;

import org.apache.solr.client.solrj.SolrClient;
import org.jdbi.v3.core.Jdbi;

import javax.enterprise.context.*;
import javax.inject.*;
import javax.ws.rs.*;
import java.util.*;
import static javax.ws.rs.core.MediaType.*;

@Path("directory/")
@ApplicationScoped
public class DirectoryResource {

    private ListTopItem listTopItem;

    @Inject
    public DirectoryResource(Jdbi jdbi, SolrClient solrClient) {
        this.listTopItem = new ListTopItem(new SearchTopItemsImpl(solrClient), new FetchTopItemsImpl(jdbi));
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/top")
    public List<TopTitleDTO> listTopTitles () {
        return listTopItem.list();
    }
}
