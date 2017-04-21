#!/bin/bash

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
CREATE DATABASE asndb;
quit
EOFMYSQL
cd /vagrant
mvn spring-boot:run

