package com.kildeen.allstreams;

import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.SQLException;

class TestTest {

    Jdbi jdbi = PostgresContainer.INSTANCE.jdbi();

    @Test
    void name() throws SQLException {
        jdbi.withHandle(handle -> handle.createQuery("select 1").mapTo(String.class).one());
    }
}