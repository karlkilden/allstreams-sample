package com.kildeen.allstreams;

import com.github.dockerjava.api.command.*;
import org.apache.solr.client.solrj.*;
import org.apache.solr.client.solrj.impl.*;
import org.apache.solr.client.solrj.response.*;
import org.testcontainers.containers.*;
import org.testcontainers.utility.*;
import java.io.*;

public class EmbeddedSolrContainer extends GenericContainer<SolrContainer> {
    private static final Integer SOLR_PORT = 8983;
    public String getHost2() {
        StringBuilder sb = new StringBuilder();
        sb.append("http://");
        sb.append(getContainerIpAddress());
        sb.append(":");
        sb.append(getMappedPort(SOLR_PORT));
        sb.append("/solr");
        sb.append("/allstreams");
        return sb.toString();
    }

    public EmbeddedSolrContainer(DockerImageName dockerImageName) {
        super(dockerImageName);
    }

    @Override
    protected void configure() {
        addExposedPort(SOLR_PORT);
    }

    @Override
    protected void containerIsStarted(InspectContainerResponse containerInfo) {
        super.containerIsStarted(containerInfo);

        String[] commands = { "/bin/sh", "-c", "bin/solr create_core -c " + "allstreams" };
        try {
            var result = execInContainer(commands);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}