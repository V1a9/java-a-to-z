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