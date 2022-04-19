begin transaction;

alter table account
    add updated_at timestamptz default (now() at time zone 'utc');

alter table account
    add created_at timestamptz default (now() at time zone 'utc');

CREATE OR REPLACE FUNCTION update_modified_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = now() at time zone 'utc';
RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_customer_modtime BEFORE UPDATE ON account FOR EACH ROW EXECUTE PROCEDURE  update_modified_column();

commit;
end transaction;