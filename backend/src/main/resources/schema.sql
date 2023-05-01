DROP TABLE IF EXISTS "credentials" CASCADE;
DROP TABLE IF EXISTS "user" CASCADE;
DROP TABLE IF EXISTS "measurement" CASCADE;
DROP TABLE IF EXISTS "user_measurement" CASCADE;
DROP TABLE IF EXISTS "workout" CASCADE;
DROP TABLE IF EXISTS "exercise" CASCADE;
DROP TABLE IF EXISTS "record" CASCADE;

CREATE TABLE "credentials"
(
    "user_id"  bigint PRIMARY KEY,
    "login"    varchar,
    "password" varchar
);

CREATE TABLE "user"
(
    "id"       bigserial PRIMARY KEY,
    "name"     varchar,
    "surname"  varchar,
    "birthday" varchar,
    "gender"   varchar
);

CREATE TABLE "measurement"
(
    "id"   bigserial PRIMARY KEY,
    "name" varchar
);

CREATE TABLE "user_measurement"
(
    "user_id"        bigint,
    "measurement_id" bigint,
    "value"          bigint,
    PRIMARY KEY ("user_id", "measurement_id")
);

CREATE TABLE "workout"
(
    "id"      bigserial PRIMARY KEY,
    "user_id" bigint,
    "date"    timestamp
);

CREATE TABLE "exercise"
(
    "id"          bigserial PRIMARY KEY,
    "name"        varchar,
    "description" varchar
);

CREATE TABLE "record"
(
    "workout_id"  bigint,
    "exercise_id" bigint,
    "weight"      integer,
    "count"       integer,
    PRIMARY KEY ("workout_id", "exercise_id")
);

ALTER TABLE "credentials"
    ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "user_measurement"
    ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "workout"
    ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "credentials"
    ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "record"
    ADD FOREIGN KEY ("workout_id") REFERENCES "workout" ("id");

ALTER TABLE "record"
    ADD FOREIGN KEY ("exercise_id") REFERENCES "exercise" ("id");