-- Add USER_DELETE privilege

INSERT INTO privileges (name)
    VALUES ('USER_DELETE');

INSERT INTO roles_privileges
    VALUES (1, 2);
INSERT INTO roles_privileges
    VALUES (2, 2);
