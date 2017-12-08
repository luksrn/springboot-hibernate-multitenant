---Database admin

-- Schema: public

-- DROP SCHEMA public;

CREATE TABLE public.cliente (
    id        integer,
    nome     varchar(255),  
    CONSTRAINT agencia_id_cliente PRIMARY KEY(id)
);


insert into public.cliente values (1, 'Cliente 1 - Agência B');
insert into public.cliente values (2, 'Cliente 2 - Agência B');
insert into public.cliente values (3, 'Cliente 3 - Agência B');


CREATE SEQUENCE hibernate_sequence start with 100;
