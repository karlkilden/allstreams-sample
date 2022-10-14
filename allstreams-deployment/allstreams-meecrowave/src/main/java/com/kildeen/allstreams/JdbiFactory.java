package com.kildeen.allstreams;

import org.jdbi.v3.core.Jdbi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class JdbiFactory {

    @Produces
    Jdbi jdbi() {
        return PostgresContainer.INSTANCE.jdbi();
    }
}
