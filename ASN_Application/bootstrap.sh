#!/usr/bin/env bash

sudo apt-get update
sudo apt-get -y upgrade
sudo add-apt-repository -y ppa:webupd8team/java
sudo apt-get update
echo debconf shared/accepted-oracle-license-v1-1 select true | sudo debconf-set-selections
echo debconf shared/accepted-oracle-license-v1-1 seen true | sudo debconf-set-selections
sudo apt-get install -y oracle-java8-installer
sudo apt-get install -y maven
sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password password asnTeam1'
sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password_again password asnTeam1'
sudo apt-get install -y mysql-server
mysql -u root -pasnTeam1 <<EOFMYSQL
DROP DATABASE IF EXISTS asndb;
CREATE DATABASE asndb;
USE asndb;

drop table if exists audit_log;
drop table if exists caregiver;
drop table if exists child;
drop table if exists comment;
drop table if exists employee;
drop table if exists lunch;
drop table if exists lunch_children_ids;
drop table if exists message;
drop table if exists nursery_information;
drop table if exists parent;
drop table if exists parent_tasks;
drop table if exists picture;
drop table if exists picture_comment;
drop table if exists private_message;
drop table if exists registration;
drop table if exists sibling;
drop table if exists task;
drop table if exists user_data;
create table audit_log (id bigint not null auto_increment, date datetime not null, log varchar(255) not null, user_name varchar(255) not null, primary key (id));
create table caregiver (id bigint not null auto_increment, eligible bit default false not null, first_name varchar(255) not null, img_name varchar(255), last_name varchar(255) not null, phone_number varchar(255) not null, relationship varchar(255), child_id bigint not null, primary key (id));
create table child (id bigint not null auto_increment, allergies varchar(255), birthday varchar(255) not null, custody varchar(255), emergency_number varchar(255) not null, first_name varchar(255) not null, food_intolerances varchar(255), gender varchar(255) not null, img_name varchar(255), last_name varchar(255) not null, religion varchar(255), parent1_username varchar(255) not null, parent2_username varchar(255), primary key (id));
create table comment (id bigint not null auto_increment, comment varchar(255) not null, date datetime not null, picture_name varchar(255), username varchar(255) not null, primary key (id));
create table employee (family_status varchar(255), work_role varchar(255), working_state varchar(255), username varchar(255) not null, primary key (username));
create table lunch (id bigint not null auto_increment, cost double precision not null, date datetime not null, meal varchar(255) not null, primary key (id));
create table lunch_children_ids (lunch_id bigint not null, children_ids bigint);
create table message (id bigint not null auto_increment, date datetime not null, message varchar(255) not null, username varchar(255) not null, primary key (id));
create table nursery_information (id bigint not null auto_increment, bring_end datetime not null, bring_start datetime not null, current_occupancy int default 0, max_occupancy integer not null, origin_date datetime not null, pick_up_end datetime not null, pick_up_start datetime not null, todays_date datetime not null, primary key (id));
create table parent (family_status varchar(255), status bit not null, username varchar(255) not null, primary key (username));
create table parent_tasks (parent_username varchar(255) not null, tasks_id bigint not null, primary key (parent_username, tasks_id));
create table picture (id bigint not null auto_increment, date datetime not null, title varchar(255) not null, url varchar(255) not null, publisher_username varchar(255) not null, primary key (id));
create table picture_comment (picture_id bigint not null, comment_id bigint not null, primary key (picture_id, comment_id));
create table private_message (id bigint not null auto_increment, date datetime, message varchar(255) not null, username_receiver varchar(255) not null, username_sender varchar(255) not null, primary key (id));
create table registration (id bigint not null auto_increment, bringdate datetime not null, date datetime not null, note varchar(255), child_id bigint not null, primary key (id));
create table sibling (id bigint not null auto_increment, birthday varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, child_id bigint not null, primary key (id));
create table task (id bigint not null auto_increment, begin_date datetime not null, description varchar(255) not null, ending_date datetime not null, important bit not null, string_id varchar(255), style_class varchar(255), task_status bit default false not null, receiver_username varchar(255) not null, sender_username varchar(255) not null, primary key (id));
create table user_data (username varchar(255) not null, birthday varchar(255), email varchar(255) not null, first_name varchar(255) not null, img_name varchar(255), last_name varchar(255) not null, location varchar(255) not null, notification bit default false not null, password varchar(255) not null, phone_number varchar(255), postcode varchar(255) not null, religion varchar(255), street_name varchar(255) not null, user_role varchar(255), primary key (username));
alter table parent_tasks add constraint UK_gx0fgmcxr1p655mqo0e5ch0k8 unique (tasks_id);
alter table picture_comment add constraint UK_3bsogwngr9mjpdr2p4biiut3m unique (comment_id);
alter table caregiver add constraint FK33330iy1oisq6dtbljqab933 foreign key (child_id) references child (id);
alter table child add constraint FKcpv3j0u8365com6tqh5ian880 foreign key (parent1_username) references parent (username);
alter table child add constraint FK75aqsk5cpogs5uh42d9l5v7yx foreign key (parent2_username) references parent (username);
alter table employee add constraint FKaup3c0g8flup4pm2cd4skskgf foreign key (username) references user_data (username);
alter table lunch_children_ids add constraint FK5s9gq3nerowvr43su84t0yru1 foreign key (lunch_id) references lunch (id);
alter table parent add constraint FKbycyj3vcahhdwhsvb0l2loynb foreign key (username) references user_data (username);
alter table parent_tasks add constraint FKb7275txd7n6py3gy565uax1or foreign key (tasks_id) references task (id);
alter table parent_tasks add constraint FKk8xx88a2yi0904aartyry13rm foreign key (parent_username) references parent (username);
alter table picture add constraint FKlu7j09wfwp6txuh27ldbam760 foreign key (publisher_username) references user_data (username);
alter table picture_comment add constraint FK1nk3snidrr3p0vhxsu48x2wj4 foreign key (comment_id) references comment (id);
alter table picture_comment add constraint FK5ycynp76a1dajixcjc7mhk05e foreign key (picture_id) references picture (id);
alter table registration add constraint FKonwm8o92vs5rquv10wdmhcci5 foreign key (child_id) references child (id);
alter table sibling add constraint FKp2poio42yh71hf4t0ne0h1d2j foreign key (child_id) references child (id);
alter table task add constraint FK7ks3yp24b4dr1q9jhx6gbnjjm foreign key (receiver_username) references user_data (username);
alter table task add constraint FKilbm3e76taylut0o3jvxhyhdy foreign key (sender_username) references user_data (username);

source /vagrant/src/main/resources/data.sql;

quit
EOFMYSQL


    #Change directory to the source code
	cd /vagrant/
	
	mvn test
	#Run the application
    mvn spring-boot:run
