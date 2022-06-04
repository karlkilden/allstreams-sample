package com.kildeen.allstreams;

import com.kildeen.allstreams.directory.*;
import javax.enterprise.context.*;
import javax.inject.*;
import javax.ws.rs.*;
import java.util.*;
import static javax.ws.rs.core.MediaType.*;

@Path("directory/")
@ApplicationScoped
public class DirectoryResource {

    private TitleResource titleResource;

    @Inject
    public DirectoryResource() {
        this.titleResource = new TitleResource();
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/top-titles")
    public List<TopTitleDTO> listTopTitles () {
        return titleResource.topTitles();
    }

}
