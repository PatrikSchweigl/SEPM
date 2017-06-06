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

It is highly recommended to start the project on a Virtual Machine. To do so a `Vagrantfile` is provided
in the application directory (ASN_Application). Just execute `vagrant up` it may take a few minutes. After that jump into
the virtual machine with `vagrant ssh` and change the directory with `cd /vagrant`. Now  boot the project
with `mvn spring-boot:run`. Make sure that line 8 and 11 from the `application.properties` are enabled 
before the build-process (Hint: disable line 8 and 11 before further boots to make sure the DB is persistent). After that 
it is necessary to load data into the DB. To do so, just execute `loadData.sh` on vagrant from ubuntu bash or import `data.sql` 
via mysql shell with `mysql -u root -pasnTeam1` and execute following line: *source /vagrant/src/main/resources/data.sql*.

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
