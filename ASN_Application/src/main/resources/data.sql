INSERT INTO user_data (FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME, PASSWORD, USER_ROLE) VALUES('Admin', 'Istrator', 'admin', 'Innsbruck', '6020', 'Tiergartenstrasse', '$2a$10$W9xQIwa/FstPUvcbzJXnQ.XjVdTyIcCEp.g6VCq1gYuSsQNjJjbJG', 'ADMIN');

INSERT INTO user_data (FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME, PASSWORD, USER_ROLE) VALUES('Bernardo', 'Menia', 'bernd', 'Innsbruck', '6020', 'Technikerstrasse', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'PARENT');
INSERT INTO parent (FAMILY_STATUS, IMG_NAME, STATUS, USERNAME) VALUES ('MARRIED', 'john.jpg',TRUE, 'bernd');
INSERT INTO user_data (FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME, PASSWORD, USER_ROLE) VALUES('Ashley', 'McCain', 'ashley', 'Innsbruck', '6020', 'Bundesstrasse', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'PARENT');
INSERT INTO parent (FAMILY_STATUS,  IMG_NAME, STATUS, USERNAME) VALUES ('MARRIED', 'pete.jpg',TRUE, 'ashley');
INSERT INTO user_data (FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME, PASSWORD, USER_ROLE) VALUES('Mohammed', 'Li', 'mohammed', 'Innsbruck', '6020', 'Bahnhofstraße', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'PARENT');
INSERT INTO parent (FAMILY_STATUS, IMG_NAME, STATUS, USERNAME) VALUES ('MARRIED', 'luke.jpg',TRUE, 'mohammed');

INSERT INTO user_data (FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME, PASSWORD, USER_ROLE) VALUES('Thorunn', 'Einarsson', 'thorunn', 'Innsbruck', '6020', 'Sillpark', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'EMPLOYEE');
INSERT INTO employee (RELIGION, PHONE_NUMBER, USERNAME) VALUES ('CHRISTIANITY', '0660123456', 'thorunn');
INSERT INTO user_data (FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME, PASSWORD, USER_ROLE) VALUES('Cheng', 'Lee', 'cheng', 'Innsbruck', '6020', 'Meinhardstrasse', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'EMPLOYEE');
INSERT INTO employee (RELIGION, PHONE_NUMBER, USERNAME) VALUES ('BUDDHISM', '0660123456', 'cheng');
INSERT INTO user_data (FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME, PASSWORD, USER_ROLE) VALUES('Fatima', 'Abdullah', 'fatima', 'Innsbruck', '6020', 'Bozener Platz', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'EMPLOYEE');
INSERT INTO employee (RELIGION, PHONE_NUMBER, USERNAME) VALUES ('ISLAM', '0660123456', 'fatima');

INSERT INTO child (FIRST_NAME, LAST_NAME, PARENT1_USERNAME, BIRTHDAY) VALUES('Lena', 'Rädler', 'bernd', '11/11/2015');

INSERT INTO private_message (MESSAGE, USERNAME_RECEIVER, USERNAME_SENDER) VALUES('Hallo', 'cheng', 'cheng');
INSERT INTO private_message (MESSAGE, USERNAME_RECEIVER, USERNAME_SENDER) VALUES('Hallo2', 'cheng', 'cheng');
INSERT INTO private_message (MESSAGE, USERNAME_RECEIVER, USERNAME_SENDER) VALUES('Hallo3', 'cheng', 'cheng');

