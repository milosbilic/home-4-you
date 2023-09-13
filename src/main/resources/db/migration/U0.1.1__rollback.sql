-- DB rollback for version 0.1.1

TRUNCATE roles_privileges;
TRUNCATE privileges;

DELETE
FROM flyway_schema_history
WHERE version = '0.1.1';