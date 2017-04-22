#!/usr/bin/env bash

mysql -u root -pasnTeam1 <<EOFMYSQL
USE asndb;
source /vagrant/data.sql
quit
EOFMYSQL
