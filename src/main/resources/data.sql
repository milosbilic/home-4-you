INSERT INTO users VALUES (1, 'milos.bilic1995@gmail.com', 'Milos', 'Bilic', 'password', '+38134456');

INSERT INTO roles VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles VALUES (2, 'ROLE_USER');

INSERT INTO users_roles VALUES (1, 1);

INSERT INTO locations VALUES (1, 'Belgrade', '11000');
INSERT INTO locations VALUES (2, 'Novi Sad', '21000');
INSERT INTO locations VALUES (3, 'Ruma', '24000');

INSERT INTO properties VALUES (1, 42.0, true, 'ELECTRIC', null, 2, 1);
INSERT INTO properties VALUES (2, 25.0, true, 'GAS', null, 1, 2);
INSERT INTO properties VALUES (3, 215, true, 'GAS', null, 6, 3);

INSERT INTO apartments VALUES (1, 2, 1);
INSERT INTO apartments VALUES (2, 5, 2);
INSERT INTO houses VALUES (1, 80, 1, 1);

INSERT INTO ads VALUES (1, null, 'Slobodan za izdavanje stan u Beogradu (Vozdovac)', null, 'Izdavanje stana u Beogradu', 'RENT', 1, 1, 600);
INSERT INTO ads VALUES (2, null, 'Slobodan za izdavanje stan u Novom Sadu (Grbavica)', null, 'Izdavanje stana u Novom Sadu', 'RENT', 2, 1, 250);
INSERT INTO ads VALUES (3, null, 'Prodajem super kucu kod Borkovca', null, 'Prodaja kuce - Ruma', 'SALE', 3, 1, 180000);


