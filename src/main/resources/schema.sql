--DROP TABLE IF EXISTS users_roles;
--DROP TABLE IF EXISTS roles_privileges;
--DROP TABLE IF EXISTS privileges;
--DROP TABLE IF EXISTS roles;
--DROP TABLE IF EXISTS apartments;
--DROP TABLE IF EXISTS houses;
--DROP TABLE IF EXISTS property_equipment;
--DROP TABLE IF EXISTS properties;
--DROP TABLE IF EXISTS equipment;
--DROP TABLE IF EXISTS locations;
--DROP TABLE IF EXISTS ads;
--DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users
(
  id SERIAL PRIMARY KEY,
  email character varying(255) NOT NULL UNIQUE,
  first_name character varying(255) NOT NULL,
  last_name character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  phone character varying(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS roles
(
  id SERIAL NOT NULL PRIMARY KEY,
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
  id SERIAL NOT NULL PRIMARY KEY,
  name character varying(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS roles_privileges
(
  role_id bigint NOT NULL REFERENCES roles (id),
  privilege_id bigint NOT NULL REFERENCES privileges (id)
);

CREATE TABLE IF NOT EXISTS locations
(
  id SERIAL NOT NULL PRIMARY KEY,
  name character varying(255) NOT NULL,
  zip_code character varying(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS ads
(
  id SERIAL PRIMARY KEY,
  type character varying(255) COLLATE pg_catalog."default",
  title character varying(255) COLLATE pg_catalog."default" NOT NULL,
  description character varying(255),
  expiration_date timestamp(6) without time zone,
  price bigint,
  created_at timestamp(6) without time zone,
  user_id bigint NOT NULL REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS properties
(
  id SERIAL NOT NULL PRIMARY KEY,
  area integer NOT NULL,
  booked boolean NOT NULL,
  heat_type character varying(255),
  image oid,
  number_of_rooms integer,
  location_id bigint NOT NULL REFERENCES locations (id),
  ad_id bigint NOT NULL REFERENCES ads (id)
);

CREATE TABLE IF NOT EXISTS property_equipment
(
    property_id bigint NOT NULL REFERENCES properties (id),
    equipment CHARACTER VARYING (256) NOT NULL
);

CREATE TABLE IF NOT EXISTS apartments
(
  id SERIAL PRIMARY KEY,
  floor integer NOT NULL,
  property_id bigint NOT NULL REFERENCES properties (id)
);

CREATE TABLE IF NOT EXISTS houses
(
  id BIGSERIAL PRIMARY KEY,
  courtyard_area integer NOT NULL,
  number_of_floors integer NOT NULL,
  property_id bigint NOT NULL REFERENCES properties (id)
);
