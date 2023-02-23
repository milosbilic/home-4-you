INSERT INTO users(email, first_name, last_name, password, phone)
    VALUES ('milos.bilic1995@gmail.com', 'Milos', 'Bilic', 'password', '+38134456');

INSERT INTO roles(role)
    VALUES ('ROLE_ADMIN');
INSERT INTO roles(role)
    VALUES ('ROLE_USER');

INSERT INTO users_roles
    VALUES (1, 1);

INSERT INTO locations(name, zip_code)
    VALUES ('Belgrade', '11000');
INSERT INTO locations(name, zip_code)
    VALUES ('Novi Sad', '21000');
INSERT INTO locations(name, zip_code)
    VALUES ('Ruma', '24000');

INSERT INTO ads(type, title, description, expiration_date, price, created_at, user_id)
    VALUES ('RENT','Izdavanje stana u Beogradu' , 'Slobodan za izdavanje stan u Beogradu (Vozdovac)', null, 600, null, 1);
INSERT INTO ads(type, title, description, expiration_date, price, created_at, user_id)
    VALUES ('RENT', 'Izdavanje stana u Novom Sadu','Slobodan za izdavanje stan u Novom Sadu (Grbavica)',null, 250, null, 1);
INSERT INTO ads(type, title, description, expiration_date, price, created_at, user_id)
    VALUES ('SALE', 'Prodaja kuce - Ruma', 'Prodajem super kucu kod Borkovca', null, 180000, null, 1);

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


