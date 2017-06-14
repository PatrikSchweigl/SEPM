INSERT INTO user_data (FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME,  EMAIL,PASSWORD, USER_ROLE) VALUES('Admin', 'Istrator', 'admin', 'Innsbruck', '6020', 'Tiergartenstrasse','xyzdafsasdf233223@abc.at', '$2a$10$W9xQIwa/FstPUvcbzJXnQ.XjVdTyIcCEp.g6VCq1gYuSsQNjJjbJG', 'ADMIN');

INSERT INTO user_data (IMG_NAME, FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME, EMAIL, PASSWORD, USER_ROLE) VALUES('emptypicture.png', 'Bernardo', 'Menia', 'bernd', 'Innsbruck', '6020', 'Technikerstrasse', 'patrik.schweigl@student.uibk.ac.at', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'PARENT');
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


INSERT INTO lunch (DATE, MEAL, COST) VALUES(STR_TO_DATE('07/06/2017:02:00:00', '%d/%m/%Y:%h:%i:%s'), 'Wiener Schnitzel', 3.5);
INSERT INTO lunch (DATE, MEAL, COST) VALUES(STR_TO_DATE('08/06/2017:02:00:00', '%d/%m/%Y:%h:%i:%s'), 'Gemüselasagne', 3.0);
INSERT INTO lunch (DATE, MEAL, COST) VALUES(STR_TO_DATE('09/06/2017:02:00:00', '%d/%m/%Y:%h:%i:%s'), 'Blätterteigtaschen', 3.5);
INSERT INTO lunch (DATE, MEAL, COST) VALUES(STR_TO_DATE('12/06/2017:02:00:00', '%d/%m/%Y:%h:%i:%s'), 'Lasagne', 3.5);
INSERT INTO lunch (DATE, MEAL, COST) VALUES(STR_TO_DATE('13/06/2017:02:00:00', '%d/%m/%Y:%h:%i:%s'), 'Spaghetti', 3.5);
INSERT INTO lunch (DATE, MEAL, COST) VALUES(STR_TO_DATE('14/06/2017:02:00:00', '%d/%m/%Y:%h:%i:%s'), 'Wiener Schnitzel', 3.5);
INSERT INTO lunch (DATE, MEAL, COST) VALUES(STR_TO_DATE('16/06/2017:02:00:00', '%d/%m/%Y:%h:%i:%s'), 'Blätterteigtaschen', 3.5);
INSERT INTO lunch (DATE, MEAL, COST) VALUES(STR_TO_DATE('19/06/2017:02:00:00', '%d/%m/%Y:%h:%i:%s'), 'Lasagne', 3.5);
INSERT INTO lunch (DATE, MEAL, COST) VALUES(STR_TO_DATE('20/06/2017:02:00:00', '%d/%m/%Y:%h:%i:%s'), 'Spaghetti', 3.5);
INSERT INTO lunch (DATE, MEAL, COST) VALUES(STR_TO_DATE('21/06/2017:02:00:00', '%d/%m/%Y:%h:%i:%s'), 'Wiener Schnitzel', 3.5);
INSERT INTO lunch (DATE, MEAL, COST) VALUES(STR_TO_DATE('22/06/2017:02:00:00', '%d/%m/%Y:%h:%i:%s'), 'Gemüselasagne', 3.0);
INSERT INTO lunch (DATE, MEAL, COST) VALUES(STR_TO_DATE('23/06/2017:02:00:00', '%d/%m/%Y:%h:%i:%s'), 'Blätterteigtaschen', 3.5);
INSERT INTO lunch (DATE, MEAL, COST) VALUES(STR_TO_DATE('26/06/2017:02:00:00', '%d/%m/%Y:%h:%i:%s'), 'Lasagne', 3.5);
INSERT INTO lunch (DATE, MEAL, COST) VALUES(STR_TO_DATE('27/06/2017:02:00:00', '%d/%m/%Y:%h:%i:%s'), 'Spaghetti', 3.5);
INSERT INTO lunch (DATE, MEAL, COST) VALUES(STR_TO_DATE('28/06/2017:02:00:00', '%d/%m/%Y:%h:%i:%s'), 'Wiener Schnitzel', 3.5);
INSERT INTO lunch (DATE, MEAL, COST) VALUES(STR_TO_DATE('29/06/2017:02:00:00', '%d/%m/%Y:%h:%i:%s'), 'Gemüselasagne', 3.0);
INSERT INTO lunch (DATE, MEAL, COST) VALUES(STR_TO_DATE('30/06/2017:02:00:00', '%d/%m/%Y:%h:%i:%s'), 'Blätterteigtaschen', 3.5);

INSERT INTO lunch_children_ids(LUNCH_ID, CHILDREN_IDS) VALUES(1,1);
INSERT INTO lunch_children_ids(LUNCH_ID, CHILDREN_IDS) VALUES(2,1);
INSERT INTO lunch_children_ids(LUNCH_ID, CHILDREN_IDS) VALUES(3,1);
INSERT INTO lunch_children_ids(LUNCH_ID, CHILDREN_IDS) VALUES(4,1);
INSERT INTO lunch_children_ids(LUNCH_ID, CHILDREN_IDS) VALUES(5,1);

INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-01 09:00:00', '2017-06-01 10:15:00', '2017-06-01 14:00:00', '2017-06-01 15:45:00', 20, '2017-06-01 02:00:00', '2017-06-01 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-02 09:00:00', '2017-06-02 10:15:00', '2017-06-02 14:00:00', '2017-06-02 15:45:00', 20, '2017-06-02 02:00:00', '2017-06-02 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-07 09:00:00', '2017-06-07 10:15:00', '2017-06-07 14:00:00', '2017-06-07 15:45:00', 20, '2017-06-07 02:00:00', '2017-06-07 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-08 09:00:00', '2017-06-08 10:15:00', '2017-06-08 14:00:00', '2017-06-08 15:45:00', 20, '2017-06-08 02:00:00', '2017-06-08 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-09 09:00:00', '2017-06-09 10:15:00', '2017-06-09 14:00:00', '2017-06-09 15:45:00', 20, '2017-06-09 02:00:00', '2017-06-09 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-12 09:00:00', '2017-06-12 10:15:00', '2017-06-12 14:00:00', '2017-06-12 15:45:00', 20, '2017-06-12 02:00:00', '2017-06-12 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-13 09:00:00', '2017-06-13 10:15:00', '2017-06-13 14:00:00', '2017-06-13 15:45:00', 20, '2017-06-13 02:00:00', '2017-06-13 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-14 09:00:00', '2017-06-14 10:15:00', '2017-06-14 14:00:00', '2017-06-14 15:45:00', 20, '2017-06-14 02:00:00', '2017-06-14 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-16 09:00:00', '2017-06-16 10:15:00', '2017-06-16 14:00:00', '2017-06-16 15:45:00', 20, '2017-06-16 02:00:00', '2017-06-16 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-19 09:00:00', '2017-06-19 10:15:00', '2017-06-19 14:00:00', '2017-06-19 15:45:00', 20, '2017-06-19 02:00:00', '2017-06-19 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-20 09:00:00', '2017-06-20 10:15:00', '2017-06-20 14:00:00', '2017-06-20 15:45:00', 20, '2017-06-20 02:00:00', '2017-06-20 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-21 09:00:00', '2017-06-21 10:15:00', '2017-06-21 14:00:00', '2017-06-21 15:45:00', 20, '2017-06-21 02:00:00', '2017-06-21 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-22 09:00:00', '2017-06-22 10:15:00', '2017-06-22 14:00:00', '2017-06-22 15:45:00', 20, '2017-06-22 02:00:00', '2017-06-22 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-23 09:00:00', '2017-06-23 10:15:00', '2017-06-23 14:00:00', '2017-06-23 15:45:00', 20, '2017-06-23 02:00:00', '2017-06-23 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-26 09:00:00', '2017-06-26 10:15:00', '2017-06-26 14:00:00', '2017-06-26 15:45:00', 20, '2017-06-26 02:00:00', '2017-06-26 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-27 09:00:00', '2017-06-27 10:15:00', '2017-06-27 14:00:00', '2017-06-27 15:45:00', 20, '2017-06-27 02:00:00', '2017-06-27 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-28 09:00:00', '2017-06-28 10:15:00', '2017-06-28 14:00:00', '2017-06-28 15:45:00', 20, '2017-06-28 02:00:00', '2017-06-28 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-29 09:00:00', '2017-06-29 10:15:00', '2017-06-29 14:00:00', '2017-06-29 15:45:00', 20, '2017-06-29 02:00:00', '2017-06-29 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-06-30 09:00:00', '2017-06-30 10:15:00', '2017-06-30 14:00:00', '2017-06-30 15:45:00', 20, '2017-06-30 02:00:00', '2017-06-30 02:00:00');

INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-03 09:00:00', '2017-07-03 10:15:00', '2017-07-03 14:00:00', '2017-07-03 15:45:00', 20, '2017-07-03 02:00:00', '2017-07-03 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-04 09:00:00', '2017-07-04 10:15:00', '2017-07-04 14:00:00', '2017-07-04 15:45:00', 20, '2017-07-04 02:00:00', '2017-07-04 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-05 09:00:00', '2017-07-05 10:15:00', '2017-07-05 14:00:00', '2017-07-05 15:45:00', 20, '2017-07-05 02:00:00', '2017-07-05 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-06 09:00:00', '2017-07-06 10:15:00', '2017-07-06 14:00:00', '2017-07-06 15:45:00', 20, '2017-07-06 02:00:00', '2017-07-06 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-07 09:00:00', '2017-07-07 10:15:00', '2017-07-07 14:00:00', '2017-07-07 15:45:00', 20, '2017-07-07 02:00:00', '2017-07-07 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-10 09:00:00', '2017-07-10 10:15:00', '2017-07-10 14:00:00', '2017-07-10 15:45:00', 20, '2017-07-10 02:00:00', '2017-07-10 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-11 09:00:00', '2017-07-11 10:15:00', '2017-07-11 14:00:00', '2017-07-11 15:45:00', 20, '2017-07-11 02:00:00', '2017-07-11 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-12 09:00:00', '2017-07-12 10:15:00', '2017-07-12 14:00:00', '2017-07-12 15:45:00', 20, '2017-07-12 02:00:00', '2017-07-12 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-13 09:00:00', '2017-07-13 10:15:00', '2017-07-13 14:00:00', '2017-07-13 15:45:00', 20, '2017-07-13 02:00:00', '2017-07-13 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-14 09:00:00', '2017-07-14 10:15:00', '2017-07-14 14:00:00', '2017-07-14 15:45:00', 20, '2017-07-14 02:00:00', '2017-07-14 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-17 09:00:00', '2017-07-17 10:15:00', '2017-07-17 14:00:00', '2017-07-17 15:45:00', 20, '2017-07-17 02:00:00', '2017-07-17 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-18 09:00:00', '2017-07-18 10:15:00', '2017-07-18 14:00:00', '2017-07-18 15:45:00', 20, '2017-07-18 02:00:00', '2017-07-18 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-19 09:00:00', '2017-07-19 10:15:00', '2017-07-19 14:00:00', '2017-07-19 15:45:00', 20, '2017-07-19 02:00:00', '2017-07-19 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-20 09:00:00', '2017-07-20 10:15:00', '2017-07-20 14:00:00', '2017-07-20 15:45:00', 20, '2017-07-20 02:00:00', '2017-07-20 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-21 09:00:00', '2017-07-21 10:15:00', '2017-07-21 14:00:00', '2017-07-21 15:45:00', 20, '2017-07-21 02:00:00', '2017-07-21 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-24 09:00:00', '2017-07-24 10:15:00', '2017-07-24 14:00:00', '2017-07-24 15:45:00', 20, '2017-07-24 02:00:00', '2017-07-24 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-25 09:00:00', '2017-07-25 10:15:00', '2017-07-25 14:00:00', '2017-07-25 15:45:00', 20, '2017-07-25 02:00:00', '2017-07-25 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-26 09:00:00', '2017-07-26 10:15:00', '2017-07-26 14:00:00', '2017-07-26 15:45:00', 20, '2017-07-26 02:00:00', '2017-07-26 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-27 09:00:00', '2017-07-27 10:15:00', '2017-07-27 14:00:00', '2017-07-27 15:45:00', 20, '2017-07-27 02:00:00', '2017-07-27 02:00:00');
INSERT INTO nursery_information (BRING_START, BRING_END, PICK_UP_START, PICK_UP_END, MAX_OCCUPANCY, ORIGIN_DATE, TODAYS_DATE) VALUES ('2017-07-28 09:00:00', '2017-07-28 10:15:00', '2017-07-28 14:00:00', '2017-07-28 15:45:00', 20, '2017-07-28 02:00:00', '2017-07-28 02:00:00');


INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME, STRING_ID) VALUES ('2017-01-01 02:00:00', 'Feiertag: Neujahr', '2017-01-01 02:00:00', TRUE, 'cheng', 'cheng', '0eb8eddf-fa64-4ce4-ab00-d82afdd3fd07');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME, STRING_ID) VALUES ('2017-01-06 02:00:00', 'Feiertag: Heilige Drei Könige', '2017-01-06 02:00:00', TRUE, 'cheng', 'cheng', '0eb8e00f-fa64-4ce4-ab00-d82afdd3fd07');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME, STRING_ID) VALUES ('2017-03-19 02:00:00', 'Feiertag: Josef', '2017-03-19 02:00:00', TRUE, 'cheng', 'cheng', '0eb8eddf-fa64-4ce4-ab00-d82afdd3fd08');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME, STRING_ID) VALUES ('2017-04-14 02:00:00', 'Feiertag: Karfreitag', '2017-04-14 02:00:00', TRUE, 'cheng', 'cheng', '0eb8eddf-fa64-4ce4-ab00-d82afdd3fd09');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME, STRING_ID) VALUES ('2017-04-16 02:00:00', 'Feiertag: Ostersonntag', '2017-04-16 02:00:00', TRUE, 'cheng', 'cheng', '0eb8eddf-fa64-4ce4-ab00-d82afdd3fd11');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME, STRING_ID) VALUES ('2017-04-17 02:00:00', 'Feiertag: Ostermontag', '2017-04-17 02:00:00', TRUE, 'cheng', 'cheng', '0eb8eddf-fa64-4ce4-ab00-d82afdd3fd12');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME, STRING_ID) VALUES ('2017-05-01 02:00:00', 'Feiertag: Staatsfeiertag', '2017-05-01 02:00:00', TRUE, 'cheng', 'cheng', '0eb8eddf-fa64-4ce4-ab00-d82afdd3fd34');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME, STRING_ID) VALUES ('2017-05-25 02:00:00', 'Feiertag: Christi Himmelfahrt', '2017-05-25 02:00:00', TRUE, 'cheng', 'cheng', '0eb8eddf-fa64-4ce4-ab00-d82afdd3fd66');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME, STRING_ID) VALUES ('2017-06-04 02:00:00', 'Feiertag: Pfingstsonntag', '2017-06-04 02:00:00', TRUE, 'cheng', 'cheng', '0eb8eddf-fa64-4ce4-ab00-d42afdd3fd07');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME, STRING_ID) VALUES ('2017-06-05 02:00:00', 'Feiertag: Pfingstmontag', '2017-06-05 02:00:00', TRUE, 'cheng', 'cheng', '0eb8eddf-fa64-4ce4-ab00-d82afdd3fd27');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME, STRING_ID) VALUES ('2017-06-15 02:00:00', 'Feiertag: Fronleichnam', '2017-06-15 02:00:00', TRUE, 'cheng', 'cheng', '0eb8eddf-fa64-4ce4-ab00-d82afdd4fd07');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME, STRING_ID) VALUES ('2017-08-15 02:00:00', 'Feiertag: Mariä Himmelfahrt', '2017-08-15 02:00:00', TRUE, 'cheng', 'cheng', '0eb8eddf-fa64-4ce4-ab00-d82afdd3fe07');
INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME, STRING_ID) VALUES ('2017-10-26 02:00:00', 'Feiertag: Nationalfeiertag', '2017-10-26 02:00:00', TRUE, 'cheng', 'cheng', '0eb8eddf-fa64-4ce4-ab00-d82afdd3fd30');


INSERT INTO task (BEGIN_DATE, DESCRIPTION, ENDING_DATE, IMPORTANT, RECEIVER_USERNAME, SENDER_USERNAME, STRING_ID) VALUES ('2017-06-06 02:00:00', 'Ferientag: Pfingstdienstag', '2017-06-06 02:00:00', TRUE, 'cheng', 'cheng', '0eb8eddf-fa64-4ce4-ab00-d82afdd3fd50');