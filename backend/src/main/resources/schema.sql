DROP TABLE IF EXISTS "image" CASCADE;
DROP TABLE IF EXISTS "credentials" CASCADE;
DROP TABLE IF EXISTS "user" CASCADE;
DROP TABLE IF EXISTS "token" CASCADE;
DROP TABLE IF EXISTS "settings" CASCADE;
DROP TABLE IF EXISTS "dimension" CASCADE;
DROP TABLE IF EXISTS "user_dimension" CASCADE;
DROP TABLE IF EXISTS "workout" CASCADE;
DROP TABLE IF EXISTS "exercise" CASCADE;
DROP TABLE IF EXISTS "record" CASCADE;
DROP TABLE IF EXISTS "category" CASCADE;

CREATE TABLE "image"
(
    "id"   bigserial PRIMARY KEY,
    "link" varchar,
    "name" varchar
);

CREATE TABLE "user"
(
    "id"       bigserial PRIMARY KEY,
    "name"     varchar,
    "surname"  varchar,
    "birthday" varchar,
    "gender"   varchar,
    "image_id" bigint
);

CREATE TABLE "credentials"
(
    "user_id"  bigint PRIMARY KEY,
    "login"    varchar,
    "password" varchar
);

CREATE TABLE "token"
(
    "id"                 bigserial PRIMARY KEY,
    "access"             varchar,
    "refresh"            varchar,
    "user_id"            bigint,
    "access_expired_at"  timestamp,
    "refresh_expired_at" timestamp
);

CREATE TABLE "settings"
(
    "user_id"  bigserial PRIMARY KEY,
    "language" varchar,
    "units"    varchar
);

CREATE TABLE "dimension"
(
    "id"   bigserial PRIMARY KEY,
    "name" varchar
);

CREATE TABLE "user_dimension"
(
    "id"           bigserial PRIMARY KEY,
    "user_id"      bigint,
    "dimension_id" bigint,
    "value"        bigint,
    "date"         timestamp
);

CREATE TABLE "workout"
(
    "id"      bigserial PRIMARY KEY,
    "user_id" bigint,
    "date"    timestamp
);

CREATE TABLE "category"
(
    "id"          bigserial PRIMARY KEY,
    "image_id"    bigint,
    "name"        varchar
);

CREATE TABLE "exercise"
(
    "id"          bigserial PRIMARY KEY,
    "image_id"    bigint,
    "category_id" bigint,
    "name"        varchar,
    "description" varchar
);

CREATE TABLE "record"
(
    "id"           bigserial PRIMARY KEY,
    "workout_id"   bigint,
    "exercise_id"  bigint,
    "value"        integer,
    "measure_type" varchar
);

ALTER TABLE "credentials"
    ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "token"
    ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "settings"
    ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "user_dimension"
    ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "user_dimension"
    ADD FOREIGN KEY ("dimension_id") REFERENCES "dimension" ("id");

ALTER TABLE "workout"
    ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "record"
    ADD FOREIGN KEY ("workout_id") REFERENCES "workout" ("id");

ALTER TABLE "record"
    ADD FOREIGN KEY ("exercise_id") REFERENCES "exercise" ("id");

ALTER TABLE "exercise"
    ADD FOREIGN KEY ("category_id") REFERENCES "category" ("id");