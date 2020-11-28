-- first thing is to give root all privileges

GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;


CREATE DATABASE beles_dev;
CREATE DATABASE beles_prod;

CREATE USER 'beles_dev_user'@'polocalhost' IDENTIFIED BY 'beles';
CREATE USER 'beles_prod_user'@'localhost' IDENTIFIED BY 'beles';
CREATE USER 'beles_dev_user'@'%' IDENTIFIED BY 'beles';
CREATE USER 'beles_prod_user'@'%' IDENTIFIED BY 'beles';

GRANT SELECT ON beles_dev.* TO 'beles_dev_user'@'localhost';
GRANT INSERT ON beles_dev.* TO 'beles_dev_user'@'localhost';
GRANT UPDATE ON beles_dev.* TO 'beles_dev_user'@'localhost';
GRANT DELETE ON beles_dev.* TO 'beles_dev_user'@'localhost';

GRANT SELECT ON beles_prod.* TO 'beles_prod_user'@'localhost';
GRANT INSERT ON beles_prod.* TO 'beles_prod_user'@'localhost';
GRANT UPDATE ON beles_prod.* TO 'beles_prod_user'@'localhost';
GRANT DELETE ON beles_prod.* TO 'beles_prod_user'@'localhost';




GRANT SELECT ON beles_dev.* TO 'beles_dev_user'@'%';
GRANT INSERT ON beles_dev.* TO 'beles_dev_user'@'%';
GRANT UPDATE ON beles_dev.* TO 'beles_dev_user'@'%';
GRANT DELETE ON beles_dev.* TO 'beles_dev_user'@'%';

GRANT SELECT ON beles_prod.* TO 'beles_prod_user'@'%';
GRANT INSERT ON beles_prod.* TO 'beles_prod_user'@'%';
GRANT UPDATE ON beles_prod.* TO 'beles_prod_user'@'%';
GRANT DELETE ON beles_prod.* TO 'beles_prod_user'@'%';
