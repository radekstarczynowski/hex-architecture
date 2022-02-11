--liquibase formatted sql
--changeset radek:create-books-table
create table books
(
    uuid uuid primary key,
    author varchar(255) not null,
    title varchar(255) not null,
    gender varchar(255) not null
);
--rollback drop table books;