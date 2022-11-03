--liquibase formatted sql
--changeset <litvik>:<add-external-id-column-to-movie-character-table>

ALTER TABLE movie_character ADD external_id bigint;

--rollback ALTER TABLE DROP COLUMN external_id;