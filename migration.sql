DROP TABLE IF EXISTS absences;
DROP TABLE IF EXISTS courseAssignments;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS grades;
DROP TABLE IF EXISTS `groups`;
DROP TABLE IF EXISTS parents;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS teachers;

CREATE TABLE absences (
    student   VARCHAR (64) NOT NULL,
    date      VARCHAR (64) NOT NULL,
    motivated VARCHAR (64) NOT NULL
);

CREATE TABLE courseAssignments (
    courseName VARCHAR (64) NOT NULL,
    groupName  VARCHAR (64) NOT NULL
);

CREATE TABLE courses (
    name     VARCHAR (64) NOT NULL,
    teacher  VARCHAR (64) NOT NULL,
    withExam VARCHAR (64) NOT NULL
);

CREATE TABLE grades (
    student VARCHAR (64) NOT NULL,
    course  VARCHAR (64) NOT NULL,
    value   VARCHAR (64) NOT NULL
);

CREATE TABLE `groups` (
    name VARCHAR (64) NOT NULL
);

CREATE TABLE parents (
    student VARCHAR (64) NOT NULL,
    name    VARCHAR (64) NOT NULL,
    phone   VARCHAR (64) NOT NULL
);

CREATE TABLE students (
    name  VARCHAR (64) NOT NULL,
    age   VARCHAR (64) NOT NULL,
    `group` VARCHAR (64) NOT NULL
);

CREATE TABLE teachers (
    name VARCHAR (64) NOT NULL
);
