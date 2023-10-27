INSERT INTO "image"(link) values('ic_menu_chest');
INSERT INTO "image"(link) values('ic_menu_back');
INSERT INTO "image"(link) values('ic_menu_biceps');
INSERT INTO "image"(link) values('ic_menu_triceps');
INSERT INTO "image"(link) values('ic_menu_shoulders');
INSERT INTO "image"(link) values('ic_menu_trapeze');
INSERT INTO "image"(link) values('ic_menu_forearms');
INSERT INTO "image"(link) values('ic_menu_abs');
INSERT INTO "image"(link) values('ic_menu_quads');
INSERT INTO "image"(link) values('ic_menu_hamstrings');
INSERT INTO "image"(link) values('ic_menu_glutes');
INSERT INTO "image"(link) values('ic_menu_caviar');


INSERT INTO "user"(name, surname, birthday, gender) values('Василиса', 'Егорова', '1981-05-22', 'W');
INSERT INTO "user"(name, surname, birthday, gender) values('Валерия', 'Щербаков', '1981-05-22', 'W');

INSERT INTO "credentials"(user_id, login, password) values(1, 'rock', '$2a$10$w9Xvn7V.w6XTMoCrLyPIX.X/gnSaSavaRhO2yVEFNwrlb4kqj4jhe');
INSERT INTO "credentials"(user_id, login, password) values(2, 'lera', '$2a$10$w9Xvn7V.w6XTMoCrLyPIX.X/gnSaSavaRhO2yVEFNwrlb4kqj4jhe');

INSERT INTO "settings"(user_id, language, units) values(1, 'en', 'metric');
INSERT INTO "settings"(user_id, language, units) values(2, 'ru', 'metric');

INSERT INTO "category"(name, image_id) values('Chest', 1);
INSERT INTO "category"(name, image_id) values('Back', 2);
INSERT INTO "category"(name, image_id) values('Biceps', 3);
INSERT INTO "category"(name, image_id) values('Triceps', 4);
INSERT INTO "category"(name, image_id) values('Shoulders', 5);
INSERT INTO "category"(name, image_id) values('Trapezius', 6);
INSERT INTO "category"(name, image_id) values('Forearms', 7);
INSERT INTO "category"(name, image_id) values('Abs', 8);
INSERT INTO "category"(name, image_id) values('Quads', 9);
INSERT INTO "category"(name, image_id) values('Hamstrings', 10);
INSERT INTO "category"(name, image_id) values('Glutes', 11);
INSERT INTO "category"(name, image_id) values('Calves', 12);

INSERT INTO "dimension"(name) values('Руки');
INSERT INTO "dimension"(name) values('Предплечья');
INSERT INTO "dimension"(name) values('Шея');
INSERT INTO "dimension"(name) values('Грудь');
INSERT INTO "dimension"(name) values('Талия');
INSERT INTO "dimension"(name) values('Бедра');
INSERT INTO "dimension"(name) values('Икры');

INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(1, 13, 'Bench Press', 'Barbell', 'The bench press is a basic exercise with free weights. The exercise is performed with a barbell on a bench.',  '1. Lay flat on the bench with your feet on the ground. With straight arms unrack the bar.\n 2. Lower the bar to your mid chest\n 3. Raise the bar until you locked your elbows');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(1, 14, 'Incline Bench Press', 'Barbell', 'The incline bench press is a variation of the bench press. Using an incline will allow you to better target your upper chest, a lagging area for many lifters. The exercise is performed with a barbell on a bench with an incline of 30°.', '1. Position the bench between 30 and 45 degrees.\n2. Lay flat on the bench with your feet on the ground. With straight arms unrack the bar.\n3. Lower the bar to your mid chest \n4. Raise the bar (slowly and controlled) until you locked your elbows.');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(1, 15, 'Decline Bench Press', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(1, 16, 'Bench Press', 'Dumbbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(1, 17, 'Incline Bench Press', 'Dumbbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(1, 18, 'Decline Bench Press', 'Dumbbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(1, 19, 'Bench Press', 'Smith Machine', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(1, 20, 'Incline Bench Press', 'Smith Machine', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(1, 21, 'Decline Bench Press', 'Smith Machine', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(1, 22, 'Bench Press', 'Machine', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(1, 23, 'Incline Bench Press', 'Machine', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(1, 24, 'Incline Chest Flys', 'Dumbbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(1, 25, 'Push Up', 'Body weight', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(1, 26, 'Chest Dips', 'Body weight', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(1, 27, 'Chest Fly', 'Cable', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(1, 28, 'Incline Chest Fly', 'Cable', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(1, 29, 'Decline Chest Fly', 'Cable', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(1, 30, 'Chest Press', 'Cable', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(1, 31, 'Pullover', 'Dumbbell', '', '');

INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(2, 32, 'Bent Over Row', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(2, 33, 'Reverse Grip Bent Over Row', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(2, 34, 'Deadlift', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(2, 35, 'Good Mornings', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(2, 36, 'T-Bar Rows', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(2, 37, 'Deadlift', 'Dumbbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(2, 38, 'Row Bilateral', 'Dumbbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(2, 39, 'Row Unilateral ', 'Dumbbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(2, 40, 'Pull Ups', 'Body weight', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(2, 42, 'Hyperextension', 'Body weight', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(2, 43, 'Pull Ups', 'Gravitron', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(2, 44, 'Lat Pulldown ', 'Cable', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(2, 45, 'Behind Neck Lat Pulldown ', 'Cable', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(2, 46, 'Seated Cable Row', 'Cable', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(2, 47, 'V-Bar Pulldown ', 'Cable', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(2, 48, 'T-Bar Row ', 'Machine', '', '');

INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(3, 49, 'Curls', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(3, 50, 'Reverse Curls', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(3, 51, 'Preacher Curls', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(3, 52, 'Curls', 'EZ-Bar', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(3, 53, 'Curls', 'Dumbbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(3, 54, 'Preacher Curls', 'Dumbbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(3, 55, 'Hammer Curls', 'Dumbbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(3, 56, 'Reverse Curls', 'Dumbbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(3, 57, 'Incline Lying Curls', 'Dumbbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(3, 58, 'Chin Ups', 'Body weight', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(3, 59, 'Superman Curls', 'Cable', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(3, 60, 'Standing Curls', 'Cable', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(3, 61, 'Hammer Curls', 'Cable', '', '');

INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(4, 62, 'French Press', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(4, 63, 'Close Grip Bench Press', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(4, 64, 'Close Grip Bench Press', 'Smith Machine', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(4, 65, 'Skullcrusher', 'Dumbbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(4, 66, 'Overhead Extension', 'Dumbbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(4, 67, 'Rope Pushdown', 'Cable', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(4, 68, 'Pushdown', 'Cable', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(4, 69, 'Reverse Grip Pushdown', 'Cable', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(4, 70, 'Dips', 'Body weight', '', '');

INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(5, 71, 'Lateral Raise', 'Dumbbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(5, 72, 'Front Raise', 'Dumbbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(5, 73, 'Rear Delt Fly', 'Dumbbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(5, 74, 'Overhead Press', 'Dumbbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(5, 75, 'Front Raise', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(5, 76, 'Overhead Press', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(5, 77, 'Upright Row', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(5, 78, 'Upright Row', 'Smith Machine', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(5, 79, 'Overhead Press', 'Smith Machine', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(5, 80, 'Rope Face Pulls', 'Cable', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(5, 81, 'Lateral Raise', 'Cable', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(5, 82, 'Upright Row', 'Cable', '', '');

INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(6, 83, 'Shrugs', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(6, 84, 'Shrugs', 'Cable', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(6, 85, 'Shrugs', 'Dumbbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(6, 86, 'Shrugs', 'Smith Machine', '', '');

INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(7, 87, 'Wrist Curl', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(7, 88, 'Wrist Curl', 'Dumbbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(7, 89, 'Wrist Curl', 'Cable', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(7, 90, 'Wrist Extension', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(7, 91, 'Wrist Extension', 'Dumbbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(7, 92, 'Wrist Extension', 'Cable', '', '');

INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(8, 93, 'Crunches', 'Machine', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(8, 94, 'Knee Raises', 'Body weight', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(8, 95, 'Crunches', 'Body weight', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(8, 96, 'Rope Crunches', 'Cable', '', '');

INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(9, 97, 'Squat', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(9, 98, 'Front Squat', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(9, 99, 'Bulgarian Squat', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(9, 100, 'Lunges', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(9, 101, 'Squat', 'Dumbbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(9, 102, 'Lunges', 'Dumbbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(9, 103, 'Hack Squat', 'Machine', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(9, 104, 'Leg Extensions', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(9, 105, 'Leg Press', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(9, 106, 'Squat', 'Smith Machine', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(9, 107, 'Thigh Adductor', 'Machine', '', '');

INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(10, 108, 'Deadlift', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(10, 109, 'Romanian Deadlift', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(10, 110, 'Seated Leg Curls', 'Machine', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(10, 111, 'Lying Leg Curls', 'Machine', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(10, 112, 'Standing Leg Curls', 'Machine', '', '');

INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(11, 113, 'Hip Thrust', 'Barbell', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(11, 114, 'Kickback', 'Cable', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(11, 115, 'Glute Bridge', 'Body weight', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(11, 116, 'Side Kick', 'Cable', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(11, 117, 'Hyperextension', 'Body weight', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(11, 118, 'Romanian Deadlift', 'Barbell', '', '');

INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(12, 119, 'Seated Calf Raises', 'Machine', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(12, 120, 'Calf Press', 'Machine', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(12, 121, 'Calf Raises', 'Smith Machine', '', '');
INSERT INTO "exercise"(category_id, image_id, name, equipment, description, technique) values(12, 122, 'Standing Calf Raises', 'Body weight', '', '');

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