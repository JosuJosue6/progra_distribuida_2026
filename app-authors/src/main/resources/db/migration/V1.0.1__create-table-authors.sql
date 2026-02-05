--
-- PostgreSQL database dump

CREATE TABLE public.authors
(
    version integer NOT NULL,
    id      bigint  NOT NULL,
    name    character varying(255)
);


ALTER TABLE public.authors OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 42713)
-- Name: authors_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.authors_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE CACHE 1;


ALTER SEQUENCE public.authors_seq OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 42721)
-- Name: books; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.books
(
    price   numeric(8, 2)          NOT NULL,
    version integer                NOT NULL,
    isbn    character varying(255) NOT NULL,
    title   character varying(255)
);


ALTER TABLE public.books OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 42728)
-- Name: books_authors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.books_authors
(
    authors_id bigint                 NOT NULL,
    books_isbn character varying(255) NOT NULL
);


ALTER TABLE public.books_authors OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 42731)
-- Name: customers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customers
(
    version integer NOT NULL,
    id      bigint  NOT NULL,
    email   character varying(255),
    name    character varying(255)
);


ALTER TABLE public.customers OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 42714)
-- Name: customers_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customers_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE CACHE 1;


ALTER SEQUENCE public.customers_seq OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 115815)
-- Name: ids_generados; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ids_generados
(
    sigid bigint,
    tabla character varying(255) NOT NULL
);


ALTER TABLE public.ids_generados OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 42738)
-- Name: inventory; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inventory
(
    sold      integer                NOT NULL,
    supplied  integer                NOT NULL,
    version   integer                NOT NULL,
    book_isbn character varying(255) NOT NULL
);


ALTER TABLE public.inventory OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 42743)
-- Name: line_items; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.line_items
(
    id        integer                NOT NULL,
    quantity  integer                NOT NULL,
    order_id  bigint                 NOT NULL,
    book_isbn character varying(255) NOT NULL
);


ALTER TABLE public.line_items OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 42748)
-- Name: purchase_orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.purchase_orders
(
    status       smallint,
    total        integer NOT NULL,
    customer_id  bigint  NOT NULL,
    delivered_on timestamp(6) without time zone,
    id           bigint  NOT NULL,
    placed_on    timestamp(6) without time zone,
    CONSTRAINT purchase_orders_status_check CHECK (((status >= 0) AND (status <= 1)))
);


ALTER TABLE public.purchase_orders OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 42715)
-- Name: purchase_orders_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.purchase_orders_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE CACHE 1;


ALTER SEQUENCE public.purchase_orders_seq OWNER TO postgres;

SELECT pg_catalog.setval('public.authors_seq', 1, false);


--
-- TOC entry 5024 (class 0 OID 0)
-- Dependencies: 218
-- Name: customers_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customers_seq', 1, false);


--
-- TOC entry 5025 (class 0 OID 0)
-- Dependencies: 219
-- Name: purchase_orders_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.purchase_orders_seq', 1, false);


--
-- TOC entry 4843 (class 2606 OID 42720)
-- Name: authors authors_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authors
    ADD CONSTRAINT authors_pkey PRIMARY KEY (id);


--
-- TOC entry 4845 (class 2606 OID 42727)
-- Name: books books_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (isbn);


--
-- TOC entry 4847 (class 2606 OID 42737)
-- Name: customers customers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);


--
-- TOC entry 4855 (class 2606 OID 115819)
-- Name: ids_generados ids_generados_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ids_generados
    ADD CONSTRAINT ids_generados_pkey PRIMARY KEY (tabla);


--
-- TOC entry 4849 (class 2606 OID 42742)
-- Name: inventory inventory_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inventory
    ADD CONSTRAINT inventory_pkey PRIMARY KEY (book_isbn);


--
-- TOC entry 4851 (class 2606 OID 42747)
-- Name: line_items line_items_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.line_items
    ADD CONSTRAINT line_items_pkey PRIMARY KEY (id, order_id);


--
-- TOC entry 4853 (class 2606 OID 42753)
-- Name: purchase_orders purchase_orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase_orders
    ADD CONSTRAINT purchase_orders_pkey PRIMARY KEY (id);


--
-- TOC entry 4856 (class 2606 OID 42754)
-- Name: books_authors fk20menrngp9wi9at1dsu5cbb8o; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books_authors
    ADD CONSTRAINT fk20menrngp9wi9at1dsu5cbb8o FOREIGN KEY (authors_id) REFERENCES public.authors(id);


--
-- TOC entry 4859 (class 2606 OID 42774)
-- Name: line_items fk5faywgsy47v4g95fhhgkvdgxv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.line_items
    ADD CONSTRAINT fk5faywgsy47v4g95fhhgkvdgxv FOREIGN KEY (book_isbn) REFERENCES public.books(isbn);


--
-- TOC entry 4857 (class 2606 OID 42759)
-- Name: books_authors fk638vl9wfdvpll0nigsx8hvkf8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books_authors
    ADD CONSTRAINT fk638vl9wfdvpll0nigsx8hvkf8 FOREIGN KEY (books_isbn) REFERENCES public.books(isbn);


--
-- TOC entry 4858 (class 2606 OID 42764)
-- Name: inventory fkdmhkf2qcda7uw8lu688jugu2s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inventory
    ADD CONSTRAINT fkdmhkf2qcda7uw8lu688jugu2s FOREIGN KEY (book_isbn) REFERENCES public.books(isbn);


--
-- TOC entry 4861 (class 2606 OID 42779)
-- Name: purchase_orders fkfbqmektiojpqhypnpog4uqk41; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchase_orders
    ADD CONSTRAINT fkfbqmektiojpqhypnpog4uqk41 FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- TOC entry 4860 (class 2606 OID 42769)
-- Name: line_items fkqxuqc9unf2dsoohy6tef6675b; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.line_items
    ADD CONSTRAINT fkqxuqc9unf2dsoohy6tef6675b FOREIGN KEY (order_id) REFERENCES public.purchase_orders(id);


-- Completed on 2026-01-12 08:11:15

--
-- PostgreSQL database dump complete
--

