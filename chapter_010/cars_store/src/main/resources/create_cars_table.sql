create table IF NOT EXISTS parts(
  id bigserial not null
    CONSTRAINT parts_pkey
    primary key,
  part_type varchar(255) not null,
  description varchar(255) NOT NULL
);

create table IF NOT EXISTS cars(
  id bigserial NOT NULL
    CONSTRAINT cars_pkey
    PRIMARY KEY,
  vin VARCHAR(255) NOT NULL,
  brand VARCHAR(255) NOT NULL
);

CREATE TABLE if NOT EXISTS car_part(
  car_id BIGINT NOT NULL ,
  part_id BIGINT NOT NULL ,
  FOREIGN KEY (car_id) REFERENCES cars(id),
  FOREIGN KEY (part_id) REFERENCES parts(id),
  CONSTRAINT car_part_pkey PRIMARY KEY (car_id, part_id)
);

CREATE TABLE IF NOT EXISTS users(
  id BIGSERIAL NOT NULL
    CONSTRAINT user_pkey
    PRIMARY KEY ,
  name VARCHAR(255) NOT NULL ,
  login VARCHAR(255) NOT NULL ,
  password VARCHAR(255) NOT NULL
);

INSERT INTO parts(part_type, description) VALUES ('TRANSMISSION', 'Manual transmission, 6 gears');
INSERT INTO parts(part_type, description) VALUES ('TRANSMISSION', 'Automatic transmission, 5 gears');
INSERT INTO parts(part_type, description) VALUES ('TRANSMISSION', 'CVT, 6 gears');
INSERT INTO parts(part_type, description) VALUES ('TRANSMISSION', 'DSG, 7 gears');

INSERT INTO parts(part_type, description) VALUES ('ENGINE', '1.4L tJet, 150 h/p');
INSERT INTO parts(part_type, description) VALUES ('ENGINE', '2.0L, 110 h/p');
INSERT INTO parts(part_type, description) VALUES ('ENGINE', '2.4L, 150 h/p');
INSERT INTO parts(part_type, description) VALUES ('ENGINE', '2.0 TDI, 150 h/p');

INSERT INTO parts(part_type, description) VALUES ('SUSPENSION', 'Passive suspension');
INSERT INTO parts(part_type, description) VALUES ('SUSPENSION', 'Semi-active suspension');
INSERT INTO parts(part_type, description) VALUES ('SUSPENSION', 'Active suspension');
INSERT INTO parts(part_type, description) VALUES ('SUSPENSION', 'Interconnected suspension');

INSERT INTO parts(part_type, description) VALUES ('BODY', 'Hatchback');
INSERT INTO parts(part_type, description) VALUES ('BODY', 'Sedan');
INSERT INTO parts(part_type, description) VALUES ('BODY', 'Coupe');
INSERT INTO parts(part_type, description) VALUES ('BODY', 'Crossover');

INSERT INTO cars_store.public.users(name, login, password) VALUES ('Ben', 'ben', 'ben');
INSERT INTO cars_store.public.users(name, login, password) VALUES ('Tom', 'tom', 'tom');
INSERT INTO cars_store.public.users(name, login, password) VALUES ('Pit', 'pit', 'pit');