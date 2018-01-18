Task description:

Scripts for DBs creation and user passwords are placed in the root of MySQL folder.

Exist 4 entities:
1. User;
2. Address;
3. Role;
4. Music type.

Tables & relations:
1. Role: User(1 -> M);
2. User: Address(1 -> 1);
3. User: Music type(M -> M);

Tables Role and Music Type filled (USER, MODERATOR, ADMIN) & (RAP, ROCK, ...).

Implement DAO pattern for each of entities, there CRUD operations have to be implemented (Create, Find by ID, Retrieve by ID, Update, Delete ).
Spring and Hibernate can't be used.

WEB interface have to be added with possibility of entering under the given roles.