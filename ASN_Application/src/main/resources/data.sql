INSERT INTO USER (FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, USER_ROLE) VALUES('Admin', 'Istrator', 'admin', '$2a$10$W9xQIwa/FstPUvcbzJXnQ.XjVdTyIcCEp.g6VCq1gYuSsQNjJjbJG', 'ADMIN')
INSERT INTO USER (FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, USER_ROLE) VALUES('Bernd', 'Menia', 'bernd', '$2a$10$6dQcayeT/JAFvgFvzIjlcew5z9cmdCrGlv57.BGnIKvPXTDsQm7hG', 'PARENT')
INSERT INTO EMPLOYEE (BIRTHDAY, FAMILY_STATUS, LOCATION, PHONE_NUMBER, POSTCODE, RELIGION, ROLE, STREET_NAME, WORKING_STATE, ID) VALUES ('2016-01-01 00:00:00', 'MARRIED', 'Innsbruck', '0900666666', '6020', 'ATHEISM', 'PEDAGOGUE', 'Bahnhofstraße', 'PRESENT', SELECT ID FROM USER WHERE USERNAME = 'bernd')







