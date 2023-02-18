DROP TABLE IF EXISTS "credentials" CASCADE;
DROP TABLE IF EXISTS "user" CASCADE;

CREATE TABLE "credentials"
(
    "user_id"  bigserial PRIMARY KEY,
    "login"    varchar,
    "password" varchar
);

CREATE TABLE "user"
(
    "id"         bigserial PRIMARY KEY,
    "name"       varchar,
    "surname"    varchar,
    "birthday"   varchar,
    "gender"     varchar
);

ALTER TABLE "credentials"
    ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");
