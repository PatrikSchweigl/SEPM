INSERT INTO USER (FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, USER_ROLE) VALUES('Admin', 'Istrator', 'admin', 'passwd', 'ADMIN')
INSERT INTO USER (FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, USER_ROLE) VALUES('Bernd', 'Menia', 'bernd', 'passwd', 'ANGESTELLTER')
INSERT INTO EMPLOYEE (BIRTHDAY, FAMILY_STATUS, LOCATION, PHONE_NUMBER, POSTCODE, RELIGION, ROLE, STREET_NAME, WORKING_STATE, ID) VALUES ('2016-01-01 00:00:00', 'VERHEIRATET', 'Innsbruck', '0900666666', '6020', 'ATHEISMUS', 'PÄDAGOGE', 'Bahnhofstraße', 'ANWESEND', SELECT ID FROM USER WHERE USERNAME = 'bernd')







