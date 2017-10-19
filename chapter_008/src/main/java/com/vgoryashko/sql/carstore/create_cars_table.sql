create table transmissions(
	transmission_id serial primary key,
	description text
);

CREATE TABLE engines(
  engine_id SERIAL PRIMARY KEY,
  description text
);

CREATE TABLE suspensions(
  suspension_id SERIAL PRIMARY KEY,
  description text
);

create table cars(
  car_id serial primary key,
  descritpion text,
  engine_id INTEGER REFERENCES engines(engine_id),
  suspension_id INTEGER REFERENCES suspensions(suspension_id),
  transmission_id INTEGER REFERENCES transmissions(transmission_id)
);

CREATE TABLE products_orders(
  product_id INTEGER REFERENCES cars(car_id),
  order_id INTEGER REFERENCES orders(order_id)
);

INSERT INTO transmissions(description) VALUES ('Manual transmission, 6 gears');
INSERT INTO transmissions(description) VALUES ('Automatic transmission, 5 gears');
INSERT INTO transmissions(description) VALUES ('CVT, 6 gears');
INSERT INTO transmissions(description) VALUES ('DSG, 7 gears');

INSERT INTO engines(description) VALUES ('1.4L tJet, 150 h/p');
INSERT INTO engines(description) VALUES ('2.0L, 110 h/p');
INSERT INTO engines(description) VALUES ('2.4L, 150 h/p');
INSERT INTO engines(description) VALUES ('2.0 TDI, 150 h/p');

INSERT INTO suspensions(description) VALUES ('Passive suspension');
INSERT INTO suspensions(description) VALUES ('Semi-active suspension');
INSERT INTO suspensions(description) VALUES ('Active suspension');
INSERT INTO suspensions(description) VALUES ('Interconnected suspension');

INSERT INTO order_categories(description) VALUES ('Vehicles');

INSERT INTO cars(descritpion, engine_id, suspension_id, transmission_id) VALUES ('Ford fusion', 1, 1, 1);
INSERT INTO cars(descritpion, engine_id, suspension_id, transmission_id) VALUES ('Fiat doblo', 4, 2, 3);
INSERT INTO cars(descritpion, engine_id, suspension_id, transmission_id) VALUES ('BMW x3', 2, 1, 2);

insert into orders(description, user_id, category_id) values ('Ford', 2, 4);
insert into orders(description, user_id, category_id) values ('Fiat', 3, 4);
insert into orders(description, user_id, category_id) values ('BMW', 1, 4);

INSERT INTO products_orders(product_id, order_id) VALUES (1, 8);
INSERT INTO products_orders(product_id, order_id) VALUES (2, 9);
INSERT INTO products_orders(product_id, order_id) VALUES (3, 10);

SELECT * FROM cars;

SELECT e.description, t.description, s.description FROM cars as c RIGHT OUTER JOIN engines as e on c.engine_id = e.engine_id
  FULL OUTER JOIN transmissions AS t on c.transmission_id = t.transmission_id
  FULL OUTER JOIN suspensions as s on c.suspension_id = s.suspension_id WHERE c.car_id ISNULL;