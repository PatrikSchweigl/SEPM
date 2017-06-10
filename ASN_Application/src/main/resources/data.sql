INSERT INTO user_data (FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME,  EMAIL,PASSWORD, USER_ROLE) VALUES('Admin', 'Istrator', 'admin', 'Innsbruck', '6020', 'Tiergartenstrasse','xyzdafsasdf233223@abc.at', '$2a$10$W9xQIwa/FstPUvcbzJXnQ.XjVdTyIcCEp.g6VCq1gYuSsQNjJjbJG', 'ADMIN');

INSERT INTO user_data (IMG_NAME, FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME, EMAIL, PASSWORD, USER_ROLE) VALUES('emptypicture.png', 'Bernardo', 'Menia', 'bernd', 'Innsbruck', '6020', 'Technikerstrasse', 'xyzdafsasdf233223@abc.at', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'PARENT');
INSERT INTO parent (FAMILY_STATUS, STATUS, USERNAME) VALUES ('VERHEIRATET', TRUE, 'bernd');
INSERT INTO user_data (IMG_NAME, FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME,  EMAIL,PASSWORD, USER_ROLE) VALUES('emptypicture.png','Ashley', 'McCain', 'ashley', 'Innsbruck', '6020', 'Bundesstrasse','xyzdafsasdf233223@abc.at', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'PARENT');
INSERT INTO parent (FAMILY_STATUS, STATUS, USERNAME) VALUES ('VERHEIRATET',TRUE, 'ashley');
INSERT INTO user_data (IMG_NAME, FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME,  EMAIL,PASSWORD, USER_ROLE) VALUES('emptypicture.png','Mohammed', 'Li', 'mohammed', 'Innsbruck', '6020', 'Bahnhofstraße','xyzdafsasdf233223@abc.at', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'PARENT');
INSERT INTO parent (FAMILY_STATUS, STATUS, USERNAME) VALUES ('VERHEIRATET', TRUE, 'mohammed');

INSERT INTO user_data (IMG_NAME, RELIGION, FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME,  EMAIL,PASSWORD, USER_ROLE) VALUES('emptypicture.png','CHRISTENTUM','Thorunn', 'Einarsson', 'thorunn', 'Innsbruck', '6020', 'Sillpark', 'xyzdafsasdf233223@abc.at','$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'EMPLOYEE');
INSERT INTO employee (USERNAME) VALUES ('thorunn');
INSERT INTO user_data (IMG_NAME, RELIGION, FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME,  EMAIL,PASSWORD, USER_ROLE) VALUES('emptypicture.png','BUDDHISMUS','Cheng', 'Lee', 'cheng', 'Innsbruck', '6020', 'Meinhardstrasse', 'xyzdafsasdf233223@abc.at','$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'EMPLOYEE');
INSERT INTO employee (USERNAME) VALUES ('cheng');
INSERT INTO user_data (IMG_NAME, RELIGION, FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME,  EMAIL,PASSWORD, USER_ROLE) VALUES('emptypicture.png','ISLAM','Fatima', 'Abdullah', 'fatima', 'Innsbruck', '6020', 'Bozener Platz', 'xyzdafsasdf233223@abc.at','$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'EMPLOYEE');
INSERT INTO employee (USERNAME) VALUES ('fatima');

INSERT INTO child (FIRST_NAME, LAST_NAME, PARENT1_USERNAME, BIRTHDAY, GENDER, EMERGENCY_NUMBER) VALUES('Lena', 'Rädler', 'bernd', '11/11/2015', 'WEIBLICH', '06949109995');
INSERT INTO child (FIRST_NAME, LAST_NAME, PARENT1_USERNAME, BIRTHDAY, GENDER, EMERGENCY_NUMBER) VALUES('Sakura', 'Xiaping', 'mohammed', '03/05/2014', 'WEIBLICH', '0361117615');
INSERT INTO child (FIRST_NAME, LAST_NAME, PARENT1_USERNAME, BIRTHDAY, GENDER, EMERGENCY_NUMBER) VALUES('Fidel', 'Castro', 'ashley', '26/09/2014', 'MAENNLICH', '0632651555');
INSERT INTO child (FIRST_NAME, LAST_NAME, PARENT1_USERNAME, BIRTHDAY, GENDER, EMERGENCY_NUMBER) VALUES('Peter', 'Hanser', 'bernd', '02/03/2013', 'MAENNLICH', '0963322115');

INSERT INTO private_message (MESSAGE, USERNAME_RECEIVER, USERNAME_SENDER) VALUES('Hallo', 'cheng', 'cheng');
INSERT INTO private_message (MESSAGE, USERNAME_RECEIVER, USERNAME_SENDER) VALUES('Hallo2', 'cheng', 'cheng');
INSERT INTO private_message (MESSAGE, USERNAME_RECEIVER, USERNAME_SENDER) VALUES('Hallo3', 'cheng', 'cheng');

INSERT INTO lunch (DATE, MEAL, COST) VALUES(STR_TO_DATE('05/06/2017:06:00:00', '%d/%m/%Y:%h:%i:%s'), 'Lasagne', 3.5);
INSERT INTO lunch (DATE, MEAL, COST) VALUES(STR_TO_DATE('06/06/2017:06:00:00', '%d/%m/%Y:%h:%i:%s'), 'Dürüm', 3.0);
INSERT INTO lunch (DATE, MEAL, COST) VALUES(STR_TO_DATE('07/06/2017:06:00:00', '%d/%m/%Y:%h:%i:%s'), 'Spaghetti', 3.5);


INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-01 7:00:00', '2017-06-01 8:15:00', '2017-06-01 12:00:00', '2017-06-01 13:45:00', 20, '2017-06-01 00:00:00', '2017-06-01 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-02 7:00:00', '2017-06-02 8:15:00', '2017-06-02 12:00:00', '2017-06-02 13:45:00', 20, '2017-06-02 00:00:00', '2017-06-02 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-07 7:00:00', '2017-06-07 8:15:00', '2017-06-07 12:00:00', '2017-06-07 13:45:00', 20, '2017-06-07 00:00:00', '2017-06-07 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-08 7:00:00', '2017-06-08 8:15:00', '2017-06-08 12:00:00', '2017-06-08 13:45:00', 20, '2017-06-08 00:00:00', '2017-06-08 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-09 7:00:00', '2017-06-09 8:15:00', '2017-06-09 12:00:00', '2017-06-09 13:45:00', 20, '2017-06-09 00:00:00', '2017-06-09 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-12 7:00:00', '2017-06-12 8:15:00', '2017-06-12 12:00:00', '2017-06-12 13:45:00', 20, '2017-06-12 00:00:00', '2017-06-12 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-13 7:00:00', '2017-06-13 8:15:00', '2017-06-13 12:00:00', '2017-06-13 13:45:00', 20, '2017-06-13 00:00:00', '2017-06-13 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-14 7:00:00', '2017-06-14 8:15:00', '2017-06-14 12:00:00', '2017-06-14 13:45:00', 20, '2017-06-14 00:00:00', '2017-06-14 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-16 7:00:00', '2017-06-16 8:15:00', '2017-06-16 12:00:00', '2017-06-16 13:45:00', 20, '2017-06-16 00:00:00', '2017-06-16 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-19 7:00:00', '2017-06-19 8:15:00', '2017-06-19 12:00:00', '2017-06-19 13:45:00', 20, '2017-06-19 00:00:00', '2017-06-19 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-20 7:00:00', '2017-06-20 8:15:00', '2017-06-20 12:00:00', '2017-06-20 13:45:00', 20, '2017-06-20 00:00:00', '2017-06-20 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-21 7:00:00', '2017-06-21 8:15:00', '2017-06-21 12:00:00', '2017-06-21 13:45:00', 20, '2017-06-21 00:00:00', '2017-06-21 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-22 7:00:00', '2017-06-22 8:15:00', '2017-06-22 12:00:00', '2017-06-22 13:45:00', 20, '2017-06-22 00:00:00', '2017-06-22 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-23 7:00:00', '2017-06-23 8:15:00', '2017-06-23 12:00:00', '2017-06-23 13:45:00', 20, '2017-06-23 00:00:00', '2017-06-23 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-26 7:00:00', '2017-06-26 8:15:00', '2017-06-26 12:00:00', '2017-06-26 13:45:00', 20, '2017-06-26 00:00:00', '2017-06-26 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-27 7:00:00', '2017-06-27 8:15:00', '2017-06-27 12:00:00', '2017-06-27 13:45:00', 20, '2017-06-27 00:00:00', '2017-06-27 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-28 7:00:00', '2017-06-28 8:15:00', '2017-06-28 12:00:00', '2017-06-28 13:45:00', 20, '2017-06-28 00:00:00', '2017-06-28 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-29 7:00:00', '2017-06-29 8:15:00', '2017-06-29 12:00:00', '2017-06-29 13:45:00', 20, '2017-06-29 00:00:00', '2017-06-29 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-30 7:00:00', '2017-06-30 8:15:00', '2017-06-30 12:00:00', '2017-06-30 13:45:00', 20, '2017-06-30 00:00:00', '2017-06-30 00:00:00');

INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-03 7:00:00', '2017-07-03 8:15:00', '2017-07-03 12:00:00', '2017-07-03 13:45:00', 20, '2017-07-03 00:00:00', '2017-07-03 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-04 7:00:00', '2017-07-04 8:15:00', '2017-07-04 12:00:00', '2017-07-04 13:45:00', 20, '2017-07-04 00:00:00', '2017-07-04 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-05 7:00:00', '2017-07-05 8:15:00', '2017-07-05 12:00:00', '2017-07-05 13:45:00', 20, '2017-07-05 00:00:00', '2017-07-05 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-06 7:00:00', '2017-07-06 8:15:00', '2017-07-06 12:00:00', '2017-07-06 13:45:00', 20, '2017-07-06 00:00:00', '2017-07-06 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-07 7:00:00', '2017-07-07 8:15:00', '2017-07-07 12:00:00', '2017-07-07 13:45:00', 20, '2017-07-07 00:00:00', '2017-07-07 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-10 7:00:00', '2017-07-10 8:15:00', '2017-07-10 12:00:00', '2017-07-10 13:45:00', 20, '2017-07-10 00:00:00', '2017-07-10 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-11 7:00:00', '2017-07-11 8:15:00', '2017-07-11 12:00:00', '2017-07-11 13:45:00', 20, '2017-07-11 00:00:00', '2017-07-11 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-12 7:00:00', '2017-07-12 8:15:00', '2017-07-12 12:00:00', '2017-07-12 13:45:00', 20, '2017-07-12 00:00:00', '2017-07-12 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-13 7:00:00', '2017-07-13 8:15:00', '2017-07-13 12:00:00', '2017-07-13 13:45:00', 20, '2017-07-13 00:00:00', '2017-07-13 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-14 7:00:00', '2017-07-14 8:15:00', '2017-07-14 12:00:00', '2017-07-14 13:45:00', 20, '2017-07-14 00:00:00', '2017-07-14 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-17 7:00:00', '2017-07-17 8:15:00', '2017-07-17 12:00:00', '2017-07-17 13:45:00', 20, '2017-07-17 00:00:00', '2017-07-17 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-18 7:00:00', '2017-07-18 8:15:00', '2017-07-18 12:00:00', '2017-07-18 13:45:00', 20, '2017-07-18 00:00:00', '2017-07-18 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-19 7:00:00', '2017-07-19 8:15:00', '2017-07-19 12:00:00', '2017-07-19 13:45:00', 20, '2017-07-19 00:00:00', '2017-07-19 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-20 7:00:00', '2017-07-20 8:15:00', '2017-07-20 12:00:00', '2017-07-20 13:45:00', 20, '2017-07-20 00:00:00', '2017-07-20 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-21 7:00:00', '2017-07-21 8:15:00', '2017-07-21 12:00:00', '2017-07-21 13:45:00', 20, '2017-07-21 00:00:00', '2017-07-21 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-24 7:00:00', '2017-07-24 8:15:00', '2017-07-24 12:00:00', '2017-07-24 13:45:00', 20, '2017-07-24 00:00:00', '2017-07-24 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-25 7:00:00', '2017-07-25 8:15:00', '2017-07-25 12:00:00', '2017-07-25 13:45:00', 20, '2017-07-25 00:00:00', '2017-07-25 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-26 7:00:00', '2017-07-26 8:15:00', '2017-07-26 12:00:00', '2017-07-26 13:45:00', 20, '2017-07-26 00:00:00', '2017-07-26 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-27 7:00:00', '2017-07-27 8:15:00', '2017-07-27 12:00:00', '2017-07-27 13:45:00', 20, '2017-07-27 00:00:00', '2017-07-27 00:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-28 7:00:00', '2017-07-28 8:15:00', '2017-07-28 12:00:00', '2017-07-28 13:45:00', 20, '2017-07-28 00:00:00', '2017-07-28 00:00:00');


INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME) VALUES ('2017-01-01 00:00:00', 'Feiertag: Neujahr', '2017-01-01 00:00:00', TRUE, 'cheng', 'cheng');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME) VALUES ('2017-01-06 00:00:00', 'Feiertag: Heilige Drei Könige', '2017-01-06 00:00:00', TRUE, 'cheng', 'cheng');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME) VALUES ('2017-03-19 00:00:00', 'Feiertag: Josef', '2017-03-19 00:00:00', TRUE, 'cheng', 'cheng');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME) VALUES ('2017-04-14 00:00:00', 'Feiertag: Karfreitag', '2017-04-14 00:00:00', TRUE, 'cheng', 'cheng');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME) VALUES ('2017-04-16 00:00:00', 'Feiertag: Ostersonntag', '2017-04-16 00:00:00', TRUE, 'cheng', 'cheng');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME) VALUES ('2017-04-17 00:00:00', 'Feiertag: Ostermontag', '2017-04-17 00:00:00', TRUE, 'cheng', 'cheng');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME) VALUES ('2017-05-01 00:00:00', 'Feiertag: Staatsfeiertag', '2017-05-01 00:00:00', TRUE, 'cheng', 'cheng');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME) VALUES ('2017-05-25 00:00:00', 'Feiertag: Christi Himmelfahrt', '2017-05-25 00:00:00', TRUE, 'cheng', 'cheng');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME) VALUES ('2017-06-04 00:00:00', 'Feiertag: Pfingstsonntag', '2017-06-04 00:00:00', TRUE, 'cheng', 'cheng');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME) VALUES ('2017-06-05 00:00:00', 'Feiertag: Pfingstmontag', '2017-06-05 00:00:00', TRUE, 'cheng', 'cheng');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME) VALUES ('2017-06-15 00:00:00', 'Feiertag: Fronleichnam', '2017-06-15 00:00:00', TRUE, 'cheng', 'cheng');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME) VALUES ('2017-08-15 00:00:00', 'Feiertag: Mariä Himmelfahrt', '2017-08-15 00:00:00', TRUE, 'cheng', 'cheng');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME) VALUES ('2017-10-26 00:00:00', 'Feiertag: Nationalfeiertag', '2017-10-26 00:00:00', TRUE, 'cheng', 'cheng');


INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME) VALUES ('2017-06-06 00:00:00', 'Ferientag: Pfingstdienstag', '2017-06-06 00:00:00', TRUE, 'cheng', 'cheng');

