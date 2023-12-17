INSERT INTO "user"(name, birthday, gender, image_id, enabled, locked) values('Антоний', '1981-05-22', 'M', 1, true, false);
INSERT INTO "user"(name, birthday, gender, image_id, enabled, locked) values('Валерия', '1981-05-22', 'F', 1, true, false);
INSERT INTO "user"(name, birthday, gender, image_id, enabled, locked) values('Алексей', '1981-05-22', 'M', 1, true, true);

INSERT INTO "credentials"(user_id, login, email, role, password) values(1, 'user', 'antonsamoilo@gmail.com', 'USER', '$2a$10$w9Xvn7V.w6XTMoCrLyPIX.X/gnSaSavaRhO2yVEFNwrlb4kqj4jhe');
INSERT INTO "credentials"(user_id, login, email, role, password) values(2, 'lera', 'lera@qualle.test', 'USER', '$2a$10$w9Xvn7V.w6XTMoCrLyPIX.X/gnSaSavaRhO2yVEFNwrlb4kqj4jhe');
INSERT INTO "credentials"(user_id, login, email, role, password) values(3, 'alexi', 'alexi@qualle.test', 'USER', '$2a$10$w9Xvn7V.w6XTMoCrLyPIX.X/gnSaSavaRhO2yVEFNwrlb4kqj4jhe');

INSERT INTO "settings"(user_id, language, units) values(1, 'en', 'metric');
INSERT INTO "settings"(user_id, language, units) values(2, 'ru', 'metric');
INSERT INTO "settings"(user_id, language, units) values(3, 'ru', 'metric');

INSERT INTO "user_dimension"(user_id, dimension_id, value, date) values(1, 1, 40, '2023-04-22 19:10:25-07');
INSERT INTO "user_dimension"(user_id, dimension_id, value, date) values(1, 2, 28, '2023-04-22 19:10:25-07');
INSERT INTO "user_dimension"(user_id, dimension_id, value, date) values(1, 3, 50, '2023-04-22 19:10:25-07');
INSERT INTO "user_dimension"(user_id, dimension_id, value, date) values(1, 4, 110, '2023-04-22 19:10:25-07');
INSERT INTO "user_dimension"(user_id, dimension_id, value, date) values(1, 5, 80, '2023-04-22 19:10:25-07');
INSERT INTO "user_dimension"(user_id, dimension_id, value, date) values(1, 6, 67, '2023-04-22 19:10:25-07');
INSERT INTO "user_dimension"(user_id, dimension_id, value, date) values(1, 7, 43, '2023-04-22 19:10:25-07');

INSERT INTO "user_dimension"(user_id, dimension_id, value, date) values(1, 1, 40, '2023-05-01 19:10:25-07');
INSERT INTO "user_dimension"(user_id, dimension_id, value, date) values(1, 2, 28, '2023-05-01 19:10:25-07');
INSERT INTO "user_dimension"(user_id, dimension_id, value, date) values(1, 3, 50, '2023-05-01 19:10:25-07');
INSERT INTO "user_dimension"(user_id, dimension_id, value, date) values(1, 4, 110, '2023-05-01 19:10:25-07');
INSERT INTO "user_dimension"(user_id, dimension_id, value, date) values(1, 5, 80, '2023-05-01 19:10:25-07');
INSERT INTO "user_dimension"(user_id, dimension_id, value, date) values(1, 6, 67, '2023-05-01 19:10:25-07');
INSERT INTO "user_dimension"(user_id, dimension_id, value, date) values(1, 7, 43, '2023-05-01 19:10:25-07');

INSERT INTO "workout"(user_id, date) values(1, '2023-09-22 02:10:25.000000');
INSERT INTO "workout"(user_id, date) values(1, '2023-09-23 02:10:25.000000');
INSERT INTO "workout"(user_id, date) values(1, '2023-09-24 02:10:25.000000');

INSERT INTO "record"(workout_id, exercise_id, weight, reps) values(1, 1, 60, 14);
INSERT INTO "record"(workout_id, exercise_id, weight, reps) values(1, 2, 80, 12);
INSERT INTO "record"(workout_id, exercise_id, weight, reps) values(1, 3, 70, 10);
INSERT INTO "record"(workout_id, exercise_id, weight, reps) values(2, 1, 70, 14);
INSERT INTO "record"(workout_id, exercise_id, weight, reps) values(2, 2, 90, 13);
INSERT INTO "record"(workout_id, exercise_id, weight, reps) values(2, 3, 80, 12);
INSERT INTO "record"(workout_id, exercise_id, weight, reps) values(3, 1, 80, 10);
INSERT INTO "record"(workout_id, exercise_id, weight, reps) values(3, 2, 100, 9);
INSERT INTO "record"(workout_id, exercise_id, weight, reps) values(3, 3, 90, 6);