INSERT INTO USER (FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, USER_ROLE) VALUES('Admin', 'Istrator', 'admin', '$2a$10$W9xQIwa/FstPUvcbzJXnQ.XjVdTyIcCEp.g6VCq1gYuSsQNjJjbJG', 'ADMIN')
INSERT INTO USER (FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, USER_ROLE) VALUES('Bernd', 'Menia', 'bernd', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'PARENT')
INSERT INTO PARENT (FAMILY_STATUS, LOCATION, POSTCODE, STREET_NAME, IMG_NAME, STATUS, ID) VALUES ('MARRIED', 'Innsbruck', '6020', 'Bahnhofstraße', 'john.jpg',TRUE, SELECT ID FROM USER WHERE USERNAME = 'bernd')







