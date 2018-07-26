SET GLOBAL event_scheduler = 1;
DROP EVENT IF EXISTS `check_if_expired`;
CREATE EVENT `check_if_expired` ON SCHEDULE
        EVERY 1 DAY
    ON COMPLETION NOT PRESERVE
    ENABLE
    DO
UPDATE ad SET ad.expired = 1 WHERE ad.expiration_date < NOW();