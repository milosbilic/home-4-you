INSERT INTO users(email, first_name, last_name, password, phone)
    VALUES ('milos.bilic1995@gmail.com', 'Milos', 'Bilic', 'password', '+38134456');

INSERT INTO roles VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles VALUES (2, 'ROLE_USER');

INSERT INTO users_roles VALUES (1, 1);

INSERT INTO locations VALUES (1, 'Belgrade', '11000');
INSERT INTO locations VALUES (2, 'Novi Sad', '21000');
INSERT INTO locations VALUES (3, 'Ruma', '24000');

INSERT INTO ads
    VALUES (1, 'RENT','Izdavanje stana u Beogradu' , 'Slobodan za izdavanje stan u Beogradu (Vozdovac)', null, 600, null, 1);
INSERT INTO ads
    VALUES (2,'RENT', 'Izdavanje stana u Novom Sadu','Slobodan za izdavanje stan u Novom Sadu (Grbavica)',null , 250, null, 1);
INSERT INTO ads
    VALUES (3,'SALE', 'Prodaja kuce - Ruma', 'Prodajem super kucu kod Borkovca', null, 180000, null, 1);

INSERT INTO properties VALUES (1, 42, true, 'ELECTRIC', null, 2, 1, 1);
INSERT INTO properties VALUES (2, 25, true, 'GAS', null, 1, 2, 2);
INSERT INTO properties VALUES (3, 215, true, 'GAS', null, 6, 3, 3);

INSERT INTO apartments VALUES (1, 2, 1);
INSERT INTO apartments VALUES (2, 5, 2);
INSERT INTO houses VALUES (1, 80, 1, 1);


