CREATE DATABASE online_shop
	WITH
	OWNER = postgres
	ENCODING = 'UTF8'
	CONNECTION LIMIT = -1
	IS_TEMPLATE = False;

////////////////

CREATE TABLE public.roles
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name character(30) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.roles
    OWNER to postgres;

///////////////

CREATE TABLE public.users
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    uuid character(50) NOT NULL,
    email character(50) NOT NULL,
    phone character(13) NOT NULL,
    role bigint NOT NULL,
    login character(30) NOT NULL,
    password character(80) NOT NULL,
    active boolean NOT NULL,
    activation_code character(50) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_role FOREIGN KEY (role)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;

///////////////

CREATE TABLE public.companies
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 CACHE 1 ),
    name character(250) NOT NULL,
    address character(250),
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.companies
    OWNER to postgres;

///////////////

CREATE TABLE public.colors
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 CACHE 1 ),
    name character(100) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public." colors"
    OWNER to postgres;

///////////////

CREATE TABLE public.goods
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 CACHE 1 ),
    uuid character(50) NOT NULL,
    name character(150) NOT NULL,
    producer bigint NOT NULL,
    color bigint NOT NULL,
    description text NOT NULL,
    short_deskription character(250) NOT NULL,
    height integer,
    width integer,
    length integer,
    weight numeric(3),
    price numeric(2) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_producer_company FOREIGN KEY (producer)
        REFERENCES public.companies (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_color_color FOREIGN KEY (color)
        REFERENCES public.colors (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.goods
    OWNER to postgres;

/////////////

CREATE TABLE public.bucket
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 CACHE 1 ),
    "user" bigint NOT NULL,
    good bigint NOT NULL,
    quantity integer NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_user FOREIGN KEY ("user")
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_good FOREIGN KEY (good)
        REFERENCES public.goods (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.bucket
    OWNER to postgres;

/////////////

CREATE TABLE public.catalog
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 CACHE 1 ),
    parent bigint,
    uuid character(50) NOT NULL,
    name character(250) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.catalog
    OWNER to postgres;