--liquibase formatted sql
--changeset <litvik>:<add-type-column-to-movie-character>

ALTER TABLE movie_character ADD type varchar(255) not null;

--rollback ALTER TABLE DROP COLUMN type;