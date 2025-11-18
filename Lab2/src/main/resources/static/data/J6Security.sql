CREATE TABLE Users
(
    Username VARCHAR(50) NOT NULL,
    Password VARCHAR(500) NOT NULL,
    Enabled BIT NOT NULL,
    PRIMARY KEY(Username)
);

CREATE TABLE Authorities
(
    Id BIGINT NOT NULL AUTO_INCREMENT,
    Username VARCHAR(50) NOT NULL,
    Authority VARCHAR(50) NOT NULL,
    PRIMARY KEY(Id),
    UNIQUE(Username, Authority),
    FOREIGN KEY(Username) REFERENCES Users(Username)
        ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Users(Username, Password, Enabled) VALUES('user@gmail.com', '{noop}123', 1);
INSERT INTO Users(Username, Password, Enabled) VALUES('admin@gmail.com', '{noop}123', 1);
INSERT INTO Users(Username, Password, Enabled) VALUES('both@gmail.com', '{noop}123', 1);

INSERT INTO Authorities(Username, Authority) VALUES('user@gmail.com', 'ROLE_USER');
INSERT INTO Authorities(Username, Authority) VALUES('admin@gmail.com', 'ROLE_ADMIN');
INSERT INTO Authorities(Username, Authority) VALUES('both@gmail.com', 'ROLE_USER');
INSERT INTO Authorities(Username, Authority) VALUES('both@gmail.com', 'ROLE_ADMIN');

# Bài 3
CREATE TABLE J6users
(
    Username VARCHAR(50) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Enabled  TINYINT(1) NOT NULL,
    PRIMARY KEY (Username)
);

CREATE TABLE J6roles
(
    Id   VARCHAR(50) NOT NULL,
    Name NVARCHAR(50) NOT NULL,
    PRIMARY KEY (Id)
);

CREATE TABLE J6userroles
(
    Id       BIGINT NOT NULL AUTO_INCREMENT,
    Username VARCHAR(50) NOT NULL,
    RoleId   VARCHAR(50) NOT NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (Username) REFERENCES J6users (Username)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (RoleId) REFERENCES J6roles (Id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (Username, RoleId)
);

INSERT INTO J6users(Username, Password, Enabled) VALUES
('user@gmail.com', '{noop}123', 1),
('admin@gmail.com', '{noop}123', 1),
('both@gmail.com', '{noop}123', 1);

INSERT INTO J6roles(Id, Name) VALUES
('ROLE_USER', N'Nhân viên'),
('ROLE_ADMIN', N'Quản lý');

INSERT INTO J6userroles(Username, RoleId) VALUES
('user@gmail.com', 'ROLE_USER'),
('admin@gmail.com', 'ROLE_ADMIN'),
('both@gmail.com', 'ROLE_USER'),
('both@gmail.com', 'ROLE_ADMIN');