--liquibase formatted sql
--changeset <litvik>:<add-image-column-to-movie-character>

ALTER TABLE movie_character ADD image varchar(255) not null;

--rollback ALTER TABLE DROP COLUMN image;