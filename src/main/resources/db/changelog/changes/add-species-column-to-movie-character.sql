--liquibase formatted sql
--changeset <litvik>:<add-species-column-to-movie-character>

ALTER TABLE movie_character ADD species varchar(255) not null;

--rollback ALTER TABLE DROP COLUMN species;