-- DB migration for application version 0.0.1

CREATE TABLE IF NOT EXISTS roles
(
  id SERIAL NOT NULL PRIMARY KEY,
  name character varying(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users
(
  id SERIAL PRIMARY KEY,
  email character varying(255) NOT NULL UNIQUE,
  first_name character varying(255) NOT NULL,
  last_name character varying(255) NOT NULL,
  password character varying(255) NULL,
  phone character varying(255) NULL UNIQUE,
  role_id bigint NOT NULL REFERENCES roles (id)
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
  expiration_date timestamp with time zone,
  price bigint,
  created_at timestamp with time zone NOT NULL,
  owner_id bigint NOT NULL REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS properties
(
  id SERIAL NOT NULL PRIMARY KEY,
  area integer NOT NULL,
  booked boolean NOT NULL,
  heat_type character varying(255),
  image oid,
  number_of_rooms decimal,
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

INSERT INTO roles(name)
    VALUES ('ROLE_ADMIN');
INSERT INTO roles(name)
    VALUES ('ROLE_USER');

INSERT INTO users(email, first_name, last_name, password, phone, role_id)
    VALUES ('milos.bilic1995@gmail.com', 'Milos', 'Bilic', 'password', '+38134456', 1);

INSERT INTO locations(name, zip_code)
    VALUES ('Belgrade', '11000');
INSERT INTO locations(name, zip_code)
    VALUES ('Novi Sad', '21000');
INSERT INTO locations(name, zip_code)
    VALUES ('Ruma', '24000');

INSERT INTO ads(type, title, description, expiration_date, price, created_at, owner_id)
    VALUES ('RENT','Izdavanje stana u Beogradu' , 'Slobodan za izdavanje stan u Beogradu (Vozdovac)', null, 600, current_timestamp, 1);
INSERT INTO ads(type, title, description, expiration_date, price, created_at, owner_id)
    VALUES ('RENT', 'Izdavanje stana u Novom Sadu','Slobodan za izdavanje stan u Novom Sadu (Grbavica)',null, 250, current_timestamp, 1);
INSERT INTO ads(type, title, description, expiration_date, price, created_at, owner_id)
    VALUES ('SALE', 'Prodaja kuce - Ruma', 'Prodajem super kucu kod Borkovca', null, 180000, current_timestamp, 1);

INSERT INTO properties(area, booked, heat_type, image, number_of_rooms, location_id, ad_id)
    VALUES (42, true, 'ELECTRIC', null, 2, 1, 1);
INSERT INTO properties(area, booked, heat_type, image, number_of_rooms, location_id, ad_id)
    VALUES (25, true, 'GAS', null, 1, 2, 2);
INSERT INTO properties(area, booked, heat_type, image, number_of_rooms, location_id, ad_id)
    VALUES (215, true, 'GAS', null, 6, 3, 3);

INSERT INTO apartments(floor, property_id)
    VALUES (2, 1);
INSERT INTO apartments(floor, property_id)
    VALUES (5, 2);
INSERT INTO houses(courtyard_area, number_of_floors, property_id)
    VALUES (80, 1, 1);

