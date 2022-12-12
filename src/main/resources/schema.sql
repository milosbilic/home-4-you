CREATE TABLE IF NOT EXISTS users
(
    id bigint NOT NULL PRIMARY KEY,
    email character varying(255) NOT NULL UNIQUE,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    phone character varying(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS roles
(
    id bigint NOT NULL PRIMARY KEY,
    role character varying(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id bigint NOT NULL REFERENCES users (id),
    role_id bigint NOT NULL REFERENCES roles (id),
    CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id)
);

CREATE TABLE IF NOT EXISTS privileges
(
    id bigint NOT NULL PRIMARY KEY,
    name character varying(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS roles_privileges
(
    role_id bigint NOT NULL REFERENCES roles (id),
    privilege_id bigint NOT NULL REFERENCES privileges (id)
);

CREATE TABLE IF NOT EXISTS locations
(
    id bigint NOT NULL PRIMARY KEY,
    name character varying(255) NOT NULL,
    zip_code character varying(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS properties
(
    id bigint NOT NULL PRIMARY KEY,
    area double precision NOT NULL,
    booked boolean NOT NULL,
    heat_type character varying(255),
    image oid,
    number_of_rooms double precision,
    location_id bigint NOT NULL REFERENCES locations (id)
);

CREATE TABLE IF NOT EXISTS apartments
(
    id bigint PRIMARY KEY,
    floor integer NOT NULL,
    property_id bigint NOT NULL REFERENCES properties (id)
);

CREATE TABLE IF NOT EXISTS houses
(
    id bigint NOT NULL PRIMARY KEY,
    court_yard_area integer NOT NULL,
    number_of_floors integer NOT NULL,
    property_id bigint NOT NULL REFERENCES properties (id)
);

CREATE TABLE IF NOT EXISTS ads
(
    id bigint NOT NULL PRIMARY KEY,
    date_created timestamp(6) without time zone,
    description character varying(255),
    expiration_date date,
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    type character varying(255) COLLATE pg_catalog."default",
    property_id bigint NOT NULL REFERENCES properties (id),
    user_id bigint NOT NULL REFERENCES users (id),
    price numeric(38,2)
);
