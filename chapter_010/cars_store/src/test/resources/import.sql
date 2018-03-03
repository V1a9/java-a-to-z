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

INSERT INTO cars_store.public.users(name, login, password) VALUES ('Admin', 'admin', 'admin');
INSERT INTO cars_store.public.users(name, login, password) VALUES ('Ben', 'ben', 'ben');
INSERT INTO cars_store.public.users(name, login, password) VALUES ('Tom', 'tom', 'tom');
-- INSERT INTO cars_store.public.users(name, login, password) VALUES ('Pit', 'pit', 'pit');