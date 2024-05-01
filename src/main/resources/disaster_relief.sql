-- POSTGRESQL RELIEF SERVICES DATABASE --
-- AUTHOR: LAMMY

-- CREATING TABLES --
DROP TABLE IF EXISTS "inquirer" CASCADE;
CREATE TABLE "inquirer"
(
    "inq_id"       SERIAL,
    "first_name"   VARCHAR(100) NOT NULL,
    "last_name"    VARCHAR(100),
    "phone_number" TEXT         NOT NULL,

    PRIMARY KEY ("inq_id")
);

DROP TABLE IF EXISTS "inquiry_log" CASCADE;
CREATE TABLE "inquiry_log"
(
    "log_id"          SERIAL,
    "inq_id"          INT          NOT NULL,
    "date_of_inquiry" DATE         NOT NULL,
    "info_provided"   VARCHAR(500) NOT NULL,

    PRIMARY KEY ("log_id"),
    FOREIGN KEY ("inq_id") REFERENCES inquirer (inq_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS "location" CASCADE;
CREATE TABLE "location"
(
    "l_id"    CHAR(5)      NOT NULL,
    "name"    VARCHAR(100) NOT NULL,
    "address" VARCHAR(500) NOT NULL,

    PRIMARY KEY ("l_id")
);

DROP TABLE IF EXISTS "disaster_victim" CASCADE;
CREATE TABLE "disaster_victim"
(
    "p_id"            SERIAL,
    "l_id"            CHAR(5)      NOT NULL,
    "entry_date"      DATE         NOT NULL,
    "first_name"      VARCHAR(100) NOT NULL,
    "last_name"       VARCHAR(100),
    "date_of_birth"   DATE,
    "approximate_age" INT,
    "comments"        VARCHAR(500),
    "gender"          VARCHAR(100),

    PRIMARY KEY ("p_id"),
    FOREIGN KEY ("l_id") REFERENCES "location" ("l_id") ON DELETE CASCADE
);

DROP TABLE IF EXISTS "family_relation" CASCADE;
CREATE TABLE "family_relation"
(
    "p1_id"           INT          NOT NULL,
    "relationship_to" VARCHAR(100) NOT NULL,
    "p2_id"           INT          NOT NULL,

    PRIMARY KEY ("p1_id", "relationship_to", "p2_id"),
    FOREIGN KEY (p1_id) REFERENCES disaster_victim (p_id) ON DELETE CASCADE, --ON UPDATE CASCADE in case of changing primary key
    FOREIGN KEY (p2_id) REFERENCES disaster_victim (p_id) ON DELETE CASCADE
);

DROP TYPE IF EXISTS DIET_OPTIONS CASCADE;
CREATE TYPE DIET_OPTIONS AS ENUM ('AVML', 'DBML', 'GFML', 'KSML', 'LSML', 'MOML', 'PFML', 'VGML', 'VJML');

DROP TABLE IF EXISTS "dietary_restriction" CASCADE;
CREATE TABLE "dietary_restriction"
(
    "p_id"             INT NOT NULL,
    "diet_restriction" DIET_OPTIONS,

    PRIMARY KEY ("p_id", "diet_restriction"),
    FOREIGN KEY ("p_id") REFERENCES disaster_victim (p_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS "medical_record" CASCADE;
CREATE TABLE "medical_record"
(
    "p_id"              INT          NOT NULL,
    "l_id"              CHAR(5)      NOT NULL,
    "treatment_details" VARCHAR(500) NOT NULL,
    "date_of_treatment" DATE         NOT NULL,

    PRIMARY KEY ("p_id", "l_id"),
    FOREIGN KEY ("p_id") REFERENCES disaster_victim (p_id) ON DELETE CASCADE,
    FOREIGN KEY ("l_id") REFERENCES "location" (l_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS "supply" CASCADE;
CREATE TABLE "supply"
(
    "s_id"              CHAR(5)      NOT NULL,
    "l_id"              CHAR(5)      NOT NULL,
    "type"              VARCHAR(100) NOT NULL,
    "quantity_in_stock" INT,

    PRIMARY KEY ("s_id"),
    FOREIGN KEY ("l_id") REFERENCES "location" (l_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS "personal_belonging" CASCADE;
CREATE TABLE "personal_belonging"
(
    "p_id"                 INT          NOT NULL,
    "type"                 VARCHAR(100) NOT NULL,
    "amount_in_possession" INT,

    PRIMARY KEY ("p_id"),
    FOREIGN KEY ("p_id") REFERENCES disaster_victim (p_id) ON DELETE CASCADE
);

-- INSERTING VALUES --

INSERT INTO inquirer (first_name, last_name, phone_number)
VALUES ('Dominik', 'Pflug', '123-456-9831'),
       ('Yaa', 'Odei', '123-456-8913'),
       ('Cecilia', 'Cobos', '123-456-7891'),
       ('Hongjoo', 'Park', '123-456-8912'),
       ('Saartje', NULL, '123-456-7234'),
       ('Urjoshi', NULL, '456-123-4281');

INSERT INTO inquiry_log (inq_id, date_of_inquiry, info_provided)
VALUES (1, '2024-02-28', 'Looking for my wife Theresa Pflug'),
       (2, '2024-02-28', 'I''m offering to assist as volunteer'),
       (3, '2024-03-01', 'Valesk Souza'),
       (1, '2024-03-01', 'Looking for my wife Theresa Pflug. Please assist!'),
       (1, '2024-03-02', 'Where is Theresa Pflug!!!'),
       (4, '2024-03-02', 'Looking for my friends, Yoyo Jefferson and Roisin Fitzgerald'),
       (5, '2024-03-02', 'Henk Wouters'),
       (3, '2024-03-03', 'Looking for my child Melinda'),
       (6, '2024-03-04', 'Julius, my best friend has gone missing');


INSERT INTO "location" ("l_id", "name", "address")
VALUES ('LA25D', 'Los Angeles', '25 Donatello Road, NW'),
       ('NY39A', 'New York', '39 Flinstone Avenue, SW'),
       ('TO11B', 'Toronto', '11 Panorama Blvd, SE'),
       ('CA04D', 'Calgary', '4 Humpton Lane, NE');

INSERT INTO "disaster_victim" ("l_id", "entry_date", "first_name", "last_name", "date_of_birth", "approximate_age",
                               "comments", "gender")
VALUES ('LA25D', '2022-01-01', 'Jean-Claude', 'Van-Damme', '1996-02-02', NULL, 'He''s that guy', 'man'),
       ('CA04D', '2024-01-01', 'Theresa', 'Pflug', '1989-02-09', NULL,
        'Came in with deep wounds. Looking for her husband.', 'woman'),
       ('CA04D', '2023-04-04', 'Valesk', 'Souza', '2002-11-22', NULL, 'Bad wounds, priority victim', 'man'),
       ('NY39A', '2022-09-22', 'Yoyo', 'Jefferson', NULL, 25, 'Okay shape. Prepared to volunteer', 'man'),
       ('NY39A', '2022-09-22', 'Roisin', 'Fitzgerald', NULL, 30, 'Okay shape, Prepared to volunteer', 'man'),
       ('TO11B', '2021-07-09', 'Henk', 'Wouters', NULL, 40, 'Slight bruises, prepared to volunteer', 'man'),
       ('LA25D', '2024-02-02', 'Melinda', NULL, NULL, 18, 'Looking for brother Julius', 'girl'),
       ('LA25D', '2024-02-03', 'Julius', NULL, NULL, 17, 'Shell-shocked, priority victim', 'boy'),
       ('CA04D', '2022-01-02', 'Teruya', NULL, NULL, 5,
        'Alert but quiet. Speaks French and Japanese. Has been assigned a social worker', 'boy'),
       ('TO11B', '2024-01-18', 'Freda', 'McDonald', '1998-09-06', NULL,
        'Not accompanied by husband. Looking for child Teruya.', 'woman');

INSERT INTO "family_relation" ("p1_id", "relationship_to", "p2_id")
VALUES (7, 'sibling', 8),
       (9, 'child', 10),
       (10, 'parent', 9);

INSERT INTO "dietary_restriction" ("p_id", "diet_restriction")
VALUES (1, 'VGML'),
       (2, 'AVML');

INSERT INTO "medical_record" ("p_id", "l_id", "treatment_details", "date_of_treatment")
VALUES (1, 'LA25D', 'Treated nothing. He''s the best', '2022-01-01');

INSERT INTO "supply" ("s_id", "l_id", "type", "quantity_in_stock")
VALUES ('COLA2', 'LA25D', 'Clothing', 48),
       ('FOLA2', 'LA25D', 'Food', 100),
       ('FOTO1', 'TO11B', 'Food', 150);

INSERT INTO "personal_belonging" ("p_id", "type", "amount_in_possession")
VALUES ('COLA2', 'Clothing', 2);