INSERT INTO UserData (FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, USER_ROLE) VALUES('Admin', 'Istrator', 'admin', '$2a$10$W9xQIwa/FstPUvcbzJXnQ.XjVdTyIcCEp.g6VCq1gYuSsQNjJjbJG', 'ADMIN');
INSERT INTO UserData (FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, USER_ROLE) VALUES('Bernd', 'Menia', 'bernd', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'PARENT');
INSERT INTO PARENT (FAMILY_STATUS, LOCATION, POSTCODE, STREET_NAME, IMG_NAME, STATUS, ID) VALUES ('MARRIED', 'Innsbruck', '6020', 'Bahnhofstraße', 'john.jpg',TRUE, 2);
INSERT INTO UserData (FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, USER_ROLE) VALUES('Emanuel', 'Striednig', 'emanuel', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'PARENT');
INSERT INTO PARENT (FAMILY_STATUS, LOCATION, POSTCODE, STREET_NAME, IMG_NAME, STATUS, ID) VALUES ('NOT_MARRIED', 'Innsbruck', '6020', 'Bahnhofstraße', 'pete.jpg',TRUE, 3);
INSERT INTO UserData (FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, USER_ROLE) VALUES('Lukas', 'Aukenthaler', 'lukas', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'PARENT');
INSERT INTO PARENT (FAMILY_STATUS, LOCATION, POSTCODE, STREET_NAME, IMG_NAME, STATUS, ID) VALUES ('MARRIED', 'Innsbruck', '6020', 'Bahnhofstraße', 'luke.jpg',TRUE, 4);
INSERT INTO UserData (FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, USER_ROLE) VALUES('Bernd', 'Menia', 'bernd', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'EMPLOYEE');
INSERT INTO EMPLOYEE (RELIGION, LOCATION, POSTCODE, STREET_NAME, PHONE_NUMBER, ID) VALUES ('CHRISTIANITY', 'Innsbruck', '6020', 'Bahnhofstraße', '0660123456', 5);
INSERT INTO UserData (FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, USER_ROLE) VALUES('Emanuel', 'Striednig', 'emanuel', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'EMPLOYEE');
INSERT INTO EMPLOYEE (RELIGION, LOCATION, POSTCODE, STREET_NAME, PHONE_NUMBER, ID) VALUES ('CHRISTIANITY', 'Innsbruck', '6020', 'Bahnhofstraße', '0660123456', 6);
INSERT INTO UserData (FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, USER_ROLE) VALUES('Lukas', 'Aukenthaler', 'lukas', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'EMPLOYEE');
INSERT INTO EMPLOYEE (RELIGION, LOCATION, POSTCODE, STREET_NAME, PHONE_NUMBER, ID) VALUES ('CHRISTIANITY', 'Innsbruck', '6020', 'Bahnhofstraße', '0660123456', 7);
INSERT INTO CHILD (FIRST_NAME, LAST_NAME, PARENT1, PARENT2, CUSTODY) VALUES('Lena', 'Raedler', 3, 4, 'BOTH')



