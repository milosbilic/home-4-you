-- Add expired column to ad table

ALTER TABLE ads
 ADD COLUMN expired BOOLEAN NOT NULL DEFAULT FALSE;
