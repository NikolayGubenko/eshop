--liquibase formatted sql
--changeset eshop_sql:9

insert into eshopDb.roles (name)
values ('ADMIN'),
       ('USER');

--changeset eshop_sql:10

insert into users (email, first_name, last_name, password, active)
values ('a@a.com', 'Alexey', 'Petrov', '$2a$12$WRsa6exBj21poSTwC2s8zOi5ujlIwY.3NrxBGg30YTC4kHsRQa9/O', 1),
       ('b@b.com', 'Ivan', 'Ivanov', '$2a$12$yTNASo552FJCmlUZbvJWEOWw70WYGveSShYOHV6.xNFG6xXD56pOG', 1);

insert into user_role(user_id, role_id)
values (1, 1),
       (2, 2);

--changeset eshop_sql:11

insert into products(name, price, product_type)
values ('RTX2060', 21000, 'GPU'),
       ('RTX3060', 33000, 'GPU'),
       ('GTX 1660', 19000, 'GPU'),
       ('GTX 1650', 12000, 'GPU'),
       ('GTX 1030', 3000, 'GPU'),
       ('RX 6800', 40000, 'GPU'),
       ('RX 6600', 25000, 'GPU'),
       ('core i5 11100', 3500, 'CPU'),
       ('core i5 11400', 5000, 'CPU'),
       ('core i7 11700', 8500, 'CPU'),
       ('core i9 11900', 12000, 'CPU'),
       ('Ryzen 3600', 6000, 'CPU'),
       ('Ryzen 5800', 13000, 'CPU');

insert into postal_offices (name, address)
values ('Office N1', 'Some street name 1'),
       ('Office N2', 'Some street name 2'),
       ('Office N4', 'Some street name 6'),
       ('Office N5', 'Some street name 8'),
       ('Office N12', 'Some street name 9'),
       ('Office N26', 'Some street name 33');

--changeset eshop_sql:12

insert into orders(date, description, is_active, status, postal_id, user_id)
values (NOW(), 'Order from Ivan', 1, 'NEW', 1, 2);

insert into orders(date, description, is_active, status, postal_id, user_id)
values (NOW(), 'Order from ivan', 1, 'NEW', 2, 2);

insert into order_products(order_id, product_id, quantity)
values (1, 1, 3),
       (1, 3, 1),
       (1, 2, 1),
       (2, 5, 2),
       (2, 9, 1),
       (2, 11, 2);