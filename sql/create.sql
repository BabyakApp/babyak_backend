CREATE TABLE MAJOR (
                       majorId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       college TEXT NOT NULL,
                       departure TEXT NOT NULL
);

CREATE TABLE USER (
    userId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email TEXT NOT NULL,
    studentId INT NOT NULL,
    nickname TEXT NOT NULL,
    noShows INT,
    majorId INT,
    FOREIGN KEY (majorId) REFERENCES MAJOR(majorId)
);

CREATE TABLE BLOCK (
    blockId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    userId INT,
    FOREIGN KEY (userId) REFERENCES USER(userId)
);