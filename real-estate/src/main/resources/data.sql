INSERT INTO role VALUES(1, 'ROLE_USER');
INSERT INTO role VALUES(2, 'ROLE_ADMIN');
INSERT INTO user(id, email, first_name, last_name, username, password, phone) 
VALUES(1, 'slog@advertising.com', 'Brana', 'Slog', 'slog', '12345', '0000');
INSERT INTO user(id, email, first_name, last_name, username, password, phone) 
VALUES(2, 'sone@advertising.com', 'Sone', 'Askraba', 'nenadee', 'moliiiim', '0001');
INSERT INTO user_role VALUES (1, 2);
INSERT INTO user_role VALUES (2, 1);
INSERT INTO equipment values(1, 'Internet');