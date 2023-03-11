-- DB rollback for application version 0.0.1

DROP TABLE  users_roles;
DROP TABLE  roles_privileges;
DROP TABLE  privileges;
DROP TABLE  roles;
DROP TABLE  apartments;
DROP TABLE  houses;
DROP TABLE  property_equipment;
DROP TABLE  properties;
DROP TABLE  equipment;
DROP TABLE  locations;
DROP TABLE  ads;
DROP TABLE  users;

DELETE
FROM flyway_schema_history
WHERE version = '0.1.0';
