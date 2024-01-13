DROP TABLE IF EXISTS session_details;
DROP TABLE IF EXISTS session_details_history;

CREATE TABLE IF NOT EXISTS session_details
(
    id                 BIGSERIAL PRIMARY KEY       NOT NULL,
    uid                VARCHAR(50)              NOT NULL,
    name               VARCHAR(500)             NOT NULL,
    agreement          VARCHAR(10)              NOT NULL,
    created_ts         TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);

SELECT create_history_table_and_audit_trigger('session_details');

