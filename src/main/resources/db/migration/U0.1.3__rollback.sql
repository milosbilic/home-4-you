-- Rollback for DB migration 0.1.3

ALTER TABLE ads
 DROP COLUMN expired;

 DELETE
 FROM flyway_schema_history
 WHERE version = '0.1.3';
