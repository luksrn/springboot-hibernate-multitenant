---Database admin

-- Schema: public

-- DROP SCHEMA public;


CREATE TABLE public.cliente (
    id        integer,
    nome     varchar(255),  
    CONSTRAINT agencia_id_cliente PRIMARY KEY(id)
);


insert into public.cliente values (1, 'Cliente 1 - Agência A');
insert into public.cliente values (2, 'Cliente 2 - Agência A');
insert into public.cliente values (3, 'Cliente 3 - Agência A');
insert into public.cliente values (4, 'Cliente 4 - Agência A');
insert into public.cliente values (5, 'Cliente 5 - Agência A');


CREATE SEQUENCE hibernate_sequence start with 100;
