INSERT INTO user_data (FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME, PASSWORD, USER_ROLE) VALUES('Admin', 'Istrator', 'admin', 'Innsbruck', '6020', 'Tiergartenstrasse', '$2a$10$W9xQIwa/FstPUvcbzJXnQ.XjVdTyIcCEp.g6VCq1gYuSsQNjJjbJG', 'ADMIN');

INSERT INTO user_data (IMG_NAME, FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME, PASSWORD, USER_ROLE) VALUES('emptypicture.png', 'Bernardo', 'Menia', 'bernd', 'Innsbruck', '6020', 'Technikerstrasse', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'PARENT');
INSERT INTO parent (FAMILY_STATUS, STATUS, USERNAME) VALUES ('VERHEIRATET', TRUE, 'bernd');
INSERT INTO user_data (IMG_NAME, FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME, PASSWORD, USER_ROLE) VALUES('emptypicture.png','Ashley', 'McCain', 'ashley', 'Innsbruck', '6020', 'Bundesstrasse', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'PARENT');
INSERT INTO parent (FAMILY_STATUS, STATUS, USERNAME) VALUES ('VERHEIRATET',TRUE, 'ashley');
INSERT INTO user_data (IMG_NAME, FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME, PASSWORD, USER_ROLE) VALUES('emptypicture.png','Mohammed', 'Li', 'mohammed', 'Innsbruck', '6020', 'Bahnhofstraße', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'PARENT');
INSERT INTO parent (FAMILY_STATUS, STATUS, USERNAME) VALUES ('VERHEIRATET', TRUE, 'mohammed');

INSERT INTO user_data (IMG_NAME, RELIGION, FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME, PASSWORD, USER_ROLE) VALUES('emptypicture.png','CHRISTENTUM','Thorunn', 'Einarsson', 'thorunn', 'Innsbruck', '6020', 'Sillpark', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'EMPLOYEE');
INSERT INTO employee (USERNAME) VALUES ('thorunn');
INSERT INTO user_data (IMG_NAME, RELIGION, FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME, PASSWORD, USER_ROLE) VALUES('emptypicture.png','BUDDHISMUS','Cheng', 'Lee', 'cheng', 'Innsbruck', '6020', 'Meinhardstrasse', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'EMPLOYEE');
INSERT INTO employee (USERNAME) VALUES ('cheng');
INSERT INTO user_data (IMG_NAME, RELIGION, FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME, PASSWORD, USER_ROLE) VALUES('emptypicture.png','ISLAM','Fatima', 'Abdullah', 'fatima', 'Innsbruck', '6020', 'Bozener Platz', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'EMPLOYEE');
INSERT INTO employee (USERNAME) VALUES ('fatima');

INSERT INTO child (FIRST_NAME, LAST_NAME, PARENT1_USERNAME, BIRTHDAY) VALUES('Lena', 'Rädler', 'bernd', '11/11/2015');
INSERT INTO child (FIRST_NAME, LAST_NAME, PARENT1_USERNAME, BIRTHDAY) VALUES('Sakura', 'Xiaping', 'mohammed', '03/05/2014');
INSERT INTO child (FIRST_NAME, LAST_NAME, PARENT1_USERNAME, BIRTHDAY) VALUES('Fidel', 'Castro', 'ashley', '26/09/2014');
INSERT INTO child (FIRST_NAME, LAST_NAME, PARENT1_USERNAME, BIRTHDAY) VALUES('Peter', 'Hanser', 'bernd', '02/03/2013');

INSERT INTO private_message (MESSAGE, USERNAME_RECEIVER, USERNAME_SENDER) VALUES('Hallo', 'cheng', 'cheng');
INSERT INTO private_message (MESSAGE, USERNAME_RECEIVER, USERNAME_SENDER) VALUES('Hallo2', 'cheng', 'cheng');
INSERT INTO private_message (MESSAGE, USERNAME_RECEIVER, USERNAME_SENDER) VALUES('Hallo3', 'cheng', 'cheng');

INSERT INTO lunch (DATE, MEAL, COST) VALUES('28/05/2017', 'Lasagne', 3.5);
INSERT INTO lunch (DATE, MEAL, COST) VALUES('29/05/2017', 'Dürüm', 3.0);
INSERT INTO lunch (DATE, MEAL, COST) VALUES('30/05/2017', 'Spaghetti', 3.5);

