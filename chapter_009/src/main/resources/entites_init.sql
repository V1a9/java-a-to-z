BEGIN;

CREATE TABLE IF NOT EXISTS addresses(
  id SERIAL PRIMARY KEY ,
  country VARCHAR(255),
  city VARCHAR(255),
  street VARCHAR(255),
  appartment VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS roles(
  id SERIAL PRIMARY KEY ,
  role VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS musics(
  id SERIAL PRIMARY KEY ,
  genre VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS users(
  id SERIAL PRIMARY KEY ,
  name VARCHAR(255),
  login VARCHAR(255),
  password VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS users_music(
  user_id INTEGER,
  genre_id INTEGER,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (genre_id) REFERENCES musics(id)
);

INSERT INTO roles(role) VALUES ('USER');
INSERT INTO roles(role) VALUES ('MODERATOR');
INSERT INTO roles(role) VALUES ('ADMIN');

INSERT INTO musics(genre) VALUES ('ROCK');
INSERT INTO musics(genre) VALUES ('GRUNGE');
INSERT INTO musics(genre) VALUES ('METAL');

COMMIT;