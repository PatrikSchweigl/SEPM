# SEPM Group 4 - Team 1

###### Members:
- Lukas AUKENTHALER
- Stefan MATTERSBERGER
- Bernd MENIA
- Patrik SCHWEIGL
- Emanuel STRIEDNIG

This project is about developing an administrative software for nurseries.
The project is based on the project "sepm_skeleton", which was offered to us 
and made the start easier.


The project utilizes Spring Boot and is configured as maven web application project with:
- all relevant Spring Framework features enabled
- embedded Tomcat with support for JSF2
- mySQL database
- support for PrimeFaces

Just execute  `mvn spring-boot:run` to start the project and connect to
http://localhost:8080/ to access the nurseries web application. Further, you have to
preload some data into the database. 

In `vagrant`, start MYSQL with `mysql -u root -pasnTeam1` and 
execute following line: *source /vagrant/src/main/resources/data.sql*.

Then you are able to login with all different types of User.

| username | password  | role     |
| ---------|:---------:| --------:|
| fatima   | passwd    | employee |
| cheng    | passwd    | employee |
| thorunn  | passwd    | employee |
| ---------|:---------:| --------:|
| ashley   | passwd    | parent   |
| mohammed | passwd    | parent   |
| bernd    | passwd    | parent   |
| ---------|:---------:| --------:|
| admin    | passwd    | admin    |