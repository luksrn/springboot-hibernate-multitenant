---Database admin

-- Schema: public

-- DROP SCHEMA public;
 

 
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

CREATE SEQUENCE hibernate_sequence start with 100;
