--liquibase formatted sql
--changeset <litvik>:<add-created-column-to-movie-character>

ALTER TABLE movie_character ADD created varchar(255) not null;

--rollback ALTER TABLE DROP COLUMN created;