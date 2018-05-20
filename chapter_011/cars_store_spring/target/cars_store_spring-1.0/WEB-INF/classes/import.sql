-- DROP TABLE IF EXISTS users CASCADE ;
-- CREATE TABLE IF NOT EXISTS users(
--   user_id BIGSERIAL NOT NULL PRIMARY KEY ,
--   name VARCHAR(255) NOT NULL ,
--   login VARCHAR(255) NOT NULL ,
--   password VARCHAR(255) NOT NULL
-- );

-- DROP TABLE IF EXISTS cars CASCADE ;
-- create table IF NOT EXISTS cars(
--   car_id BIGSERIAL NOT NULL PRIMARY KEY,
--   vin VARCHAR(255) NOT NULL UNIQUE ,
--   brand VARCHAR(255) NOT NULL
-- );
--
-- DROP TABLE IF EXISTS parts CASCADE ;
-- CREATE TABLE IF NOT EXISTS parts(
--   part_id BIGSERIAL NOT NULL PRIMARY KEY ,
--   type VARCHAR(255) NOT NULL ,
--   description VARCHAR(255) NOT NULL
-- );
--
-- DROP TABLE IF EXISTS parts_cars CASCADE ;
-- CREATE TABLE if NOT EXISTS parts_cars(
--   car_id BIGINT NOT NULL ,
--   part_id BIGINT NOT NULL ,
--   FOREIGN KEY (car_id) REFERENCES cars(car_id)  ,
--   FOREIGN KEY (part_id) REFERENCES parts(part_id) ,
--   PRIMARY KEY (car_id, part_id)
-- );
--
-- DROP TABLE IF EXISTS advertisements CASCADE ;
-- CREATE TABLE IF NOT EXISTS advertisements(
--   advertisement_id BIGSERIAL NOT NULL PRIMARY KEY ,
--   description VARCHAR(255) NOT NULL ,
--   price INTEGER NOT NULL ,
--   sold BOOLEAN NOT NULL DEFAULT FALSE ,
--   created TIMESTAMP WITHOUT TIME ZONE ,
--   user_id BIGSERIAL NOT NULL ,
--   car_id BIGSERIAL UNIQUE ,
--   FOREIGN KEY (user_id) REFERENCES users (user_id) ,
--   FOREIGN KEY (car_id) REFERENCES cars(car_id)
-- );
--
-- DROP TABLE IF EXISTS photos CASCADE ;
-- CREATE TABLE IF NOT EXISTS photos(
--   photo_id BIGSERIAL PRIMARY KEY ,
--   advertisement_id BIGSERIAL NOT NULL ,
--   file VARCHAR(1024) NOT NULL UNIQUE ,
--   FOREIGN KEY (advertisement_id) REFERENCES advertisements (advertisement_id)
-- );
--
-- DROP TABLE IF EXISTS brands CASCADE ;
-- CREATE TABLE IF NOT EXISTS brands(
--   brand_id SERIAL PRIMARY KEY ,
--   name VARCHAR(255) NOT NULL UNIQUE
-- );
--
INSERT INTO parts(type, description) VALUES ('TRANSMISSION', 'Manual transmission, 6 gears');
INSERT INTO parts(type, description) VALUES ('TRANSMISSION', 'Automatic transmission, 5 gears');
INSERT INTO parts(type, description) VALUES ('TRANSMISSION', 'CVT, 6 gears');
INSERT INTO parts(type, description) VALUES ('TRANSMISSION', 'DSG, 7 gears');

INSERT INTO parts(type, description) VALUES ('ENGINE', '1.4L tJet, 150 h/p');
INSERT INTO parts(type, description) VALUES ('ENGINE', '2.0L, 110 h/p');
INSERT INTO parts(type, description) VALUES ('ENGINE', '2.4L, 150 h/p');
INSERT INTO parts(type, description) VALUES ('ENGINE', '2.0 TDI, 150 h/p');

INSERT INTO parts(type, description) VALUES ('SUSPENSION', 'Passive suspension');
INSERT INTO parts(type, description) VALUES ('SUSPENSION', 'Semi-active suspension');
INSERT INTO parts(type, description) VALUES ('SUSPENSION', 'Active suspension');
INSERT INTO parts(type, description) VALUES ('SUSPENSION', 'Interconnected suspension');

INSERT INTO parts(type, description) VALUES ('BODY', 'Hatchback');
INSERT INTO parts(type, description) VALUES ('BODY', 'Sedan');
INSERT INTO parts(type, description) VALUES ('BODY', 'Coupe');
INSERT INTO parts(type, description) VALUES ('BODY', 'Crossover');

INSERT INTO parts(type, description) VALUES ('BRAND', 'BMW');
INSERT INTO parts(type, description) VALUES ('BRAND', 'MERCEDES');
INSERT INTO parts(type, description) VALUES ('BRAND', 'FIAT');
INSERT INTO parts(type, description) VALUES ('BRAND', 'AUDI');
INSERT INTO parts(type, description) VALUES ('BRAND', 'FORD');
INSERT INTO parts(type, description) VALUES ('BRAND', 'WV');

INSERT INTO users(name, login, password) VALUES ('Ben', 'ben', 'ben');
INSERT INTO users(name, login, password) VALUES ('Tom', 'tom', 'tom');
INSERT INTO users(name, login, password) VALUES ('Pit', 'pit', 'pit');