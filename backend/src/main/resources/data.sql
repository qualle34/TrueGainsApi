INSERT INTO "image"(link, name) values('/', 'test 1');
INSERT INTO "image"(link, name) values('/', 'test 2');
INSERT INTO "image"(link, name) values('/', 'test 3');

INSERT INTO "user"(name, surname, birthday, gender) values('Василиса', 'Егорова', '1981-05-22', 'W');
INSERT INTO "user"(name, surname, birthday, gender) values('Валерия', 'Щербаков', '1981-05-22', 'W');

INSERT INTO "credentials"(user_id, login, password) values(1, 'rock', '$2a$10$w9Xvn7V.w6XTMoCrLyPIX.X/gnSaSavaRhO2yVEFNwrlb4kqj4jhe');
INSERT INTO "credentials"(user_id, login, password) values(2, 'lera', '$2a$10$w9Xvn7V.w6XTMoCrLyPIX.X/gnSaSavaRhO2yVEFNwrlb4kqj4jhe');

INSERT INTO "settings"(user_id, language, units) values(1, 'en', 'metric');
INSERT INTO "settings"(user_id, language, units) values(2, 'ru', 'metric');

INSERT INTO "category"(name) values('Chest');
INSERT INTO "category"(name) values('Back');
INSERT INTO "category"(name) values('Biceps');
INSERT INTO "category"(name) values('Triceps');
INSERT INTO "category"(name) values('Forearms');
INSERT INTO "category"(name) values('Shoulders');
INSERT INTO "category"(name) values('Quadriceps');
INSERT INTO "category"(name) values('Hamstrings');
INSERT INTO "category"(name) values('Glutes');
INSERT INTO "category"(name) values('Abs');
INSERT INTO "category"(name) values('Calves');

INSERT INTO "dimension"(name) values('Руки');
INSERT INTO "dimension"(name) values('Предплечья');
INSERT INTO "dimension"(name) values('Шея');
INSERT INTO "dimension"(name) values('Грудь');
INSERT INTO "dimension"(name) values('Талия');
INSERT INTO "dimension"(name) values('Бедра');
INSERT INTO "dimension"(name) values('Икры');

INSERT INTO "exercise"(category_id, name, description) values(1, 'Жим лежа', 'Жим лёжа — базовое физическое упражнение со свободным весом. Выполняющий упражнение ложится на скамейку, опускает гриф до касания с грудью и поднимает до полного выпрямления в локтевом суставе.');
INSERT INTO "exercise"(category_id, name, description) values(2, 'Становая тяга', 'Становая тяга — базовое упражнение, выполняемое обычно со штангой, а также с гантелью или гирей, удерживаемыми обеими руками и поднимаемая на уровень опущенных вытянутых рук.');
INSERT INTO "exercise"(category_id, name, description) values(7, 'Присяд', 'Приседания — одно из базовых силовых упражнений. Выполняющий упражнение приседает и затем встаёт, возвращаясь в положение стоя.');

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