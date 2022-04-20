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

DROP table if exists public.account;
--
-- Name: update_modified_column(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.update_modified_column() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.updated_at = now() at time zone 'utc';
RETURN NEW;
END;
$$;


ALTER FUNCTION public.update_modified_column() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account (
                                id serial,
                                full_name character varying(100) NOT NULL,
                                balance double precision DEFAULT 0.00 NOT NULL,
                                created_at timestamp with time zone DEFAULT timezone('utc'::text, now()),
                                updated_at timestamp with time zone DEFAULT timezone('utc'::text, now())
);


ALTER TABLE public.account OWNER TO postgres;

--
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--




--
-- Name: account account_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pk PRIMARY KEY (id);


--
-- Name: account update_customer_modtime; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER update_customer_modtime BEFORE UPDATE ON public.account FOR EACH ROW EXECUTE FUNCTION public.update_modified_column();

--
-- PostgreSQL database dump complete
--
INSERT INTO public.account (id, full_name, balance, created_at, updated_at) VALUES (1, 'dev', 1000, DEFAULT, DEFAULT);
INSERT INTO public.account (id, full_name, balance, created_at, updated_at) VALUES (2, 'owen', 11111, DEFAULT, DEFAULT);
INSERT INTO public.account (id, full_name, balance, created_at, updated_at) VALUES (3, 'jordan', 111111, DEFAULT, DEFAULT);
INSERT INTO public.account (id, full_name, balance, created_at, updated_at) VALUES (4, 'maxwell', 1, DEFAULT, DEFAULT);
INSERT INTO public.account (id, full_name, balance, created_at, updated_at) VALUES (5, 'ana', 1232322, DEFAULT, DEFAULT);

