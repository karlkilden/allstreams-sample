create table item
(
    item_id bigint,
    data     json
);

alter table item
    owner to postgres;

insert into item (item_id, data)
values  (100, '{"producer":"Karl", "genre": "drama"}'),
        (101, '{"rating":"pg13", "genre": "action"}'),
        (102, '{"rating":"pg9", "genre": "action"}');