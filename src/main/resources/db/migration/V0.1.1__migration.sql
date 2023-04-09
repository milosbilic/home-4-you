-- Add AD_DELETE privilege

INSERT INTO privileges (name)
    VALUES ('AD_DELETE');

INSERT INTO roles_privileges
    VALUES (1, 1);
INSERT INTO roles_privileges
    VALUES (2, 1);
