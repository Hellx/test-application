DROP TABLE IF EXISTS personal_sectors;
DROP TABLE IF EXISTS personal_sectors_history;

CREATE TABLE IF NOT EXISTS personal_sectors
(
    id                 BIGSERIAL PRIMARY KEY    NOT NULL,
    uid                VARCHAR(50)              NOT NULL,
    sector_id          BIGINT                   NOT NULL,
    created_ts         TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);


SELECT create_history_table_and_audit_trigger('personal_sectors');

