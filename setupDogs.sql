DROP DATABASE IF EXISTS gyeongju;
CREATE DATABASE gyeongju;
USE gyeongju;

CREATE TABLE dogs
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    ownerName VARCHAR(50),
    email VARCHAR(50) NOT NULL,
    breed VARCHAR(50) NOT NULL,
    groupName VARCHAR(50) NOT NULL,
    gender VARCHAR(20) NOT NULL,
    ranking VARCHAR(20) NOT NULL,
    handlerName VARCHAR(30)
);

CREATE TABLE handlers
(
    handlerName VARCHAR(50) NOT NULL UNIQUE,
    encryptedPassword VARCHAR(50) NOT NULL,
    role_name VARCHAR(50) NOT NULL
);

COMMIT;
