
INSERT INTO `heroku_734af71028b13b6`.`evaluate` (name, text) VALUES ('Alacsony vérnyomás.', 'Alacsony');
INSERT INTO `heroku_734af71028b13b6`.`evaluate` (name, text) VALUES ('Optimális vérnyomás.', 'Optimális');
INSERT INTO `heroku_734af71028b13b6`.`evaluate` (name, text) VALUES ('Normális vérnyomás.', 'Normális');
INSERT INTO `heroku_734af71028b13b6`.`evaluate` (name, text) VALUES ('Kis mértékben emelkedett vérnyomás.', 'Emelkedett');
INSERT INTO `heroku_734af71028b13b6`.`evaluate` (name, text) VALUES ('1. fokú hipertenzió.', 'Elsőfokú');
INSERT INTO `heroku_734af71028b13b6`.`evaluate` (name, text) VALUES ('2. fokú hipertenzió.', 'Másodfokú');
INSERT INTO `heroku_734af71028b13b6`.`evaluate` (name, text) VALUES ('3. fokú hipertenzió.', 'Harmadfokú');

INSERT INTO `heroku_734af71028b13b6`.`condition_table` (condition_column) VALUES ('Reggel, ébredés után');
INSERT INTO `heroku_734af71028b13b6`.`condition_table` (condition_column) VALUES ('Este, elalvás előtt');
INSERT INTO `heroku_734af71028b13b6`.`condition_table` (condition_column) VALUES ('Sportolás után');
INSERT INTO `heroku_734af71028b13b6`.`condition_table` (condition_column) VALUES ('Napközben, nyugalmi állapotban');

-- INSERT INTO `bloodpressure`.`weather_data` (`id`, `air_pressure`, `med_meteorology`, `humidity`, `temperature`) VALUES (1, 20.0, 'nincs front', 20.0, 25.0);

INSERT INTO `heroku_734af71028b13b6`.`blood_pressure_value` (`min_age`, `max_age`,  `min_value`, `max_value`, `proper_value`) VALUES (14,19, '105/73', '120/81', '117/77');
INSERT INTO `heroku_734af71028b13b6`.`blood_pressure_value` (`min_age`, `max_age`,  `min_value`, `max_value`, `proper_value`) VALUES (20,24, '108/75', '132/83', '120/79');
INSERT INTO `heroku_734af71028b13b6`.`blood_pressure_value` (`min_age`, `max_age`,  `min_value`, `max_value`, `proper_value`) VALUES (25,29, '109/76', '133/84', '120/80');
INSERT INTO `heroku_734af71028b13b6`.`blood_pressure_value` (`min_age`, `max_age`,  `min_value`, `max_value`, `proper_value`) VALUES (30,34, '110/77', '134/85', '122/81');
INSERT INTO `heroku_734af71028b13b6`.`blood_pressure_value` (`min_age`, `max_age`,  `min_value`, `max_value`, `proper_value`) VALUES (35,39, '111/78', '135/86', '123/82');
INSERT INTO `heroku_734af71028b13b6`.`blood_pressure_value` (`min_age`, `max_age`,  `min_value`, `max_value`, `proper_value`) VALUES (40,44, '112/79', '137/87', '124/83');
INSERT INTO `heroku_734af71028b13b6`.`blood_pressure_value` (`min_age`, `max_age`,  `min_value`, `max_value`, `proper_value`) VALUES (45,49, '115/80', '139/88', '126/84');
INSERT INTO `heroku_734af71028b13b6`.`blood_pressure_value` (`min_age`, `max_age`,  `min_value`, `max_value`, `proper_value`) VALUES (50,54, '116/81', '142/89', '129/85');
INSERT INTO `heroku_734af71028b13b6`.`blood_pressure_value` (`min_age`, `max_age`,  `min_value`, `max_value`, `proper_value`) VALUES (55,59, '118/82', '144/90', '131/86');
INSERT INTO `heroku_734af71028b13b6`.`blood_pressure_value` (`min_age`, `max_age`,  `min_value`, `max_value`, `proper_value`) VALUES (60, 117, '122/84', '146/94', '134/87');
INSERT INTO `heroku_734af71028b13b6`.`role` (name) values ('ROLE_USER');