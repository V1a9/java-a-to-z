insert into rights(description) values('Create users');
insert into rights(description) values('Modify users');
insert into rights(description) values('Delete users');
insert into rights(description) values('Create orders');
insert into rights(description) values('Add status');
insert into rights(description) values('Check status');
insert into rights(description) values('Add comments');
insert into rights(description) values('Read comments');
insert into rights(description) values('Attach files');
insert into rights(description) values('Download files');

insert into roles(description) values ('Administrator');
insert into roles(description) values ('Consumer');
insert into roles(description) values ('Sales manager');

insert into role_rights(role_id, right_id) values (1, 1);
insert into role_rights(role_id, right_id) values (1, 2);
insert into role_rights(role_id, right_id) values (1, 3);
insert into role_rights(role_id, right_id) values (2, 4);
insert into role_rights(role_id, right_id) values (2, 6);
insert into role_rights(role_id, right_id) values (2, 7);
insert into role_rights(role_id, right_id) values (2, 8);
insert into role_rights(role_id, right_id) values (2, 10);
insert into role_rights(role_id, right_id) values (3, 5);
insert into role_rights(role_id, right_id) values (3, 7);
insert into role_rights(role_id, right_id) values (3, 8);
insert into role_rights(role_id, right_id) values (3, 9);
insert into role_rights(role_id, right_id) values (3, 10);

insert into users(login, password, user_name, role_id) values ('admin', 'admin', 'Jhone Scott', 1);
insert into users(login, password, user_name, role_id) values ('user1', '1234', 'Tom Raddle', 2);
insert into users(login, password, user_name, role_id) values ('user2', '2345', 'Pit Walker', 3);

insert into order_categories(description) values('Food');
insert into order_categories(description) values('Electronics');
insert into order_categories(description) values('Clothes');

insert into orders(description, user_id, category_id) values ('Pants', 2, 3);
insert into orders(description, user_id, category_id) values ('Beer', 3, 1);
insert into orders(description, user_id, category_id) values ('TV', 2, 2);
insert into orders(description, user_id, category_id) values ('Phone', 2, 2);
insert into orders(description, user_id, category_id) values ('T-shirt', 3, 3);
insert into orders(description, user_id, category_id) values ('Milk', 3, 1);
insert into orders(description, user_id, category_id) values ('Laptop', 2, 2);

insert into order_statuses(status, order_id) values('Processed', 1);
insert into order_statuses(status, order_id) values('Processed', 2);
insert into order_statuses(status, order_id) values('Paid', 2);
insert into order_statuses(status, order_id) values('Processed', 3);
insert into order_statuses(status, order_id) values('Paid', 3);
insert into order_statuses(status, order_id) values('Shipped', 2);
insert into order_statuses(status, order_id) values('In process', 4);
insert into order_statuses(status, order_id) values('In process', 5);
insert into order_statuses(status, order_id) values('In process', 6);
insert into order_statuses(status, order_id) values('In process', 7);

insert into order_comments(description, order_id) values('No commetns', 1);
insert into order_comments(description, order_id) values('No commetns', 2);
insert into order_comments(description, order_id) values('No commetns', 3);
insert into order_comments(description, order_id) values('No commetns', 4);
insert into order_comments(description, order_id) values('No commetns', 5);
insert into order_comments(description, order_id) values('No commetns', 6);
insert into order_comments(description, order_id) values('No commetns', 7);

insert into attached_files(description, file_path, order_id) values('Invoice', './attachments/1', 1);
insert into attached_files(description, file_path, order_id) values('Invoice', './attachments/2', 2);
insert into attached_files(description, file_path, order_id) values('Invoice', './attachments/3', 3);
insert into attached_files(description, file_path, order_id) values('Invoice', './attachments/4', 4);
insert into attached_files(description, file_path, order_id) values('Invoice', './attachments/5', 5);
insert into attached_files(description, file_path, order_id) values('Invoice', './attachments/6', 6);
insert into attached_files(description, file_path, order_id) values('Invoice', './attachments/7', 7);
