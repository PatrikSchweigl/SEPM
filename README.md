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

Just run vagrant up to install our application. If you want to skip the tests, you have to comment the line `mvn test` out of `Vagrantfile`
Now the application is accessible via http://192.168.33.10:8080 and you are able to login with all different 
types of Users below.

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

If there are any problems please do not hesitate to get in touch with us.
