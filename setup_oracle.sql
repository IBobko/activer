-- Creating new user for oracle
CREATE USER C##activer IDENTIFIED BY '3361243';

-- Give grants
GRANT CREATE SEQUENCE TO C##activer;
GRANT CREATE TABLE TO C##activer;
GRANT CONNECT TO C##activer;

-- Give quotas
ALTER USER C##activer QUOTA 100M ON USERS;

