BEGIN;

CREATE TABLE IF NOT EXISTS addresses(
  id SERIAL PRIMARY KEY ,
  address VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS roles(
  id SERIAL PRIMARY KEY ,
  role VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS musics(
  id SERIAL PRIMARY KEY ,
  music VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS users(
  id SERIAL PRIMARY KEY ,
  name VARCHAR(255),
  login VARCHAR(255),
  password VARCHAR(255),
  role INTEGER,
  address INTEGER,
  FOREIGN KEY (role) REFERENCES roles(id),
  FOREIGN KEY (address) REFERENCES addresses(id)
);

CREATE TABLE IF NOT EXISTS users_music(
  user_id INTEGER,
  music_id INTEGER,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (music_id) REFERENCES musics(id)
);

INSERT INTO roles(role) VALUES ('ADMIN');
INSERT INTO roles(role) VALUES ('MODERATOR');
INSERT INTO roles(role) VALUES ('USER');

INSERT INTO musics(music) VALUES ('ROCK');
INSERT INTO musics(music) VALUES ('GRUNGE');
INSERT INTO musics(music) VALUES ('METAL');

INSERT INTO users(name, login, password, role) VALUES ('admin', 'admin', 'admin', 1);

COMMIT;