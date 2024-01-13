CREATE OR REPLACE FUNCTION general_audit()
    RETURNS TRIGGER
AS $$
DECLARE
    row RECORD;
BEGIN
    IF tg_op = 'INSERT'
    THEN
        row := new;
    ELSEIF tg_op = 'UPDATE'
    THEN
        row := new;
    ELSIF tg_op = 'DELETE'
    THEN
        row := old;
    END IF;

    EXECUTE format('INSERT INTO %I VALUES ($1,($2).*)', tg_table_name || '_history')
        USING tg_op, row;
    RETURN row;
END;
$$
    LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION create_history_table_and_audit_trigger(
    table_name TEXT
)
    RETURNS VOID
AS $$
DECLARE
    history_table        TEXT := table_name || '_history';
    trigger_name         TEXT := table_name || '_audit';
    create_history_table TEXT := 'CREATE TABLE ' || history_table || ' AS (SELECT ''''::VARCHAR(10) AS action, * FROM ' || table_name || ') WITH NO DATA;';
    create_trigger       TEXT := 'CREATE TRIGGER ' || trigger_name || ' BEFORE INSERT OR UPDATE OR DELETE ON ' || table_name ||
                                 ' FOR EACH ROW EXECUTE PROCEDURE general_audit();';
BEGIN
    EXECUTE create_history_table;
    EXECUTE create_trigger;
END;
$$
    LANGUAGE plpgsql;