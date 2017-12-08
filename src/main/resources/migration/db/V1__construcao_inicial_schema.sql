---Database admin

-- Schema: public

-- DROP SCHEMA public;

CREATE SCHEMA public
  AUTHORIZATION postgres;

GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;
COMMENT ON SCHEMA public
  IS 'standard public schema';

 
CREATE TABLE public.agencia (
     id integer,
     nome varchar(255) not null,
     tenant_id varchar(40) not null,
     CONSTRAINT id_agencia PRIMARY KEY(id),
     CONSTRAINT tagencia_enant_id unique(tenant_id)
);

CREATE TABLE public.usuario (
    id        integer,
    login     varchar(40),
    senha     varchar(40),
    id_agencia   integer REFERENCES public.agencia (id),    
    CONSTRAINT id_usuario PRIMARY KEY(id)
);


insert into public.agencia values ( 1, 'Agência A', 'agencia_a');
insert into public.agencia values ( 2, 'Agência B', 'agencia_b');

insert into public.usuario values( 1, 'usuario_a', 'teste', 1);
insert into public.usuario values( 2, 'usuario_b', 'teste', 2);


CREATE SCHEMA agencia_a
  AUTHORIZATION postgres;
CREATE SCHEMA agencia_b
  AUTHORIZATION postgres;


CREATE TABLE agencia_a.cliente (
    id        integer,
    nome     varchar(255),  
    CONSTRAINT agencia_a_id_cliente PRIMARY KEY(id)
);

CREATE TABLE agencia_b.cliente (
    id        integer,
    nome     varchar(255)  ,
    CONSTRAINT agencia_b_id_cliente PRIMARY KEY(id)
);


CREATE SEQUENCE hibernate_sequence start with 100;

insert into agencia_a.cliente values (1, 'Cliente 1 - Agência A');
insert into agencia_a.cliente values (2, 'Cliente 2 - Agência A');
insert into agencia_a.cliente values (3, 'Cliente 3 - Agência A');
insert into agencia_a.cliente values (4, 'Cliente 4 - Agência A');
insert into agencia_a.cliente values (5, 'Cliente 5 - Agência A');


insert into agencia_b.cliente values (1, 'Cliente 1 - Agência B');
insert into agencia_b.cliente values (2, 'Cliente 2 - Agência B');
insert into agencia_b.cliente values (3, 'Cliente 3 - Agência B');