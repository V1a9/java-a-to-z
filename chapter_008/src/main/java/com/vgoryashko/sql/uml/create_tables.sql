create table roles(
	role_id serial primary key,
	description text
);

create table rights(
	right_id serial primary key,
	description text
);

create table role_rights(
	role_id integer references roles(role_id),
	right_id integer references rights(right_id)
);

create table users(
	user_id serial primary key,
	login varchar(256),
	password varchar(256),
	user_name text,
	role_id integer references roles(role_id),
	create_date timestamp without time zone default CURRENT_TIMESTAMP
);

--

create table order_categories(
	category_id serial primary key,
	description text
);

create table orders(
	order_id serial primary key,
	description text,
	user_id integer references users(user_id),
	category_id integer references order_categories(category_id),
	create_date timestamp without time zone default CURRENT_TIMESTAMP
);

create table order_comments(
	comment_id serial primary key,
	description text,
	order_id integer references orders(order_id),
	create_date timestamp without time zone default CURRENT_TIMESTAMP
);

create table order_statuses(
	status_id serial primary key,
	status text,
	order_id integer references orders(order_id),
	create_date timestamp without time zone default CURRENT_TIMESTAMP
);

create table attached_files(
	file_id serial primary key,
	description text,
	file_path text,
	order_id integer references orders(order_id)
);
