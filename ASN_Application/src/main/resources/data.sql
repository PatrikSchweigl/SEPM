INSERT INTO user_data (FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME, PASSWORD, USER_ROLE) VALUES('Admin', 'Istrator', 'admin', 'Innsbruck', '6020', 'Tiergartenstrasse', '$2a$10$W9xQIwa/FstPUvcbzJXnQ.XjVdTyIcCEp.g6VCq1gYuSsQNjJjbJG', 'ADMIN');
INSERT INTO user_data (FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME, PASSWORD, USER_ROLE) VALUES('Bernd', 'Menia', 'bernd', 'Innsbruck', '6020', 'Technikerstrasse', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'PARENT');
INSERT INTO parent (FAMILY_STATUS, IMG_NAME, STATUS, ID) VALUES ('MARRIED', 'john.jpg',TRUE, 2);
INSERT INTO user_data (FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME, PASSWORD, USER_ROLE) VALUES('Emanuel', 'Striednig', 'emanuel', 'Innsbruck', '6020', 'Bundesstrasse', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'PARENT');
INSERT INTO parent (FAMILY_STATUS,  IMG_NAME, STATUS, ID) VALUES ('MARRIED', 'pete.jpg',TRUE, 3);
INSERT INTO user_data (FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME, PASSWORD, USER_ROLE) VALUES('Lukas', 'Aukenthaler', 'lukas', 'Innsbruck', '6020', 'Bahnhofstraße', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'PARENT');
INSERT INTO parent (FAMILY_STATUS, IMG_NAME, STATUS, ID) VALUES ('MARRIED', 'luke.jpg',TRUE, 4);
INSERT INTO user_data (FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME, PASSWORD, USER_ROLE) VALUES('Bernd', 'Menia', 'bernd', 'Innsbruck', '6020', 'Sillpark', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'EMPLOYEE');
INSERT INTO employee (RELIGION, PHONE_NUMBER, ID) VALUES ('CHRISTIANITY', '0660123456', 5);
INSERT INTO user_data (FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME, PASSWORD, USER_ROLE) VALUES('Emanuel', 'Striednig', 'emanuel', 'Innsbruck', '6020', 'Meinhardstrasse', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'EMPLOYEE');
INSERT INTO employee (RELIGION, PHONE_NUMBER, ID) VALUES ('CHRISTIANITY', '0660123456', 6);
INSERT INTO user_data (FIRST_NAME, LAST_NAME, USERNAME, LOCATION, POSTCODE, STREET_NAME, PASSWORD, USER_ROLE) VALUES('Lukas', 'Aukenthaler', 'lukas', 'Innsbruck', '6020', 'Bozener Platz', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'EMPLOYEE');
INSERT INTO employee (RELIGION, PHONE_NUMBER, ID) VALUES ('CHRISTIANITY', '0660123456', 7); INSERT INTO child (FIRST_NAME, LAST_NAME, parent1_id, parent2_id, CUSTODY) VALUES('Lena', 'Raedler', 3, 4, 'BOTH')