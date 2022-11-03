--liquibase formatted sql
--changeset <litvik>:<create-movie-character-table>
CREATE TABLE IF NOT EXISTS movie_character
(
    id bigint auto_increment,
    name varchar(255) not null,
    status varchar(255) not null,
    gender varchar(255) not null,
    CONSTRAINT movie_chaaracter_pk PRIMARY KEY (id)
    );

--rollback DROP TABLE movie_character;