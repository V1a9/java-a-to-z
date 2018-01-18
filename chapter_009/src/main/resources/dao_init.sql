BEGIN;

CREATE TABLE IF NOT EXISTS addresses(
  id SERIAL PRIMARY KEY ,
  address VARCHAR(255)[5]
);

CREATE TABLE IF NOT EXISTS roles(
  id SERIAL PRIMARY KEY ,
  role VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS musics(
  id SERIAL PRIMARY KEY ,
  ganre VARCHAR(255)
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
  "user" INTEGER,
  ganre INTEGER,
  FOREIGN KEY ("user") REFERENCES users(id),
  FOREIGN KEY (ganre) REFERENCES musics(id)
);

INSERT INTO roles(role) VALUES ('USER');
INSERT INTO roles(role) VALUES ('MODERATOR');
INSERT INTO roles(role) VALUES ('ADMIN');

INSERT INTO musics(ganre) VALUES ('ROCK');
INSERT INTO musics(ganre) VALUES ('GRUNGE');
INSERT INTO musics(ganre) VALUES ('METAL');

COMMIT;