--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3 (Debian 13.3-1.pgdg100+1)
-- Dumped by pg_dump version 13.3 (Debian 13.3-1.pgdg100+1)
SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP table if exists public.transaction;
--
-- Name: update_modified_column(); Type: FUNCTION; Schema: public; Owner: postgres
--

-- CREATE FUNCTION public.update_modified_column() RETURNS trigger
--     LANGUAGE plpgsql
--     AS $$
-- BEGIN
--     NEW.updated_at = now() at time zone 'utc';
-- RETURN NEW;
-- END;
-- $$;


-- ALTER FUNCTION public.update_modified_column() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: transaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transaction (
                                id serial,
                                account int NOT NULL,
                                date date NOT NULL,
                                description character varying(100) NOT NULL,
                                trans_type int NOT NULL,
                                amount double precision DEFAULT 0.00 NOT NULL,
                                sender int,
                                receiver int
);


ALTER TABLE public.transaction OWNER TO postgres;

--
-- PostgreSQL database dump complete
--
INSERT INTO public.transaction (id, account, date, description, trans_type, amount, sender, receiver) VALUES (0, 5, now(), 'withdraw from checking', 0, 48.43, DEFAULT, DEFAULT);
INSERT INTO public.transaction (id, account, date, description, trans_type, amount, sender, receiver) VALUES (1, 1, now(), 'deposit into checking', 1, 72.89, DEFAULT, DEFAULT);
INSERT INTO public.transaction (id, account, date, description, trans_type, amount, sender, receiver) VALUES (2, 2, now(), 'check deposit for checking', 2, 100.00, DEFAULT, DEFAULT);
INSERT INTO public.transaction (id, account, date, description, trans_type, amount, sender, receiver) VALUES (3, 3, now(), 'withdraw from checking', 0, 56.94, DEFAULT, DEFAULT);
INSERT INTO public.transaction (id, account, date, description, trans_type, amount, sender, receiver) VALUES (4, 4, now(), 'transfer from acct 5 to acct 6', 3, 2.30, 5, 6);