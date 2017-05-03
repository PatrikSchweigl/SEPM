#!/usr/bin/env bash

mysql -u root -pasnTeam1 <<EOFMYSQL
USE asndb;
source /src/main/resources/data.sql
quit
EOFMYSQL
