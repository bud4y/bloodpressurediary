INSERT INTO `bloodpressure`.`user` (`bmi`, `birth_date`, `email`, `first_name`, `height`, `is_male`, `last_name`, `password`, `weight`) VALUES (20, '1991-06-08', 'botosjano@gmail.com', 'János', 183, 1, 'Botos', 'asdf', 75);
INSERT INTO `bloodpressure`.`user` (`bmi`, `birth_date`, `email`, `first_name`, `height`, `is_male`, `last_name`, `password`, `weight`) VALUES (20, '1992-12-08', 'tcsaba92@gmail.com', 'Csaba', 183, 1, 'Tóth', 'asdf', 75);
INSERT INTO `bloodpressure`.`evaluate` (name, text) VALUES ('Nagyszerű hírünk van! Önnek tökéletes a vérnyomása.', 'Optimális vérnyomás');
INSERT INTO `bloodpressure`.`evaluate` (name, text) VALUES ('Önnek normális a vérnyomása.', 'Normális vérnyomás.');
INSERT INTO `bloodpressure`.`evaluate` (name, text) VALUES ('Emelkedett-normál szisztolés vérnyomás.', 'Emelkedett');
INSERT INTO `bloodpressure`.`evaluate` (name, text) VALUES ('Elsőfokú magas vérnyomás.', 'Elsőfokú');
INSERT INTO `bloodpressure`.`evaluate` (name, text) VALUES ('Másodfokú magas vérnyomás.', 'Másodfokú');
INSERT INTO `bloodpressure`.`evaluate` (name, text) VALUES ('Harmadfokú magas vérnyomás.', 'Harmadfokú');
INSERT INTO `bloodpressure`.`weather_data` (`id`, `air_pressure`, `med_meteorology`, `humidity`, `temperature`) VALUES (1, 20.0, 'nincs front', 20.0, 25.0);
INSERT INTO role (name) values ('ROLE_USER');

