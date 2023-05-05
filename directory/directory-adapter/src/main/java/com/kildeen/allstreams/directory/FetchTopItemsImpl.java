package com.kildeen.allstreams.directory;

import com.kildeen.allstreams.LongTuple;
import com.kildeen.allstreams.LongTuples;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FetchTopItemsImpl implements FetchTopItems {

    private Jdbi jdbi;

    public FetchTopItemsImpl(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public LongTuples<Long, TopItemTuple> fetch(List<Long> itemIds) {
        var query = """
                SELECT * from item where item_id IN (:ids)
                """;
        return new LongTuples<>(jdbi.withHandle(handle ->
                handle
                        .createQuery(query)
                        .bind("ids", itemIds)
                        .map((rs, ctx) -> mapTerms(rs, ctx))
                        .list()));
    }

    private LongTuple<Long, TopItemTuple> mapTerms(ResultSet rs, StatementContext ctx) throws SQLException {
        long itemId = rs.getLong("item_id");
        return LongTuple.of(itemId, itemId,
                new TopItemTuple(rs.getString("producer"), rs.getString("rating"), rs.getString("genre")));
    }
}
