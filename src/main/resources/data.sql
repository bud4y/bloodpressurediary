# INSERT INTO `bloodpressure`.`user` (`bmi`, `birth_date`, `email`, `first_name`, `height`, `is_male`, `last_name`, `password`, `weight`) VALUES (20, '1991-06-08', 'botosjano@gmail.com', 'János', 183, 1, 'Botos', 'asdf', 75);
# INSERT INTO `bloodpressure`.`user` (`bmi`, `birth_date`, `email`, `first_name`, `height`, `is_male`, `last_name`, `password`, `weight`) VALUES (20, '1992-12-08', 'tcsaba92@gmail.com', 'Csaba', 183, 1, 'Tóth', 'asdf', 75);
INSERT INTO `bloodpressure`.`evaluate` (name, text) VALUES ('Nagyszerű hírünk van! Önnek tökéletes a vérnyomása.', 'Optimális vérnyomás');
INSERT INTO `bloodpressure`.`evaluate` (name, text) VALUES ('Önnek normális a vérnyomása.', 'Normális vérnyomás.');
INSERT INTO `bloodpressure`.`evaluate` (name, text) VALUES ('Emelkedett-normál szisztolés vérnyomás.', 'Emelkedett');
INSERT INTO `bloodpressure`.`evaluate` (name, text) VALUES ('Elsőfokú magas vérnyomás.', 'Elsőfokú');
INSERT INTO `bloodpressure`.`evaluate` (name, text) VALUES ('Másodfokú magas vérnyomás.', 'Másodfokú');
INSERT INTO `bloodpressure`.`evaluate` (name, text) VALUES ('Harmadfokú magas vérnyomás.', 'Harmadfokú');

INSERT INTO `bloodpressure`.`role` (`name`) VALUES ('ROLE_USER');


INSERT INTO `bloodpressure`.`blood_pressure_value` (`min_age`, `max_age`,  `min_value`, `max_value`, `proper_value`) VALUES (14,19, '105/73', '120/81', '117/77');
INSERT INTO `bloodpressure`.`blood_pressure_value` (`min_age`, `max_age`,  `min_value`, `max_value`, `proper_value`) VALUES (20,24, '108/75', '132/83', '120/79');
INSERT INTO `bloodpressure`.`blood_pressure_value` (`min_age`, `max_age`,  `min_value`, `max_value`, `proper_value`) VALUES (25,29, '109/76', '133/84', '120/80');
INSERT INTO `bloodpressure`.`blood_pressure_value` (`min_age`, `max_age`,  `min_value`, `max_value`, `proper_value`) VALUES (30,34, '110/77', '134/85', '122/81');
INSERT INTO `bloodpressure`.`blood_pressure_value` (`min_age`, `max_age`,  `min_value`, `max_value`, `proper_value`) VALUES (35,39, '111/78', '135/86', '123/82');
INSERT INTO `bloodpressure`.`blood_pressure_value` (`min_age`, `max_age`,  `min_value`, `max_value`, `proper_value`) VALUES (40,44, '112/79', '137/87', '124/83');
INSERT INTO `bloodpressure`.`blood_pressure_value` (`min_age`, `max_age`,  `min_value`, `max_value`, `proper_value`) VALUES (45,49, '115/80', '139/88', '126/84');
INSERT INTO `bloodpressure`.`blood_pressure_value` (`min_age`, `max_age`,  `min_value`, `max_value`, `proper_value`) VALUES (50,54, '116/81', '142/89', '129/85');
INSERT INTO `bloodpressure`.`blood_pressure_value` (`min_age`, `max_age`,  `min_value`, `max_value`, `proper_value`) VALUES (55,59, '118/82', '144/90', '131/86');
INSERT INTO `bloodpressure`.`blood_pressure_value` (`min_age`, `max_age`,  `min_value`, `max_value`, `proper_value`) VALUES (60, 117, '122/84', '146/94', '134/87');

# INSERT INTO `bloodpressure`.`weather_data` (`id`, `air_pressure`, `med_meteorology`, `humidity`, `temperature`) VALUES (1, 20.0, 'nincs front', 20.0, 25.0);
# INSERT INTO role (name) values ('ROLE_USER');

