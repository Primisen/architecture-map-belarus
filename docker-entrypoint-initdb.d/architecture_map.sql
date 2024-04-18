--
-- PostgreSQL database dump
--

-- Dumped from database version 14.11 (Ubuntu 14.11-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.11 (Ubuntu 14.11-0ubuntu0.22.04.1)

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

--
-- Name: architecture_map; Type: SCHEMA; Schema: -; Owner: nadzeya2
--

CREATE SCHEMA architecture_map;


ALTER SCHEMA architecture_map OWNER TO nadzeya2;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: address; Type: TABLE; Schema: architecture_map; Owner: nadzeya2
--

CREATE TABLE architecture_map.address (
    id integer NOT NULL,
    locality character varying(20) NOT NULL,
    district character varying(20) NOT NULL,
    region character varying(20) NOT NULL,
    street character varying(20),
    house_number character varying(5)
);


ALTER TABLE architecture_map.address OWNER TO nadzeya2;

--
-- Name: address_id_seq; Type: SEQUENCE; Schema: architecture_map; Owner: nadzeya2
--

CREATE SEQUENCE architecture_map.address_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE architecture_map.address_id_seq OWNER TO nadzeya2;

--
-- Name: address_id_seq; Type: SEQUENCE OWNED BY; Schema: architecture_map; Owner: nadzeya2
--

ALTER SEQUENCE architecture_map.address_id_seq OWNED BY architecture_map.address.id;


--
-- Name: architect; Type: TABLE; Schema: architecture_map; Owner: nadzeya2
--

CREATE TABLE architecture_map.architect (
    id integer NOT NULL,
    name character varying(50) NOT NULL,
    surname character varying(50) NOT NULL,
    years_of_life character varying(50) NOT NULL,
    work_description text NOT NULL,
    middle_name character varying(50)
);


ALTER TABLE architecture_map.architect OWNER TO nadzeya2;

--
-- Name: architect_id_seq; Type: SEQUENCE; Schema: architecture_map; Owner: nadzeya2
--

CREATE SEQUENCE architecture_map.architect_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE architecture_map.architect_id_seq OWNER TO nadzeya2;

--
-- Name: architect_id_seq; Type: SEQUENCE OWNED BY; Schema: architecture_map; Owner: nadzeya2
--

ALTER SEQUENCE architecture_map.architect_id_seq OWNED BY architecture_map.architect.id;


--
-- Name: architect_image; Type: TABLE; Schema: architecture_map; Owner: nadzeya2
--

CREATE TABLE architecture_map.architect_image (
    image_id integer NOT NULL,
    architect_id integer NOT NULL
);


ALTER TABLE architecture_map.architect_image OWNER TO nadzeya2;

--
-- Name: architect_image_image_id_seq; Type: SEQUENCE; Schema: architecture_map; Owner: nadzeya2
--

CREATE SEQUENCE architecture_map.architect_image_image_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE architecture_map.architect_image_image_id_seq OWNER TO nadzeya2;

--
-- Name: architect_image_image_id_seq; Type: SEQUENCE OWNED BY; Schema: architecture_map; Owner: nadzeya2
--

ALTER SEQUENCE architecture_map.architect_image_image_id_seq OWNED BY architecture_map.architect_image.image_id;


--
-- Name: architectural_attribute; Type: TABLE; Schema: architecture_map; Owner: nadzeya2
--

CREATE TABLE architecture_map.architectural_attribute (
    id integer NOT NULL,
    name character varying(50) NOT NULL,
    description text NOT NULL
);


ALTER TABLE architecture_map.architectural_attribute OWNER TO nadzeya2;

--
-- Name: architectural_attribute_id_seq; Type: SEQUENCE; Schema: architecture_map; Owner: nadzeya2
--

CREATE SEQUENCE architecture_map.architectural_attribute_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE architecture_map.architectural_attribute_id_seq OWNER TO nadzeya2;

--
-- Name: architectural_attribute_id_seq; Type: SEQUENCE OWNED BY; Schema: architecture_map; Owner: nadzeya2
--

ALTER SEQUENCE architecture_map.architectural_attribute_id_seq OWNED BY architecture_map.architectural_attribute.id;


--
-- Name: architectural_attribute_image; Type: TABLE; Schema: architecture_map; Owner: nadzeya2
--

CREATE TABLE architecture_map.architectural_attribute_image (
    image_id integer NOT NULL,
    architectural_attribute_id integer NOT NULL
);


ALTER TABLE architecture_map.architectural_attribute_image OWNER TO nadzeya2;

--
-- Name: architectural_attribute_image_image_id_seq; Type: SEQUENCE; Schema: architecture_map; Owner: nadzeya2
--

CREATE SEQUENCE architecture_map.architectural_attribute_image_image_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE architecture_map.architectural_attribute_image_image_id_seq OWNER TO nadzeya2;

--
-- Name: architectural_attribute_image_image_id_seq; Type: SEQUENCE OWNED BY; Schema: architecture_map; Owner: nadzeya2
--

ALTER SEQUENCE architecture_map.architectural_attribute_image_image_id_seq OWNED BY architecture_map.architectural_attribute_image.image_id;


--
-- Name: architectural_style; Type: TABLE; Schema: architecture_map; Owner: nadzeya2
--

CREATE TABLE architecture_map.architectural_style (
    id integer NOT NULL,
    name character varying(40) NOT NULL,
    description text
);


ALTER TABLE architecture_map.architectural_style OWNER TO nadzeya2;

--
-- Name: architectural_style_architectural_attribute; Type: TABLE; Schema: architecture_map; Owner: nadzeya2
--

CREATE TABLE architecture_map.architectural_style_architectural_attribute (
    id integer NOT NULL,
    architectural_style_id integer NOT NULL,
    architectural_attribute_id integer NOT NULL
);


ALTER TABLE architecture_map.architectural_style_architectural_attribute OWNER TO nadzeya2;

--
-- Name: architectural_style_architectural_attribute_id_seq; Type: SEQUENCE; Schema: architecture_map; Owner: nadzeya2
--

CREATE SEQUENCE architecture_map.architectural_style_architectural_attribute_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE architecture_map.architectural_style_architectural_attribute_id_seq OWNER TO nadzeya2;

--
-- Name: architectural_style_architectural_attribute_id_seq; Type: SEQUENCE OWNED BY; Schema: architecture_map; Owner: nadzeya2
--

ALTER SEQUENCE architecture_map.architectural_style_architectural_attribute_id_seq OWNED BY architecture_map.architectural_style_architectural_attribute.id;


--
-- Name: architectural_style_attribute; Type: TABLE; Schema: architecture_map; Owner: nadzeya2
--

CREATE TABLE architecture_map.architectural_style_attribute (
    id integer NOT NULL,
    architectural_style_id integer NOT NULL,
    attribute_id integer NOT NULL
);


ALTER TABLE architecture_map.architectural_style_attribute OWNER TO nadzeya2;

--
-- Name: architectural_style_attribute_id_seq; Type: SEQUENCE; Schema: architecture_map; Owner: nadzeya2
--

CREATE SEQUENCE architecture_map.architectural_style_attribute_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE architecture_map.architectural_style_attribute_id_seq OWNER TO nadzeya2;

--
-- Name: architectural_style_attribute_id_seq; Type: SEQUENCE OWNED BY; Schema: architecture_map; Owner: nadzeya2
--

ALTER SEQUENCE architecture_map.architectural_style_attribute_id_seq OWNED BY architecture_map.architectural_style_attribute.id;


--
-- Name: architectural_style_id_seq; Type: SEQUENCE; Schema: architecture_map; Owner: nadzeya2
--

CREATE SEQUENCE architecture_map.architectural_style_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE architecture_map.architectural_style_id_seq OWNER TO nadzeya2;

--
-- Name: architectural_style_id_seq; Type: SEQUENCE OWNED BY; Schema: architecture_map; Owner: nadzeya2
--

ALTER SEQUENCE architecture_map.architectural_style_id_seq OWNED BY architecture_map.architectural_style.id;


--
-- Name: attribute_of_architectural_style; Type: TABLE; Schema: architecture_map; Owner: nadzeya2
--

CREATE TABLE architecture_map.attribute_of_architectural_style (
    id integer NOT NULL,
    name character varying(50) NOT NULL,
    description text NOT NULL,
    url_to_image character varying(500) NOT NULL
);


ALTER TABLE architecture_map.attribute_of_architectural_style OWNER TO nadzeya2;

--
-- Name: attribute_of_architectural_style_id_seq; Type: SEQUENCE; Schema: architecture_map; Owner: nadzeya2
--

CREATE SEQUENCE architecture_map.attribute_of_architectural_style_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE architecture_map.attribute_of_architectural_style_id_seq OWNER TO nadzeya2;

--
-- Name: attribute_of_architectural_style_id_seq; Type: SEQUENCE OWNED BY; Schema: architecture_map; Owner: nadzeya2
--

ALTER SEQUENCE architecture_map.attribute_of_architectural_style_id_seq OWNED BY architecture_map.attribute_of_architectural_style.id;


--
-- Name: construction; Type: TABLE; Schema: architecture_map; Owner: nadzeya2
--

CREATE TABLE architecture_map.construction (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    address_id integer NOT NULL,
    architectural_style_id integer,
    building_time character varying(20),
    description text
);


ALTER TABLE architecture_map.construction OWNER TO nadzeya2;

--
-- Name: construction_architect; Type: TABLE; Schema: architecture_map; Owner: nadzeya2
--

CREATE TABLE architecture_map.construction_architect (
    id integer NOT NULL,
    construction_id integer NOT NULL,
    architect_id integer NOT NULL
);


ALTER TABLE architecture_map.construction_architect OWNER TO nadzeya2;

--
-- Name: construction_architect_id_seq; Type: SEQUENCE; Schema: architecture_map; Owner: nadzeya2
--

CREATE SEQUENCE architecture_map.construction_architect_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE architecture_map.construction_architect_id_seq OWNER TO nadzeya2;

--
-- Name: construction_architect_id_seq; Type: SEQUENCE OWNED BY; Schema: architecture_map; Owner: nadzeya2
--

ALTER SEQUENCE architecture_map.construction_architect_id_seq OWNED BY architecture_map.construction_architect.id;


--
-- Name: construction_id_seq; Type: SEQUENCE; Schema: architecture_map; Owner: nadzeya2
--

CREATE SEQUENCE architecture_map.construction_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE architecture_map.construction_id_seq OWNER TO nadzeya2;

--
-- Name: construction_id_seq; Type: SEQUENCE OWNED BY; Schema: architecture_map; Owner: nadzeya2
--

ALTER SEQUENCE architecture_map.construction_id_seq OWNED BY architecture_map.construction.id;


--
-- Name: construction_image; Type: TABLE; Schema: architecture_map; Owner: nadzeya2
--

CREATE TABLE architecture_map.construction_image (
    image_id integer NOT NULL,
    construction_id integer NOT NULL,
    taken_time character varying(100)
);


ALTER TABLE architecture_map.construction_image OWNER TO nadzeya2;

--
-- Name: construction_image_image_id_seq; Type: SEQUENCE; Schema: architecture_map; Owner: nadzeya2
--

CREATE SEQUENCE architecture_map.construction_image_image_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE architecture_map.construction_image_image_id_seq OWNER TO nadzeya2;

--
-- Name: construction_image_image_id_seq; Type: SEQUENCE OWNED BY; Schema: architecture_map; Owner: nadzeya2
--

ALTER SEQUENCE architecture_map.construction_image_image_id_seq OWNED BY architecture_map.construction_image.image_id;


--
-- Name: flyway_schema_history; Type: TABLE; Schema: architecture_map; Owner: nadzeya2
--

CREATE TABLE architecture_map.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE architecture_map.flyway_schema_history OWNER TO nadzeya2;

--
-- Name: image; Type: TABLE; Schema: architecture_map; Owner: nadzeya2
--

CREATE TABLE architecture_map.image (
    id integer NOT NULL,
    url character varying(1000) NOT NULL,
    show boolean DEFAULT true,
    source_id integer NOT NULL,
    author character varying(100)
);


ALTER TABLE architecture_map.image OWNER TO nadzeya2;

--
-- Name: image_id_seq; Type: SEQUENCE; Schema: architecture_map; Owner: nadzeya2
--

CREATE SEQUENCE architecture_map.image_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE architecture_map.image_id_seq OWNER TO nadzeya2;

--
-- Name: image_id_seq; Type: SEQUENCE OWNED BY; Schema: architecture_map; Owner: nadzeya2
--

ALTER SEQUENCE architecture_map.image_id_seq OWNED BY architecture_map.image.id;


--
-- Name: source; Type: TABLE; Schema: architecture_map; Owner: nadzeya2
--

CREATE TABLE architecture_map.source (
    id integer NOT NULL,
    name character varying(50) NOT NULL,
    url character varying(500) NOT NULL
);


ALTER TABLE architecture_map.source OWNER TO nadzeya2;

--
-- Name: source_id_seq; Type: SEQUENCE; Schema: architecture_map; Owner: nadzeya2
--

CREATE SEQUENCE architecture_map.source_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE architecture_map.source_id_seq OWNER TO nadzeya2;

--
-- Name: source_id_seq; Type: SEQUENCE OWNED BY; Schema: architecture_map; Owner: nadzeya2
--

ALTER SEQUENCE architecture_map.source_id_seq OWNED BY architecture_map.source.id;


--
-- Name: address id; Type: DEFAULT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.address ALTER COLUMN id SET DEFAULT nextval('architecture_map.address_id_seq'::regclass);


--
-- Name: architect id; Type: DEFAULT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.architect ALTER COLUMN id SET DEFAULT nextval('architecture_map.architect_id_seq'::regclass);


--
-- Name: architect_image image_id; Type: DEFAULT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.architect_image ALTER COLUMN image_id SET DEFAULT nextval('architecture_map.architect_image_image_id_seq'::regclass);


--
-- Name: architectural_attribute id; Type: DEFAULT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.architectural_attribute ALTER COLUMN id SET DEFAULT nextval('architecture_map.architectural_attribute_id_seq'::regclass);


--
-- Name: architectural_attribute_image image_id; Type: DEFAULT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.architectural_attribute_image ALTER COLUMN image_id SET DEFAULT nextval('architecture_map.architectural_attribute_image_image_id_seq'::regclass);


--
-- Name: architectural_style id; Type: DEFAULT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.architectural_style ALTER COLUMN id SET DEFAULT nextval('architecture_map.architectural_style_id_seq'::regclass);


--
-- Name: architectural_style_architectural_attribute id; Type: DEFAULT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.architectural_style_architectural_attribute ALTER COLUMN id SET DEFAULT nextval('architecture_map.architectural_style_architectural_attribute_id_seq'::regclass);


--
-- Name: architectural_style_attribute id; Type: DEFAULT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.architectural_style_attribute ALTER COLUMN id SET DEFAULT nextval('architecture_map.architectural_style_attribute_id_seq'::regclass);


--
-- Name: attribute_of_architectural_style id; Type: DEFAULT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.attribute_of_architectural_style ALTER COLUMN id SET DEFAULT nextval('architecture_map.attribute_of_architectural_style_id_seq'::regclass);


--
-- Name: construction id; Type: DEFAULT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.construction ALTER COLUMN id SET DEFAULT nextval('architecture_map.construction_id_seq'::regclass);


--
-- Name: construction_architect id; Type: DEFAULT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.construction_architect ALTER COLUMN id SET DEFAULT nextval('architecture_map.construction_architect_id_seq'::regclass);


--
-- Name: construction_image image_id; Type: DEFAULT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.construction_image ALTER COLUMN image_id SET DEFAULT nextval('architecture_map.construction_image_image_id_seq'::regclass);


--
-- Name: image id; Type: DEFAULT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.image ALTER COLUMN id SET DEFAULT nextval('architecture_map.image_id_seq'::regclass);


--
-- Name: source id; Type: DEFAULT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.source ALTER COLUMN id SET DEFAULT nextval('architecture_map.source_id_seq'::regclass);


--
-- Data for Name: address; Type: TABLE DATA; Schema: architecture_map; Owner: nadzeya2
--

COPY architecture_map.address (id, locality, district, region, street, house_number) FROM stdin;
1	г. Барысаў	Барысаўскі раён	Мінская вобласць		
2	г. Барысаў	Барысаўскі раён	Мінская вобласць		
3	г. Барысаў	Барысаўскі раён	Мінская вобласць		
4	Барысаў	Барысаўскі раён	Мінская вобласць		
5	Барысаў	Барысаўскі раён	Мінская вобласць		
6	Багушэвічы	Бярэзінскі раён	Мінская вобласць		
7	Альковічы	Вілейскі раён	Мінская вобласць		
8	Целядовічы	Капыльскі раён	Мінская вобласць		
9	Радашковічы	Маладзечанскі раён	Мінская вобласць		
10	Будслаў	Мядзельскі раён	Мінская вобласць		
11	Мінск	Мінскі раён	Мінская вобласць		
12	Мінск	Мінскі раён	Мінская вобласць		
13	Мінск	Мінскі раён	Мінская вобласць		
14	Мінск	Мінскі раён	Мінская вобласць		
15	Мінск	Мінскі раён	Мінская вобласць		
16	Мінск	Мінскі раён	Мінская вобласць		
17	Мінск	Мінскі раён	Мінская вобласць		
18	Мінск	Мінскі раён	Мінская вобласць		
19	Мінск	Мінскі раён	Мінская вобласць		
20	Мінск	Мінскі раён	Мінская вобласць		
21	Нясвіж	Нясвіжскі раён	Мінская вобласць		
22	Сноў	Нясвіжскі раён	Мінская вобласць		
23	Узда	Уздзенскі раён	Мінская вобласць		
\.


--
-- Data for Name: architect; Type: TABLE DATA; Schema: architecture_map; Owner: nadzeya2
--

COPY architecture_map.architect (id, name, surname, years_of_life, work_description, middle_name) FROM stdin;
14	Віктар	Анікін	1918 - 1997 гг	Працаваў у інстытуце “Белдзяржпраект” галоўным архітэктарам праектаў, затым узначаліў архітэктурна-планіровачную майстэрню, у 1962 г. атрымаў пасаду галоўнага архітэктара інстытута. Як самастойна, так і ў аўтарскім калектыве В.І. Анікін распрацаваў шэраг горадабудаўнічых праектаў. Гэта забудовы мікрараёнаў у Мінску: Серабранка, Вяснянка, па вуліцы Чкалава, а таксама падобныя праекты ў Светлогорску і Брэсце. Ён кіраваў распрацоўкай генеральных планаў абласных цэнтраў рэспублікі – Магілёва, Гродна, Брэста. Пры непасрэдным удзеле Віктара Іванавіча створаны такія конкурсныя і эксперыментальныя праекты жылых раёнаў, як Усход у Мінску, Паўднёвы ў Брэсце.	Іванавіч
13	Ігар	Есьман	1932 - 2020 гг	Аўтар тэхнічных вынаходстваў як "Пятля Есьмана", ганараванага трох залатых медалёў на міжнародных выставах у Жэневе (1994 г.), Бруселі (1995 г.) і Франкфурце-на-Майне (1996 г.), атрыманы Еўрапатэнт і патэнты ў многіх\\n краінах свету, у тым ліку Рэспубліцы Беларусь, ЗША, Аўстраліі і інш.Вынаходнік "Разружнага трансфармуемага столу Есьмана-Глеба", які атрымаў патэнты Рэспублікі Беларусь. Творчыя захапленні: Авіямадэлізм, авіяцыйны спорт. Астраномія. Разьба па камені, косці, дрэве, чаканка. Выраб мініяцюрных кніжак, інструментаў, мадэлей, памятных сувеніраў і аб`ёмных паштовак. Кампутарная графіка.	Іванавіч
\.


--
-- Data for Name: architect_image; Type: TABLE DATA; Schema: architecture_map; Owner: nadzeya2
--

COPY architecture_map.architect_image (image_id, architect_id) FROM stdin;
55	13
56	14
\.


--
-- Data for Name: architectural_attribute; Type: TABLE DATA; Schema: architecture_map; Owner: nadzeya2
--

COPY architecture_map.architectural_attribute (id, name, description) FROM stdin;
\.


--
-- Data for Name: architectural_attribute_image; Type: TABLE DATA; Schema: architecture_map; Owner: nadzeya2
--

COPY architecture_map.architectural_attribute_image (image_id, architectural_attribute_id) FROM stdin;
\.


--
-- Data for Name: architectural_style; Type: TABLE DATA; Schema: architecture_map; Owner: nadzeya2
--

COPY architecture_map.architectural_style (id, name, description) FROM stdin;
2	Драўлянае дойлідства	\N
3	Класіцызм	\N
4	Барока	\N
5	Готыка	\N
6	Мадэрнізм	\N
7	Канструктывізм	\N
8	Псеўдарускі	\N
9	Хай-тэк	\N
10	Постмадэрн	\N
1	Нявызначаны	\N
\.


--
-- Data for Name: architectural_style_architectural_attribute; Type: TABLE DATA; Schema: architecture_map; Owner: nadzeya2
--

COPY architecture_map.architectural_style_architectural_attribute (id, architectural_style_id, architectural_attribute_id) FROM stdin;
\.


--
-- Data for Name: architectural_style_attribute; Type: TABLE DATA; Schema: architecture_map; Owner: nadzeya2
--

COPY architecture_map.architectural_style_attribute (id, architectural_style_id, attribute_id) FROM stdin;
\.


--
-- Data for Name: attribute_of_architectural_style; Type: TABLE DATA; Schema: architecture_map; Owner: nadzeya2
--

COPY architecture_map.attribute_of_architectural_style (id, name, description, url_to_image) FROM stdin;
\.


--
-- Data for Name: construction; Type: TABLE DATA; Schema: architecture_map; Owner: nadzeya2
--

COPY architecture_map.construction (id, name, address_id, architectural_style_id, building_time, description) FROM stdin;
17	Мінск-арэна	18	1		
4	Дом Каладзеева	5	2		
21	Палац Рдултоўскіх	22	3		
20	Фарны касцёл у Нясвіжу	21	4		
7	Царква ў Целядовічах	8	2		
16	Кінатэатр "Кастрычнік"	17	6		
15	Корпус архітэктурнага факультэту БНТУ	16	6		
18	Палац спорту	19	6		
14	Жылыя дамы на вуліцы Веры Харужай	15	6		
19	Дом Кіраўніцтва	20	7		
5	Касцёл у Багушэвічах	6	5		
3	Сабор у Барысаве	4	8		
2	Стары горад у Барысаве	2	8		
1	Барысаў-арэна	1	9		
11	Будынак беларускай калійнай кампаніі	12	9		
10	Асацыяцыя мастацкай гімнастыкі	11	9		
12	Гасцініца Renaissance	13	9		
22	Капліца ва Уздзе	23	5		
8	Касцёл у Радашковічах	9	3		
9	Касцёл у Будславе	10	4		
13	ЖК "DEPO"	14	10		
6	Касцёл у Альковічах	7	5		
\.


--
-- Data for Name: construction_architect; Type: TABLE DATA; Schema: architecture_map; Owner: nadzeya2
--

COPY architecture_map.construction_architect (id, construction_id, architect_id) FROM stdin;
1	15	14
2	15	13
\.


--
-- Data for Name: construction_image; Type: TABLE DATA; Schema: architecture_map; Owner: nadzeya2
--

COPY architecture_map.construction_image (image_id, construction_id, taken_time) FROM stdin;
3	1	\N
4	1	\N
5	1	\N
6	1	\N
7	2	\N
8	3	\N
9	4	\N
10	5	\N
11	6	\N
12	7	\N
13	8	\N
14	9	\N
15	10	\N
16	10	\N
17	10	\N
18	10	\N
19	10	\N
20	10	\N
21	10	\N
22	11	\N
23	11	\N
24	12	\N
25	19	\N
26	13	\N
27	13	\N
28	13	\N
29	13	\N
30	13	\N
31	14	\N
32	15	\N
33	15	\N
34	15	\N
35	15	\N
36	16	\N
37	16	\N
38	18	\N
39	18	\N
40	18	\N
41	18	\N
42	18	\N
43	20	\N
44	20	\N
45	21	\N
46	21	\N
47	21	\N
48	21	\N
49	22	\N
50	22	\N
51	22	\N
52	22	\N
53	22	\N
54	22	\N
\.


--
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: architecture_map; Owner: nadzeya2
--

COPY architecture_map.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	202207181743	init db	SQL	V202207181743__init_db.sql	-1236656611	nadzeya2	2024-03-03 19:21:57.325315	16	t
2	202209141937	change url photo length	SQL	V202209141937__change_url_photo_length.sql	486115979	nadzeya2	2024-03-03 19:21:57.347626	0	t
3	202209141958	change address length	SQL	V202209141958__change_address_length.sql	-1193561359	nadzeya2	2024-03-03 19:21:57.352045	0	t
4	202210161518	change construction	SQL	V202210161518__change_construction.sql	-2127089039	nadzeya2	2024-03-03 19:21:57.356201	3	t
5	202211031308	adding visual type of photo	SQL	V202211031308__adding_visual_type_of_photo.sql	-1601801153	nadzeya2	2024-03-03 19:21:57.364699	3	t
6	202211241025	change lenght of visual type name	SQL	V202211241025__change_lenght_of_visual_type_name.sql	-1798259170	nadzeya2	2024-03-03 19:21:57.372268	0	t
7	202303071701	change length of architectural type	SQL	V202303071701__change_length_of_architectural_type.sql	1101758843	nadzeya2	2024-03-03 19:21:57.375958	0	t
8	202306141915	remove photo visual type table	SQL	V202306141915__remove_photo_visual_type_table.sql	-729543293	nadzeya2	2024-03-03 19:21:57.380062	1	t
9	202306141948	add architect table	SQL	V202306141948__add_architect_table.sql	-1318839460	nadzeya2	2024-03-03 19:21:57.383814	5	t
10	202306151211	adding attribute of architectural style table	SQL	V202306151211__adding_attribute_of_architectural_style_table.sql	-1818732549	nadzeya2	2024-03-03 19:21:57.393873	5	t
11	202306151247	adding many to many relationships	SQL	V202306151247__adding_many_to_many_relationships.sql	-854728076	nadzeya2	2024-03-03 19:21:57.402033	7	t
12	202306151503	adding architectural attribute table	SQL	V202306151503__adding_architectural_attribute_table.sql	-1238264966	nadzeya2	2024-03-03 19:21:57.413742	1	t
13	202306151547	cleaning	SQL	V202306151547__cleaning.sql	-721615169	nadzeya2	2024-03-03 19:21:57.417971	7	t
14	202306151605	adding description column	SQL	V202306151605__adding_description_column.sql	-342827572	nadzeya2	2024-03-03 19:21:57.427627	3	t
15	202307041709	image refactoring	SQL	V202307041709__image_refactoring.sql	157032020	nadzeya2	2024-03-03 19:21:57.433889	9	t
16	202307041729	try3	SQL	V202307041729__try3.sql	157032020	nadzeya2	2024-03-03 19:21:57.445715	9	t
17	202307041739	add description column	SQL	V202307041739__add_description_column.sql	-1390558086	nadzeya2	2024-03-03 19:21:57.45833	2	t
18	202307041749	changing architectural attribute table	SQL	V202307041749__changing_architectural_attribute_table.sql	1157798	nadzeya2	2024-03-03 19:21:57.463645	0	t
19	202307041800	drop table photo	SQL	V202307041800__drop_table_photo.sql	1199545110	nadzeya2	2024-03-03 19:21:57.467355	0	t
20	202307051011	change construction image table	SQL	V202307051011__change_construction_image_table.sql	-1004968397	nadzeya2	2024-03-03 19:21:57.471662	0	t
21	202307111910	architect table changing	SQL	V202307111910__architect_table_changing.sql	-1788158455	nadzeya2	2024-03-03 19:21:57.47553	0	t
22	202310021242	change construction name length	SQL	V202310021242__change_construction_name_length.sql	-986939788	nadzeya2	2024-03-03 19:21:57.479487	0	t
23	202310041706	adding author column	SQL	V202310041706__adding_author_column.sql	-1313956689	nadzeya2	2024-03-03 19:21:57.48348	0	t
24	202403051516	add architect image table	SQL	V202403051516__add_architect_image_table.sql	923926122	nadzeya2	2024-03-05 15:25:14.823381	19	t
\.


--
-- Data for Name: image; Type: TABLE DATA; Schema: architecture_map; Owner: nadzeya2
--

COPY architecture_map.image (id, url, show, source_id, author) FROM stdin;
5	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%91%D0%B0%D1%80%D1%8B%D1%81%D0%B0%D1%9E%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%91%D0%B0%D1%80%D1%8B%D1%81%D0%B0%D1%9E/%D0%91%D0%B0%D1%80%D1%8B%D1%81%D0%B0%D1%9E-%D0%B0%D1%80%D1%8D%D0%BD%D0%B0/%D1%84%D0%BE%D1%82%D0%BE%D0%B1%D1%80%D0%BE%D0%B4%D0%B8%D0%BB%D0%BA%D0%B8.jpg	t	2	\N
6	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%91%D0%B0%D1%80%D1%8B%D1%81%D0%B0%D1%9E%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%91%D0%B0%D1%80%D1%8B%D1%81%D0%B0%D1%9E/%D0%91%D0%B0%D1%80%D1%8B%D1%81%D0%B0%D1%9E-%D0%B0%D1%80%D1%8D%D0%BD%D0%B0/projects_borisovarena_1-8df5281e.jpeg	t	2	\N
7	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%91%D0%B0%D1%80%D1%8B%D1%81%D0%B0%D1%9E%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%91%D0%B0%D1%80%D1%8B%D1%81%D0%B0%D1%9E/%D0%A1%D1%82%D0%B0%D1%80%D1%8B%20%D0%B3%D0%BE%D1%80%D0%B0%D0%B4/%D1%84%D0%BE%D1%82%D0%BE%D0%B1%D1%80%D0%BE%D0%B4%D0%B8%D0%BB%D0%BA%D0%B8.jpg	t	2	\N
8	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%91%D0%B0%D1%80%D1%8B%D1%81%D0%B0%D1%9E%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%91%D0%B0%D1%80%D1%8B%D1%81%D0%B0%D1%9E/%D0%91%D0%B0%D1%80%D1%8B%D1%81%D0%B0%D1%9E%D1%81%D0%BA%D1%96%20%D0%92%D0%B0%D1%81%D0%BA%D1%80%D0%B0%D1%81%D0%B5%D0%BD%D1%81%D0%BA%D1%96%20%D0%A1%D0%B0%D0%B1%D0%BE%D1%80/%D1%84%D0%BE%D1%82%D0%BE%D0%B1%D1%80%D0%BE%D0%B4%D0%B8%D0%BB%D0%BA%D0%B8.jpg	t	2	\N
9	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%91%D0%B0%D1%80%D1%8B%D1%81%D0%B0%D1%9E%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%91%D0%B0%D1%80%D1%8B%D1%81%D0%B0%D1%9E/%D0%94%D0%BE%D0%BC%20%D0%9A%D0%B0%D0%BB%D0%B0%D0%B4%D0%B7%D0%B5%D0%B5%D0%B2%D0%B0/%D1%84%D0%BE%D1%82%D0%BE%D0%B1%D1%80%D0%BE%D0%B4%D0%B8%D0%BB%D0%BA%D0%B8(2).jpg	t	2	\N
10	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%91%D1%8F%D1%80%D1%8D%D0%B7%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%91%D0%B0%D0%B3%D1%83%D1%88%D1%8D%D0%B2%D1%96%D1%87%D1%8B/%D0%9A%D0%B0%D1%81%D1%86%D1%91%D0%BB/fotobrodilki.jpg	t	2	\N
11	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%92%D1%96%D0%BB%D0%B5%D0%B9%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%90%D0%BB%D1%8C%D0%BA%D0%BE%D0%B2%D1%96%D1%87%D1%8B/%D0%9A%D0%B0%D1%81%D1%86%D1%91%D0%BB/%D1%84%D0%BE%D1%82%D0%BE%D0%B1%D1%80%D0%BE%D0%B4%D0%B8%D0%BB%D0%BA%D0%B8.jpg	t	2	\N
12	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9A%D0%B0%D0%BF%D1%8B%D0%BB%D1%8C%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%A6%D0%B5%D0%BB%D1%8F%D0%B4%D0%BE%D0%B2%D1%96%D1%87%D1%8B/vedaj.by.jpg	t	3	\N
13	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D0%B0%D0%BB%D0%B0%D0%B4%D0%B7%D0%B5%D1%87%D0%B0%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%A0%D0%B0%D0%B4%D0%B0%D1%88%D0%BA%D0%BE%D0%B2%D1%96%D1%87%D1%8B/vedaj.by.jpg	t	3	\N
14	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%8F%D0%B4%D0%B7%D0%B5%D0%BB%D1%8C%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%91%D1%83%D0%B4%D1%81%D0%BB%D0%B0%D1%9E/%D0%9A%D0%B0%D1%81%D1%86%D1%91%D0%BB/vedaj.by.jpg	t	3	\N
17	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%90%D1%81%D0%B0%D1%86%D1%8B%D1%8F%D1%86%D1%8B%D1%8F%20%D0%BC%D0%B0%D1%81%D1%82%D0%B0%D1%86%D0%BA%D0%B0%D0%B9%20%D0%B3%D1%96%D0%BC%D0%BD%D0%B0%D1%81%D1%82%D1%8B%D0%BA%D1%96/realt.onliner(3).jpeg	t	4	\N
18	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%90%D1%81%D0%B0%D1%86%D1%8B%D1%8F%D1%86%D1%8B%D1%8F%20%D0%BC%D0%B0%D1%81%D1%82%D0%B0%D1%86%D0%BA%D0%B0%D0%B9%20%D0%B3%D1%96%D0%BC%D0%BD%D0%B0%D1%81%D1%82%D1%8B%D0%BA%D1%96/realt.onliner(2).jpeg	t	4	\N
3	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%91%D0%B0%D1%80%D1%8B%D1%81%D0%B0%D1%9E%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%91%D0%B0%D1%80%D1%8B%D1%81%D0%B0%D1%9E/%D0%91%D0%B0%D1%80%D1%8B%D1%81%D0%B0%D1%9E-%D0%B0%D1%80%D1%8D%D0%BD%D0%B0/1871.jpg	f	1	\N
16	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%90%D1%81%D0%B0%D1%86%D1%8B%D1%8F%D1%86%D1%8B%D1%8F%20%D0%BC%D0%B0%D1%81%D1%82%D0%B0%D1%86%D0%BA%D0%B0%D0%B9%20%D0%B3%D1%96%D0%BC%D0%BD%D0%B0%D1%81%D1%82%D1%8B%D0%BA%D1%96/realt.onliner(1).jpeg	t	4	\N
15	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%90%D1%81%D0%B0%D1%86%D1%8B%D1%8F%D1%86%D1%8B%D1%8F%20%D0%BC%D0%B0%D1%81%D1%82%D0%B0%D1%86%D0%BA%D0%B0%D0%B9%20%D0%B3%D1%96%D0%BC%D0%BD%D0%B0%D1%81%D1%82%D1%8B%D0%BA%D1%96/realt.onliner(1).jpeg	f	4	\N
19	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%90%D1%81%D0%B0%D1%86%D1%8B%D1%8F%D1%86%D1%8B%D1%8F%20%D0%BC%D0%B0%D1%81%D1%82%D0%B0%D1%86%D0%BA%D0%B0%D0%B9%20%D0%B3%D1%96%D0%BC%D0%BD%D0%B0%D1%81%D1%82%D1%8B%D0%BA%D1%96/realt.onliner(4).jpeg	t	4	\N
20	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%90%D1%81%D0%B0%D1%86%D1%8B%D1%8F%D1%86%D1%8B%D1%8F%20%D0%BC%D0%B0%D1%81%D1%82%D0%B0%D1%86%D0%BA%D0%B0%D0%B9%20%D0%B3%D1%96%D0%BC%D0%BD%D0%B0%D1%81%D1%82%D1%8B%D0%BA%D1%96/realt.onliner(5).jpeg	t	4	\N
21	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%90%D1%81%D0%B0%D1%86%D1%8B%D1%8F%D1%86%D1%8B%D1%8F%20%D0%BC%D0%B0%D1%81%D1%82%D0%B0%D1%86%D0%BA%D0%B0%D0%B9%20%D0%B3%D1%96%D0%BC%D0%BD%D0%B0%D1%81%D1%82%D1%8B%D0%BA%D1%96/realt.onliner.jpeg	t	4	\N
23	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%91%D1%83%D0%B4%D1%8B%D0%BD%D0%B0%D0%BA%20%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D0%BA%D0%B0%D0%B9%20%D0%BA%D0%B0%D0%BB%D1%96%D0%B9%D0%BD%D0%B0%D0%B9%20%D0%BA%D0%B0%D0%BC%D0%BF%D0%B0%D0%BD%D1%96%D1%96/realt.onliner.by.jpeg	t	4	\N
24	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%93%D0%B0%D1%81%D1%86%D1%96%D0%BD%D1%96%D1%86%D0%B0%20Renaissance/realt.onliner.by.jpeg	t	4	\N
22	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%91%D1%83%D0%B4%D1%8B%D0%BD%D0%B0%D0%BA%20%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D0%BA%D0%B0%D0%B9%20%D0%BA%D0%B0%D0%BB%D1%96%D0%B9%D0%BD%D0%B0%D0%B9%20%D0%BA%D0%B0%D0%BC%D0%BF%D0%B0%D0%BD%D1%96%D1%96/realt.onliner.by(2).jpeg	t	4	\N
25	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%94%D0%BE%D0%BC%20%D0%BA%D1%96%D1%80%D0%B0%D1%9E%D0%BD%D1%96%D1%86%D1%82%D0%B2%D0%B0/wikipedia.jpg	t	5	\N
26	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%96%D0%9A%20%22Depo%22/realt.onliner(1).jpeg	t	4	\N
27	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%96%D0%9A%20%22Depo%22/realt.onliner(2).jpeg	t	4	\N
28	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%96%D0%9A%20%22Depo%22/realt.onliner(3).jpeg	t	4	\N
29	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%96%D0%9A%20%22Depo%22/realt.onliner(5).jpeg	t	4	\N
30	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%96%D0%9A%20%22Depo%22/realt.onliner.jpeg	t	4	\N
31	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%96%D1%8B%D0%BB%D1%8B%D1%8F%20%D0%B4%D0%B0%D0%BC%D1%8B%20%D0%BD%D0%B0%20%D0%B2%D1%83%D0%BB%D1%96%D1%86%D1%8B%20%D0%92%D0%B5%D1%80%D1%8B%20%D0%A5%D0%B0%D1%80%D1%83%D0%B6%D0%B0%D0%B9/realt.onliner.jpeg	t	4	\N
32	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%9A%D0%BE%D1%80%D0%BF%D1%83%D1%81%20%D0%B0%D1%80%D1%85%D1%96%D1%82%D1%8D%D0%BA%D1%82%D1%83%D1%80%D0%BD%D0%B0%D0%B3%D0%B0%20%D1%84%D0%B0%D0%BA%D1%83%D0%BB%D1%8C%D1%82%D1%8D%D1%82%D0%B0%20%D0%91%D0%9D%D0%A2%D0%A3/realt.onliner.jpeg	t	4	\N
33	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%9A%D0%BE%D1%80%D0%BF%D1%83%D1%81%20%D0%B0%D1%80%D1%85%D1%96%D1%82%D1%8D%D0%BA%D1%82%D1%83%D1%80%D0%BD%D0%B0%D0%B3%D0%B0%20%D1%84%D0%B0%D0%BA%D1%83%D0%BB%D1%8C%D1%82%D1%8D%D1%82%D0%B0%20%D0%91%D0%9D%D0%A2%D0%A3/%D0%9D%D0%B5%D0%B2%D1%8F%D0%B4%D0%BE%D0%BC%D1%8B%20%D0%B0%D1%9E%D1%82%D0%B0%D1%80.jpg	t	1	\N
34	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%9A%D0%BE%D1%80%D0%BF%D1%83%D1%81%20%D0%B0%D1%80%D1%85%D1%96%D1%82%D1%8D%D0%BA%D1%82%D1%83%D1%80%D0%BD%D0%B0%D0%B3%D0%B0%20%D1%84%D0%B0%D0%BA%D1%83%D0%BB%D1%8C%D1%82%D1%8D%D1%82%D0%B0%20%D0%91%D0%9D%D0%A2%D0%A3/%D0%9D%D0%B5%D0%B2%D1%8F%D0%B4%D0%BE%D0%BC%D1%8B%20%D0%B0%D1%9E%D1%82%D0%B0%D1%80(1).jpg	t	1	\N
35	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%9A%D0%BE%D1%80%D0%BF%D1%83%D1%81%20%D0%B0%D1%80%D1%85%D1%96%D1%82%D1%8D%D0%BA%D1%82%D1%83%D1%80%D0%BD%D0%B0%D0%B3%D0%B0%20%D1%84%D0%B0%D0%BA%D1%83%D0%BB%D1%8C%D1%82%D1%8D%D1%82%D0%B0%20%D0%91%D0%9D%D0%A2%D0%A3/bnty.by.jpg	t	1	\N
36	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%9A%D1%96%D0%BD%D0%B0%D1%82%D1%8D%D0%B0%D1%82%D1%80%20%22%D0%9A%D0%B0%D1%81%D1%82%D1%80%D1%8B%D1%87%D0%BD%D1%96%D0%BA%22/realt.onliner(1).jpeg	t	4	\N
37	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%9A%D1%96%D0%BD%D0%B0%D1%82%D1%8D%D0%B0%D1%82%D1%80%20%22%D0%9A%D0%B0%D1%81%D1%82%D1%80%D1%8B%D1%87%D0%BD%D1%96%D0%BA%22/realt.onliner.jpeg	t	4	\N
39	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%9F%D0%B0%D0%BB%D0%B0%D1%86%20%D1%81%D0%BF%D0%BE%D1%80%D1%82%D1%83/realt.onliner(1).jpeg	t	4	\N
40	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%9F%D0%B0%D0%BB%D0%B0%D1%86%20%D1%81%D0%BF%D0%BE%D1%80%D1%82%D1%83/realt.onliner(2).jpeg	t	4	\N
41	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%9F%D0%B0%D0%BB%D0%B0%D1%86%20%D1%81%D0%BF%D0%BE%D1%80%D1%82%D1%83/realt.onliner(3).jpeg	t	4	\N
42	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%9F%D0%B0%D0%BB%D0%B0%D1%86%20%D1%81%D0%BF%D0%BE%D1%80%D1%82%D1%83/realt.onliner.jpeg	t	4	\N
46	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9D%D1%8F%D1%81%D0%B2%D1%96%D0%B6%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%A1%D0%BD%D0%BE%D1%9E/%D0%9F%D0%B0%D0%BB%D0%B0%D1%86%20%D0%A0%D0%B4%D1%83%D0%BB%D1%82%D0%BE%D1%9E%D1%81%D0%BA%D1%96%D1%85/vedaj.by(2).jpg	t	3	\N
48	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9D%D1%8F%D1%81%D0%B2%D1%96%D0%B6%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%A1%D0%BD%D0%BE%D1%9E/%D0%9F%D0%B0%D0%BB%D0%B0%D1%86%20%D0%A0%D0%B4%D1%83%D0%BB%D1%82%D0%BE%D1%9E%D1%81%D0%BA%D1%96%D1%85/fotobel.by.jpg	t	6	\N
49	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%A3%D0%B7%D0%B4%D0%B7%D0%B5%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%A3%D0%B7%D0%B4%D0%B0/planetabelarus(2).jpg	t	7	\N
52	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%A3%D0%B7%D0%B4%D0%B7%D0%B5%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%A3%D0%B7%D0%B4%D0%B0/poshyk-info(3).jpg	t	8	\N
43	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9D%D1%8F%D1%81%D0%B2%D1%96%D0%B6%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9D%D1%8F%D1%81%D0%B2%D1%96%D0%B6/%D0%A4%D0%B0%D1%80%D0%BD%D1%8B%20%D0%BA%D0%B0%D1%81%D1%86%D1%91%D0%BB/wiki(2).jpg	t	5	\N
51	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%A3%D0%B7%D0%B4%D0%B7%D0%B5%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%A3%D0%B7%D0%B4%D0%B0/poshyk-info(2).jpg	t	8	\N
53	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%A3%D0%B7%D0%B4%D0%B7%D0%B5%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%A3%D0%B7%D0%B4%D0%B0/poshyk-info(4).jpg	t	8	\N
44	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9D%D1%8F%D1%81%D0%B2%D1%96%D0%B6%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9D%D1%8F%D1%81%D0%B2%D1%96%D0%B6/%D0%A4%D0%B0%D1%80%D0%BD%D1%8B%20%D0%BA%D0%B0%D1%81%D1%86%D1%91%D0%BB/wiki.jpg	t	5	\N
47	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9D%D1%8F%D1%81%D0%B2%D1%96%D0%B6%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%A1%D0%BD%D0%BE%D1%9E/%D0%9F%D0%B0%D0%BB%D0%B0%D1%86%20%D0%A0%D0%B4%D1%83%D0%BB%D1%82%D0%BE%D1%9E%D1%81%D0%BA%D1%96%D1%85/vedaj.by(1).jpg	t	3	\N
54	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%A3%D0%B7%D0%B4%D0%B7%D0%B5%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%A3%D0%B7%D0%B4%D0%B0/poshyk-info.jpg	t	8	\N
45	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9D%D1%8F%D1%81%D0%B2%D1%96%D0%B6%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%A1%D0%BD%D0%BE%D1%9E/%D0%9F%D0%B0%D0%BB%D0%B0%D1%86%20%D0%A0%D0%B4%D1%83%D0%BB%D1%82%D0%BE%D1%9E%D1%81%D0%BA%D1%96%D1%85/vedaj.by.jpg	t	3	\N
50	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%A3%D0%B7%D0%B4%D0%B7%D0%B5%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%A3%D0%B7%D0%B4%D0%B0/planetabelarus.jpg	t	7	\N
4	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%91%D0%B0%D1%80%D1%8B%D1%81%D0%B0%D1%9E%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%91%D0%B0%D1%80%D1%8B%D1%81%D0%B0%D1%9E/%D0%91%D0%B0%D1%80%D1%8B%D1%81%D0%B0%D1%9E-%D0%B0%D1%80%D1%8D%D0%BD%D0%B0/1871.jpg	t	1	\N
38	https://storage.googleapis.com/architecture-map-photos/%D0%91%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%B2%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%86%D1%8C/%D0%9C%D1%96%D0%BD%D1%81%D0%BA%D1%96%20%D1%80%D0%B0%D1%91%D0%BD/%D0%9C%D1%96%D0%BD%D1%81%D0%BA/%D0%9F%D0%B0%D0%BB%D0%B0%D1%86%20%D1%81%D0%BF%D0%BE%D1%80%D1%82%D1%83/realt.onliner(1).jpeg	f	4	\N
55	https://storage.googleapis.com/architecture-map-photos/%D0%90%D1%80%D1%85%D1%96%D1%82%D1%8D%D0%BA%D1%82%D0%B0%D1%80%D1%8B/bsa.by_%D0%95%D1%81%D1%8C%D0%BC%D0%B0%D0%BD_%D0%86%D0%B3%D0%B0%D1%80.jpg	t	9	\N
56	https://storage.googleapis.com/architecture-map-photos/%D0%90%D1%80%D1%85%D1%96%D1%82%D1%8D%D0%BA%D1%82%D0%B0%D1%80%D1%8B/nlb.by_Anikin_Viktar.jpg	t	10	\N
\.


--
-- Data for Name: source; Type: TABLE DATA; Schema: architecture_map; Owner: nadzeya2
--

COPY architecture_map.source (id, name, url) FROM stdin;
1	Невядомы аўтар	Невядомы аўтар
2	Фотобродилки	https://fotobrodilki.by/
3	Vedaj.by	https://vedaj.by/
4	realt.onliner	https://realt.onliner.by/
5	Wikipedia	https://www.wikipedia.org/
6	Фотоэнциклопедия Беларуси	http://www.fotobel.by/
7	Planeta Belarus	https://planetabelarus.by
8	Poshyk.info	https://poshyk.info/
9	Беларускі саюз архітэктараў	http://bsa.by/bsa
10	Беларусь у асобах і падзеях	https://bis.nlb.by/by
\.


--
-- Name: address_id_seq; Type: SEQUENCE SET; Schema: architecture_map; Owner: nadzeya2
--

SELECT pg_catalog.setval('architecture_map.address_id_seq', 23, true);


--
-- Name: architect_id_seq; Type: SEQUENCE SET; Schema: architecture_map; Owner: nadzeya2
--

SELECT pg_catalog.setval('architecture_map.architect_id_seq', 14, true);


--
-- Name: architect_image_image_id_seq; Type: SEQUENCE SET; Schema: architecture_map; Owner: nadzeya2
--

SELECT pg_catalog.setval('architecture_map.architect_image_image_id_seq', 1, false);


--
-- Name: architectural_attribute_id_seq; Type: SEQUENCE SET; Schema: architecture_map; Owner: nadzeya2
--

SELECT pg_catalog.setval('architecture_map.architectural_attribute_id_seq', 1, false);


--
-- Name: architectural_attribute_image_image_id_seq; Type: SEQUENCE SET; Schema: architecture_map; Owner: nadzeya2
--

SELECT pg_catalog.setval('architecture_map.architectural_attribute_image_image_id_seq', 1, false);


--
-- Name: architectural_style_architectural_attribute_id_seq; Type: SEQUENCE SET; Schema: architecture_map; Owner: nadzeya2
--

SELECT pg_catalog.setval('architecture_map.architectural_style_architectural_attribute_id_seq', 1, false);


--
-- Name: architectural_style_attribute_id_seq; Type: SEQUENCE SET; Schema: architecture_map; Owner: nadzeya2
--

SELECT pg_catalog.setval('architecture_map.architectural_style_attribute_id_seq', 1, false);


--
-- Name: architectural_style_id_seq; Type: SEQUENCE SET; Schema: architecture_map; Owner: nadzeya2
--

SELECT pg_catalog.setval('architecture_map.architectural_style_id_seq', 10, true);


--
-- Name: attribute_of_architectural_style_id_seq; Type: SEQUENCE SET; Schema: architecture_map; Owner: nadzeya2
--

SELECT pg_catalog.setval('architecture_map.attribute_of_architectural_style_id_seq', 1, false);


--
-- Name: construction_architect_id_seq; Type: SEQUENCE SET; Schema: architecture_map; Owner: nadzeya2
--

SELECT pg_catalog.setval('architecture_map.construction_architect_id_seq', 2, true);


--
-- Name: construction_id_seq; Type: SEQUENCE SET; Schema: architecture_map; Owner: nadzeya2
--

SELECT pg_catalog.setval('architecture_map.construction_id_seq', 22, true);


--
-- Name: construction_image_image_id_seq; Type: SEQUENCE SET; Schema: architecture_map; Owner: nadzeya2
--

SELECT pg_catalog.setval('architecture_map.construction_image_image_id_seq', 1, false);


--
-- Name: image_id_seq; Type: SEQUENCE SET; Schema: architecture_map; Owner: nadzeya2
--

SELECT pg_catalog.setval('architecture_map.image_id_seq', 56, true);


--
-- Name: source_id_seq; Type: SEQUENCE SET; Schema: architecture_map; Owner: nadzeya2
--

SELECT pg_catalog.setval('architecture_map.source_id_seq', 10, true);


--
-- Name: address address_pkey; Type: CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- Name: architect_image architect_image_pkey; Type: CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.architect_image
    ADD CONSTRAINT architect_image_pkey PRIMARY KEY (image_id);


--
-- Name: architect architect_pkey; Type: CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.architect
    ADD CONSTRAINT architect_pkey PRIMARY KEY (id);


--
-- Name: architectural_attribute_image architectural_attribute_image_pkey; Type: CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.architectural_attribute_image
    ADD CONSTRAINT architectural_attribute_image_pkey PRIMARY KEY (image_id);


--
-- Name: architectural_attribute architectural_attribute_pkey; Type: CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.architectural_attribute
    ADD CONSTRAINT architectural_attribute_pkey PRIMARY KEY (id);


--
-- Name: architectural_style_architectural_attribute architectural_style_architectural_attribute_pkey; Type: CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.architectural_style_architectural_attribute
    ADD CONSTRAINT architectural_style_architectural_attribute_pkey PRIMARY KEY (id);


--
-- Name: architectural_style_attribute architectural_style_attribute_pkey; Type: CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.architectural_style_attribute
    ADD CONSTRAINT architectural_style_attribute_pkey PRIMARY KEY (id);


--
-- Name: architectural_style architectural_style_pkey; Type: CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.architectural_style
    ADD CONSTRAINT architectural_style_pkey PRIMARY KEY (id);


--
-- Name: attribute_of_architectural_style attribute_of_architectural_style_pkey; Type: CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.attribute_of_architectural_style
    ADD CONSTRAINT attribute_of_architectural_style_pkey PRIMARY KEY (id);


--
-- Name: construction_architect construction_architect_pkey; Type: CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.construction_architect
    ADD CONSTRAINT construction_architect_pkey PRIMARY KEY (id);


--
-- Name: construction_image construction_image_pkey; Type: CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.construction_image
    ADD CONSTRAINT construction_image_pkey PRIMARY KEY (image_id);


--
-- Name: construction construction_pkey; Type: CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.construction
    ADD CONSTRAINT construction_pkey PRIMARY KEY (id);


--
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- Name: image image_pkey; Type: CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.image
    ADD CONSTRAINT image_pkey PRIMARY KEY (id);


--
-- Name: source source_pkey; Type: CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.source
    ADD CONSTRAINT source_pkey PRIMARY KEY (id);


--
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: architecture_map; Owner: nadzeya2
--

CREATE INDEX flyway_schema_history_s_idx ON architecture_map.flyway_schema_history USING btree (success);


--
-- Name: architect_image architect_image_architect_id_fkey; Type: FK CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.architect_image
    ADD CONSTRAINT architect_image_architect_id_fkey FOREIGN KEY (architect_id) REFERENCES architecture_map.architect(id);


--
-- Name: architect_image architect_image_image_id_fkey; Type: FK CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.architect_image
    ADD CONSTRAINT architect_image_image_id_fkey FOREIGN KEY (image_id) REFERENCES architecture_map.image(id);


--
-- Name: architectural_attribute_image architectural_attribute_image_architectural_attribute_id_fkey; Type: FK CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.architectural_attribute_image
    ADD CONSTRAINT architectural_attribute_image_architectural_attribute_id_fkey FOREIGN KEY (architectural_attribute_id) REFERENCES architecture_map.architectural_attribute(id);


--
-- Name: architectural_attribute_image architectural_attribute_image_image_id_fkey; Type: FK CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.architectural_attribute_image
    ADD CONSTRAINT architectural_attribute_image_image_id_fkey FOREIGN KEY (image_id) REFERENCES architecture_map.image(id);


--
-- Name: architectural_style_architectural_attribute architectural_style_architectur_architectural_attribute_id_fkey; Type: FK CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.architectural_style_architectural_attribute
    ADD CONSTRAINT architectural_style_architectur_architectural_attribute_id_fkey FOREIGN KEY (architectural_attribute_id) REFERENCES architecture_map.architectural_attribute(id);


--
-- Name: architectural_style_architectural_attribute architectural_style_architectural_a_architectural_style_id_fkey; Type: FK CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.architectural_style_architectural_attribute
    ADD CONSTRAINT architectural_style_architectural_a_architectural_style_id_fkey FOREIGN KEY (architectural_style_id) REFERENCES architecture_map.architectural_style(id);


--
-- Name: architectural_style_attribute architectural_style_attribute_architectural_style_id_fkey; Type: FK CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.architectural_style_attribute
    ADD CONSTRAINT architectural_style_attribute_architectural_style_id_fkey FOREIGN KEY (architectural_style_id) REFERENCES architecture_map.architectural_style(id);


--
-- Name: architectural_style_attribute architectural_style_attribute_attribute_id_fkey; Type: FK CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.architectural_style_attribute
    ADD CONSTRAINT architectural_style_attribute_attribute_id_fkey FOREIGN KEY (attribute_id) REFERENCES architecture_map.attribute_of_architectural_style(id);


--
-- Name: construction construction_address_id_fkey; Type: FK CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.construction
    ADD CONSTRAINT construction_address_id_fkey FOREIGN KEY (address_id) REFERENCES architecture_map.address(id);


--
-- Name: construction_architect construction_architect_architect_id_fkey; Type: FK CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.construction_architect
    ADD CONSTRAINT construction_architect_architect_id_fkey FOREIGN KEY (architect_id) REFERENCES architecture_map.architect(id);


--
-- Name: construction_architect construction_architect_construction_id_fkey; Type: FK CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.construction_architect
    ADD CONSTRAINT construction_architect_construction_id_fkey FOREIGN KEY (construction_id) REFERENCES architecture_map.construction(id);


--
-- Name: construction_image construction_image_construction_id_fkey; Type: FK CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.construction_image
    ADD CONSTRAINT construction_image_construction_id_fkey FOREIGN KEY (construction_id) REFERENCES architecture_map.construction(id);


--
-- Name: construction_image construction_image_image_id_fkey; Type: FK CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.construction_image
    ADD CONSTRAINT construction_image_image_id_fkey FOREIGN KEY (image_id) REFERENCES architecture_map.image(id);


--
-- Name: construction fk_architectural_style_construction; Type: FK CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.construction
    ADD CONSTRAINT fk_architectural_style_construction FOREIGN KEY (architectural_style_id) REFERENCES architecture_map.architectural_style(id);


--
-- Name: image image_source_id_fkey; Type: FK CONSTRAINT; Schema: architecture_map; Owner: nadzeya2
--

ALTER TABLE ONLY architecture_map.image
    ADD CONSTRAINT image_source_id_fkey FOREIGN KEY (source_id) REFERENCES architecture_map.source(id);


--
-- PostgreSQL database dump complete
--

