CREATE SCHEMA IF NOT EXISTS "public";

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
    "birthday" varchar,
    "gender"   varchar,
    "image_id" bigint,
    "enabled"  boolean,
    "locked"   boolean
);

CREATE TABLE "credentials"
(
    "user_id"  bigint PRIMARY KEY,
    "login"    varchar,
    "email"    varchar,
    "role"     varchar,
    "password" varchar
);

CREATE TABLE "confirmation"
(
    "user_id"    bigint PRIMARY KEY,
    "code"       integer,
    "created_at" timestamp
);

CREATE TABLE "session"
(
    "id"         varchar PRIMARY KEY,
    "token_id"   varchar,
    "user_id"    bigint,
    "created_at" bigint,
    "expired_at" bigint
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
    "id"       bigserial PRIMARY KEY,
    "image_id" bigint,
    "name"     varchar
);

CREATE TABLE "exercise"
(
    "id"          bigserial PRIMARY KEY,
    "image_id"    bigint,
    "category_id" bigint,
    "name"        varchar,
    "equipment"   varchar,
    "description" varchar,
    "technique"   varchar
);

CREATE TABLE "record"
(
    "id"          bigserial PRIMARY KEY,
    "workout_id"  bigint,
    "exercise_id" bigint,
    "weight"      real,
    "reps"        integer
);

ALTER TABLE "credentials"
    ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "session"
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