CREATE TABLE if NOT EXISTS countries(
  id SERIAL PRIMARY KEY ,
  city VARCHAR(255)
);

CREATE TABLE if NOT EXISTS cities(
  id SERIAL PRIMARY KEY ,
  city VARCHAR(255),
  country_id INTEGER,
  FOREIGN KEY (country_id) REFERENCES countries (id)
);

INSERT INTO countries (country) VALUES ('Canada');
INSERT INTO countries (country) VALUES ('USA');

INSERT INTO cities(city, country_id) VALUES ('Toronto', 1);
INSERT INTO cities(city, country_id) VALUES ('Calgary', 1);
INSERT INTO cities(city, country_id) VALUES ('Vancouver', 1);
INSERT INTO cities(city, country_id) VALUES ('Ottawa', 1);
INSERT INTO cities(city, country_id) VALUES ('New York', 2);
INSERT INTO cities(city, country_id) VALUES ('Miami', 2);
INSERT INTO cities(city, country_id) VALUES ('Seattle', 2);
INSERT INTO cities(city, country_id) VALUES ('California', 2);