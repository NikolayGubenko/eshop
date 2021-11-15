--liquibase formatted sql
--changeset eshop_sql:1

create table eshopDb.users
(
    id bigint auto_increment
        primary key,
    email varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    password varchar(255) not null,
    active bit null
);

--changeset eshop_sql:2

create table eshopDb.roles
(
    id bigint auto_increment
        primary key,
    name varchar(255) not null
);

--changeset eshop_sql:3

create table eshopDb.user_role
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id)
);

create index FKt7e7djp752sqn6w22i6ocqy6q
    on eshopDb.user_role (role_id);

--changeset eshop_sql:4

create table eshopDb.orders
(
    id bigint auto_increment
        primary key,
    date datetime not null,
    description varchar(4000) null,
    is_active bit null,
    status varchar(255) not null,
    postal_id bigint not null,
    user_id bigint not null
);

create index FK21cp87c57dpg2laiorki5kbca
    on eshopDb.orders (postal_id);

create index FK32ql8ubntj5uh44ph9659tiih
    on eshopDb.orders (user_id);


--changeset eshop_sql:5

create table eshopDb.products
(
    id bigint auto_increment
        primary key,
    name varchar(255) not null,
    price decimal(19,2) not null,
    product_type varchar(255) not null
);

--changeset eshop_sql:6

create table eshopDb.postal_offices
(
    id bigint auto_increment
        primary key,
    address varchar(255) not null,
    name varchar(255) not null
);

--changeset eshop_sql:7

create table eshopDb.verification_tokens
(
    id bigint auto_increment
        primary key,
    created_date datetime not null,
    token varchar(255) not null,
    user_id bigint not null
);

create index FK54y8mqsnq1rtyf581sfmrbp4f
    on eshopDb.verification_tokens (user_id);

--changeset eshop_sql:8

create table eshopDb.order_products
(
    id bigint auto_increment
        primary key,
    quantity bigint not null,
    order_id bigint not null,
    product_id bigint not null
);

create index FKawxpt1ns1sr7al76nvjkv21of
    on eshopDb.order_products (order_id);

create index FKdxjduvg7991r4qja26fsckxv8
    on eshopDb.order_products (product_id);
