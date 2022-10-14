package com.kildeen.allstreams.directory;

import org.jdbi.v3.core.Jdbi;

public class FetchTopTitlesImpl implements FetchTopTitles {

    private Jdbi jdbi;

    public FetchTopTitlesImpl(Jdbi jdbi) {
        this.jdbi = jdbi;
    }
}
