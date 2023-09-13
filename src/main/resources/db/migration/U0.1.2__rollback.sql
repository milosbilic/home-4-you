-- DB rollback for version 0.1.2

--todo this needs to be done correctly alongside the previous version's rollback.

DELETE
FROM flyway_schema_history
WHERE version = '0.1.2';