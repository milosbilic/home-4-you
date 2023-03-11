-- DB rollback for application version 0.0.1

DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS roles_privileges;
DROP TABLE IF EXISTS privileges;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS apartments;
DROP TABLE IF EXISTS houses;
DROP TABLE IF EXISTS property_equipment;
DROP TABLE IF EXISTS properties;
DROP TABLE IF EXISTS equipment;
DROP TABLE IF EXISTS locations;
DROP TABLE IF EXISTS ads;
DROP TABLE IF EXISTS users;

DELETE
FROM flyway_schema_history
WHERE version = '0.1.0';
