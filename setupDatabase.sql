DROP DATABASE IF EXISTS gyeongju;
CREATE DATABASE gyeongju;
USE gyeongju;

CREATE TABLE dogs
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    ownerName VARCHAR(50),
    email VARCHAR(50) NOT NULL,
    breed VARCHAR(50),
    groupName VARCHAR(50),
    gender VARCHAR(20),
    ranking VARCHAR(20)
);

COMMIT;
