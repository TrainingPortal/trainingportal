--delete tables
drop table Users;
drop table Roles;
drop table Course;
drop table FeedBack;
drop table Info_Desk;
drop table Chat;
drop table Material;
drop table Task;
drop table Homework;
drop table Attendance;
drop table Attendance_Type;
drop table Question_Status;
drop table Message;
drop table Notification;
drop table Groups;
drop table Schedule;
drop table Lesson;
drop table User_Group;
drop TABLE User_Chat;
drop TABLE Weekday;
drop TABLE Notification_Status;
drop TABLE Desired_Period;
drop TABLE Group_Status;
drop TABLE Course_Status;
drop table main_slider;
DROP TABLE main_cards;

CREATE TABLE Users
(
    user_Id      NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    name        VARCHAR2(50) ,
    email       VARCHAR2(50) ,
    password    VARCHAR2(128),
    enabled     NUMBER(1),
    token       VARCHAR(36),
    role_Id      number NOT NULL,
    manager_Id number
);

INSERT INTO users(name, email, password, enabled, role_Id)
        VALUES ('adminBoss', 'boss@gmail.com', 'password123', 1, 1);
INSERT INTO users(name, email, password, enabled, role_Id)
        VALUES ('userTrainer1', 'userTrainer@gmail.com', 'password123', 1, 3);
INSERT INTO users(name, email, password, enabled, role_Id)
        VALUES ('userTrainer2', 'userTrainer@gmail.com', 'password123', 1, 3);
INSERT INTO users(name, email, password, enabled, role_Id)
        VALUES ('userTrainer3', 'userTrainer@gmail.com', 'password123', 1, 3);
INSERT INTO users(name, email, password, enabled, role_Id)
        VALUES ('userTrainer4', 'userTrainer@gmail.com', 'password123', 1, 3);
INSERT INTO users(name, email, password, enabled, role_Id)
        VALUES ('userTrainer5', 'userTrainer@gmail.com', 'password123', 1, 3);
INSERT INTO users(name, email, password, enabled, role_Id)
        VALUES ('userTrainer6', 'userTrainer@gmail.com', 'password123', 1, 3);
INSERT INTO users(name, email, password, enabled, role_Id)
        VALUES ('userTrainer7', 'userTrainer@gmail.com', 'password123', 1, 3);
INSERT INTO users(name, email, password, enabled, role_Id)
        VALUES ('userTrainer8', 'userTrainer@gmail.com', 'password123', 1, 3);
INSERT INTO users(name, email, password, enabled, role_Id)
        VALUES ('userManager1', 'userManager@gmail.com', 'password123', 1, 4);
INSERT INTO users(name, email, password, enabled, role_Id)
        VALUES ('userManager2', 'userManager@gmail.com', 'password123', 1, 4);
INSERT INTO users(name, email, password, enabled, role_Id)
        VALUES ('userManager3', 'userManager@gmail.com', 'password123', 1, 4);
INSERT INTO users(name, email, password, enabled, role_Id)
        VALUES ('userManager4', 'userManager@gmail.com', 'password123', 1, 4);
INSERT INTO users(name, email, password, enabled, role_Id)
        VALUES ('userManager5', 'userManager@gmail.com', 'password123', 1, 4);
INSERT INTO users(name, email, password, enabled, role_Id)
        VALUES ('userManager6', 'userManager@gmail.com', 'password123', 1, 4);
INSERT INTO users(name, email, password, enabled, role_Id)
        VALUES ('userManager7', 'userManager@gmail.com', 'password123', 1, 4);
INSERT INTO users(name, email, password, enabled, role_Id)
        VALUES ('userManager8', 'userManager@gmail.com', 'password123', 1, 4);
INSERT INTO users(name, email, password, enabled, role_Id)
        VALUES ('userManager9', 'userManager@gmail.com', 'password123', 1, 4);
INSERT INTO users(name, email, password, enabled, role_Id)
        VALUES ('userManager10', 'userManager@gmail.com', 'password123', 1, 4);
        INSERT INTO users(name, email, password, enabled, role_Id, manager_Id)
        VALUES ('userUser1', 'userUser@gmail.com', 'password123', 1, 2, 10);
INSERT INTO users(name, email, password, enabled, role_Id, manager_Id)
        VALUES ('userUser2', 'userUser@gmail.com', 'password123', 1, 2, 10);
INSERT INTO users(name, email, password, enabled, role_Id, manager_Id)
        VALUES ('userUser3', 'userUser@gmail.com', 'password123', 1, 2, 11);
INSERT INTO users(name, email, password, enabled, role_Id, manager_Id)
        VALUES ('userUser4', 'userUser@gmail.com', 'password123', 1, 2, 11 );
INSERT INTO users(name, email, password, enabled, role_Id, manager_Id)
        VALUES ('userUser5', 'userUser@gmail.com', 'password123', 1, 2, 11);
INSERT INTO users(name, email, password, enabled, role_Id, manager_Id)
        VALUES ('userUser6', 'userUser@gmail.com', 'password123', 1, 2, 12);
INSERT INTO users(name, email, password, enabled, role_Id, manager_Id)
        VALUES ('userUser7', 'userUser@gmail.com', 'password123', 1, 2, 13);
INSERT INTO users(name, email, password, enabled, role_Id, manager_Id)
        VALUES ('userUser8', 'userUser@gmail.com', 'password123', 1, 2, 13);
INSERT INTO users(name, email, password, enabled, role_Id, manager_Id)
        VALUES ('userUser9', 'userUser@gmail.com', 'password123', 1, 2, 15);
INSERT INTO users(name, email, password, enabled, role_Id, manager_Id)
        VALUES ('userUser10', 'userUser@gmail.com', 'password123', 1, 2, 17);



CREATE TABLE roles
(
    role_Id      NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    name        VARCHAR(20)
);

INSERT INTO roles (name) VALUES('ROLE_ADMIN');
INSERT INTO roles (name) VALUES('ROLE_EMPLOYEE');
INSERT INTO roles (name) VALUES('ROLE_TRAINER');
INSERT INTO roles (name) VALUES('ROLE_MANAGER');

CREATE TABLE Course
(
    course_id        NUMBER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    name             VARCHAR2(50),
    course_level     VARCHAR2(40),
    course_status_id NUMBER,
    min_number       NUMBER,
    max_number       NUMBER,
    description      VARCHAR2(3000),
    trainer_id       NUMBER
);

INSERT INTO Course(name, course_level, course_status_id, min_number, max_number, description, trainer_id)
VALUES ('Amazing English', 'B2', 1, 5, 30, 'very good course', 2);
INSERT INTO Course(name, course_level, course_status_id, min_number, max_number, description, trainer_id)
VALUES ('Good English', 'B2', 1, 5, 30, 'very good course', 3);
INSERT INTO Course(name, course_level, course_status_id, min_number, max_number, description, trainer_id)
VALUES ('Very good English', 'B2', 1, 5, 30, 'very good course', 4);
INSERT INTO Course(name, course_level, course_status_id, min_number, max_number, description, trainer_id)
VALUES ('Very amazing English', 'B2', 1, 5, 30, 'very good course', 5);
INSERT INTO Course(name, course_level, course_status_id, min_number, max_number, description, trainer_id)
VALUES ('Amazing very good English', 'B2', 1, 5, 30, 'very good course', 6);



CREATE TABLE User_Group
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    group_id NUMBER,
    user_id NUMBER
);

INSERT INTO User_Group (group_id, user_id)
values (1, 11);
INSERT INTO User_Group (group_id, user_id)
values (2, 14);
INSERT INTO User_Group (group_id, user_id)
values (3, 19);
INSERT INTO User_Group (group_id, user_id)
values (4, 20);
INSERT INTO User_Group (group_id, user_id)
values (5, 26);
INSERT INTO User_Group (group_id, user_id)
values (1, 29);
INSERT INTO User_Group (group_id, user_id)
values (5, 22);
INSERT INTO User_Group (group_id, user_id)
values (2, 13);


CREATE TABLE Groups
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    name VARCHAR2(30),
    capacity NUMBER,
    course_id NUMBER,
    status_id NUMBER
);

INSERT INTO Groups(name, capacity, course_id, status_id)
        VALUES ('Group-1', 10, 1, 1 );
INSERT INTO Groups(name, capacity, course_id, status_id)
        VALUES ('Group-2', 10, 1, 1 );
INSERT INTO Groups(name, capacity, course_id, status_id)
        VALUES ('Group-3', 10, 2, 1 );
INSERT INTO Groups(name, capacity, course_id, status_id)
        VALUES ('Group-4', 10, 2, 1 );
INSERT INTO Groups(name, capacity, course_id, status_id)
        VALUES ('Group-5', 10, 3, 1 );



CREATE TABLE Lesson
(
    lesson_id          NUMBER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    lesson_name        VARCHAR2(20),
    lesson_description VARCHAR2(3000),
    lesson_duration    NUMBER,
    course_id          NUMBER,
    lesson_number      NUMBER
);

INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-1', 'very helpful description', 60, 1, 1);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-2', 'very helpful description', 60, 1, 2);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-3', 'very helpful description', 60, 1, 3);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-4', 'very helpful description', 60, 1, 4);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-5', 'very helpful description', 60, 1, 5);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-6', 'very helpful description', 60, 1, 6);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-7', 'very helpful description', 60, 1, 7);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-8', 'very helpful description', 60, 1, 8);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-9', 'very helpful description', 60, 1, 9);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-10', 'very helpful description', 60, 1, 10);

INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-1', 'very helpful description', 60, 2, 1);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-2', 'very helpful description', 60, 2, 2);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-3', 'very helpful description', 60, 2, 3);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-4', 'very helpful description', 60, 2, 4);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-5', 'very helpful description', 60, 2, 5);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-6', 'very helpful description', 60, 2, 6);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-7', 'very helpful description', 60, 2, 7);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-8', 'very helpful description', 60, 2, 8);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-9', 'very helpful description', 60, 2, 9);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-10', 'very helpful description', 60, 2, 10);

INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-1', 'very helpful description', 60, 3, 1);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-2', 'very helpful description', 60, 3, 2);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-3', 'very helpful description', 60, 3, 3);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-4', 'very helpful description', 60, 3, 4);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-5', 'very helpful description', 60, 3, 5);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-6', 'very helpful description', 60, 3, 6);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-7', 'very helpful description', 60, 3, 7);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-8', 'very helpful description', 60, 3, 8);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-9', 'very helpful description', 60, 3, 9);
INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number)
values ('Lesson-10', 'very helpful description', 60, 3, 10);


CREATE TABLE Homework
(
    homework_id            NUMBER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    lesson_id              NUMBER,
    homework_name          VARCHAR2(20),
    homework_deadline_date DATE
);

INSERT INTO Homework (lesson_id, homework_name)
values (1, 'Homework-1');
INSERT INTO Homework (lesson_id, homework_name)
values (1, 'Homework-2');
INSERT INTO Homework (lesson_id, homework_name)
values (1, 'Homework-3');
INSERT INTO Homework (lesson_id, homework_name)
values (1, 'Homework-4');
INSERT INTO Homework (lesson_id, homework_name)
values (1, 'Homework-5');
INSERT INTO Homework (lesson_id, homework_name)
values (1, 'Homework-6');
INSERT INTO Homework (lesson_id, homework_name)
values (1, 'Homework-7');
INSERT INTO Homework (lesson_id, homework_name)
values (2, 'Homework-8');
INSERT INTO Homework (lesson_id, homework_name)
values (2, 'Homework-9');
INSERT INTO Homework (lesson_id, homework_name)
values (2, 'Homework-10');

CREATE TABLE Task
(
    task_id          NUMBER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    homework_id      NUMBER,
    task_description VARCHAR2(200)
);

INSERT INTO Task (homework_id, task_description)
values (1, 'some helpful description1');
INSERT INTO Task (homework_id, task_description)
values (1, 'some helpful description2');
INSERT INTO Task (homework_id, task_description)
values (1, 'some helpful description2');
INSERT INTO Task (homework_id, task_description)
values (2, 'some helpful description1');
INSERT INTO Task (homework_id, task_description)
values (2, 'some helpful description2');
INSERT INTO Task (homework_id, task_description)
values (3, 'some helpful description1');
INSERT INTO Task (homework_id, task_description)
values (3, 'some helpful description2');
INSERT INTO Task (homework_id, task_description)
values (3, 'some helpful description3');
INSERT INTO Task (homework_id, task_description)
values (4, 'some helpful description');
INSERT INTO Task (homework_id, task_description)
values (5, 'some helpful description');
INSERT INTO Task (homework_id, task_description)
values (6, 'some helpful description1');
INSERT INTO Task (homework_id, task_description)
values (6, 'some helpful description2');


CREATE TABLE Schedule
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    group_id NUMBER,
    date_lesson DATE,
    lesson_id NUMBER
);

INSERT INTO Schedule (group_id, date_lesson, lesson_id) values (1, TO_DATE('01/09/2019','DD/MM/YYYY'),1);
INSERT INTO Schedule (group_id, date_lesson, lesson_id) values (1, TO_DATE('07/09/2019','DD/MM/YYYY'),2);
INSERT INTO Schedule (group_id, date_lesson, lesson_id) values (1, TO_DATE('14/09/2019','DD/MM/YYYY'),3);
INSERT INTO Schedule (group_id, date_lesson, lesson_id) values (1, TO_DATE('21/09/2019','DD/MM/YYYY'),4);
INSERT INTO Schedule (group_id, date_lesson, lesson_id) values (1, TO_DATE('28/09/2019','DD/MM/YYYY'),5);
INSERT INTO Schedule (group_id, date_lesson, lesson_id) values (1, TO_DATE('01/10/2019','DD/MM/YYYY'),6);
INSERT INTO Schedule (group_id, date_lesson, lesson_id) values (1, TO_DATE('07/10/2019','DD/MM/YYYY'),7);
INSERT INTO Schedule (group_id, date_lesson, lesson_id) values (1, TO_DATE('14/10/2019','DD/MM/YYYY'),8);
INSERT INTO Schedule (group_id, date_lesson, lesson_id) values (1, TO_DATE('21/10/2019','DD/MM/YYYY'),9);
INSERT INTO Schedule (group_id, date_lesson, lesson_id) values (1, TO_DATE('28/10/2019','DD/MM/YYYY'),10);

INSERT INTO Schedule (group_id, date_lesson, lesson_id) values (2, TO_DATE('01/11/2019','DD/MM/YYYY'),1);
INSERT INTO Schedule (group_id, date_lesson, lesson_id) values (2, TO_DATE('07/11/2019','DD/MM/YYYY'),2);
INSERT INTO Schedule (group_id, date_lesson, lesson_id) values (2, TO_DATE('14/11/2019','DD/MM/YYYY'),3);
INSERT INTO Schedule (group_id, date_lesson, lesson_id) values (2, TO_DATE('21/11/2019','DD/MM/YYYY'),4);
INSERT INTO Schedule (group_id, date_lesson, lesson_id) values (2, TO_DATE('28/11/2019','DD/MM/YYYY'),5);
INSERT INTO Schedule (group_id, date_lesson, lesson_id) values (2, TO_DATE('01/12/2019','DD/MM/YYYY'),6);
INSERT INTO Schedule (group_id, date_lesson, lesson_id) values (2, TO_DATE('07/12/2019','DD/MM/YYYY'),7);
INSERT INTO Schedule (group_id, date_lesson, lesson_id) values (2, TO_DATE('14/12/2019','DD/MM/YYYY'),8);
INSERT INTO Schedule (group_id, date_lesson, lesson_id) values (2, TO_DATE('21/12/2019','DD/MM/YYYY'),9);
INSERT INTO Schedule (group_id, date_lesson, lesson_id) values (2, TO_DATE('28/12/2019','DD/MM/YYYY'),10);


CREATE TABLE Attendance
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    user_id NUMBER,
    type_id NUMBER,
    schedule_id NUMBER
);

INSERT INTO attendance (user_id, type_id, schedule_id)
values (13, 1, 1);
INSERT INTO attendance (user_id, type_id, schedule_id)
values (20, 2, 3);
INSERT INTO attendance (user_id, type_id, schedule_id)
values (29, 3, 5);
INSERT INTO attendance (user_id, type_id, schedule_id)
values (10, 4, 7);
INSERT INTO attendance (user_id, type_id, schedule_id)
values (11, 5, 9);
INSERT INTO attendance (user_id, type_id, schedule_id)
values (21, 1, 10);
INSERT INTO attendance (user_id, type_id, schedule_id)
values (22, 2, 12);
INSERT INTO attendance (user_id, type_id, schedule_id)
values (23, 3, 13);
INSERT INTO attendance (user_id, type_id, schedule_id)
values (24, 4, 16);
INSERT INTO attendance (user_id, type_id, schedule_id)
values (25, 5, 20);


CREATE TABLE Attendance_Type
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    type VARCHAR2(70)
);

INSERT INTO Attendance_Type (type)values('Present');
INSERT INTO Attendance_Type (type)values('Absent without reason');
INSERT INTO Attendance_Type (type)values('Absent due tu bussines trip');
INSERT INTO Attendance_Type (type)values('Absent due to sick leave');
INSERT INTO Attendance_Type (type)values('Absent due to project activities');

CREATE TABLE Material
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    lesson_id NUMBER,
    description VARCHAR2(1000)
);

INSERT INTO Material (lesson_id, description) values(1, 'material');
INSERT INTO Material (lesson_id, description) values(2, 'some book');
INSERT INTO Material (lesson_id, description) values(3, 'some article');
INSERT INTO Material (lesson_id, description) values(4, 'some material');
INSERT INTO Material (lesson_id, description) values(5, 'some very interesting material');
INSERT INTO Material (lesson_id, description) values(6, 'some very interesting material');
INSERT INTO Material (lesson_id, description) values(7, 'some very interesting material');
INSERT INTO Material (lesson_id, description) values(8, 'some very interesting material');
INSERT INTO Material (lesson_id, description) values(9, 'some very interesting material');
INSERT INTO Material (lesson_id, description) values(10, 'some very interesting material');

CREATE TABLE Feedback
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    trainer_id NUMBER,
    employee_id NUMBER,
    description VARCHAR2(4000),
    date_feedback DATE,
    course_id NUMBER
);

CREATE TABLE Info_Desk
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    emp_id NUMBER,
    description VARCHAR2(4000),
    status_id NUMBER
);

INSERT INTO Info_Desk (emp_id, description, status_id) values(21, 'Dear Admin, how does it work?',1);
INSERT INTO Info_Desk (emp_id, description, status_id) values(21, 'Dear Admin, who is my Manager?',2);
INSERT INTO Info_Desk (emp_id, description, status_id) values(21, 'Dear Admin, why is my Manager so angry?',3);
INSERT INTO Info_Desk (emp_id, description, status_id) values(21, 'Where am i?',4);
INSERT INTO Info_Desk (emp_id, description, status_id) values(21, 'Where is my money, Jhonny???',5);
INSERT INTO Info_Desk (emp_id, description, status_id) values(21, 'where is money Lebowski???',6);

CREATE TABLE Question_Status
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    name VARCHAR2(20)
);


INSERT INTO Question_Status (name) values('Draft');
INSERT INTO Question_Status (name) values('Open');
INSERT INTO Question_Status (name) values('In Progress');
INSERT INTO Question_Status (name) values('Answered');
INSERT INTO Question_Status (name) values('Reopen');




CREATE TABLE Chat
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    group_id NUMBER
);

CREATE TABLE Notification
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    message VARCHAR2(100),
    date_notification DATE,
    user_id NUMBER,
    status_id NUMBER
);

CREATE TABLE Message
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    sender_id NUMBER,
    date_message DATE,
    message VARCHAR2(4000),
    chat_id NUMBER
);

CREATE TABLE User_Chat
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    user_id NUMBER,
    chat_id NUMBER
);

CREATE TABLE Notification_Status
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    name VARCHAR2(20)
);

INSERT INTO Notification_Status (name) values('Posted');
INSERT INTO Notification_Status (name) values('Not posted');
INSERT INTO Notification_Status (name) values('Posted');
INSERT INTO Notification_Status (name) values('Opened');
INSERT INTO Notification_Status (name) values('Closed');

CREATE TABLE Desired_Period
(
    desired_period_id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    user_id NUMBER,
    course_id NUMBER
);

INSERT INTO desired_period (user_id, course_id)
values (12, 1);
INSERT INTO desired_period (user_id, course_id)
values (20, 1);
INSERT INTO desired_period (user_id, course_id)
values (21, 2);
INSERT INTO desired_period (user_id, course_id)
values (22, 3);
INSERT INTO desired_period (user_id, course_id)
values (23, 4);
INSERT INTO desired_period (user_id, course_id)
values (24, 5);
INSERT INTO desired_period (user_id, course_id)
values (25, 3);
INSERT INTO desired_period (user_id, course_id)
values (29, 5);

CREATE TABLE Weekday
(
    weekday_id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    day_name VARCHAR2(20),
    time_start VARCHAR2(20),
    time_end VARCHAR2(20),
    period_id NUMBER
);



CREATE TABLE Group_Status
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    name_status VARCHAR2(20)
);

INSERT INTO Group_Status (name_status) values ('Open');
INSERT INTO Group_Status (name_status) values ('Full');

CREATE TABLE Course_Status
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    name_status VARCHAR2(20)
);


INSERT INTO Course_Status (name_status) values ('Open');
INSERT INTO Course_Status (name_status) values ('Closed');
INSERT INTO Course_Status (name_status) values ('Stoped');




CREATE TABLE main_cards
(
    main_card_id      NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    files_name        VARCHAR2(100 CHAR),
    files_type        VARCHAR2(10 CHAR),
    files_data        BLOB,
    card_title        VARCHAR2(50 CHAR),
    card_text         VARCHAR2(1000 CHAR),
    button_name       VARCHAR2(50 CHAR),
    card_url          VARCHAR2(100 CHAR)
);



CREATE TABLE main_slider
(
    main_slider_id    NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    files_name        VARCHAR2(100 CHAR),
    files_type        VARCHAR2(10 CHAR),
    files_data        BLOB,
    button_name       VARCHAR2(50 CHAR),
    button_url        VARCHAR2(100 CHAR)
);

COMMIT;

--delete dependencies
alter table users drop constraint user_fr_manager;
alter table users drop constraint users_fk_roles;
alter table Notification drop constraint ntfk_fk_user;
alter table Notification drop constraint ntfk_fk_status;
alter table chat drop constraint chat_fk_group;
alter table Info_Desk drop constraint idesk_emp_fk_user;
alter table Info_Desk drop constraint idesk_fk_qstatus;
alter table Feedback drop constraint fdbk_tr_fk_user;
alter table Feedback drop constraint fdbk_fk_cource;
alter table Feedback drop constraint fdbk_emp_fk_user;
alter table User_Group drop constraint ugroup_fk_user;
alter table User_Group drop constraint ugroup_fk_group;
alter table Groups drop constraint group_fk_course;
alter table Groups drop constraint group_fr_status;
alter table User_Chat drop constraint uchat_fk_user;
alter table User_Chat drop constraint uchat_fk_chat;
alter table Attendance drop constraint atten_fk_user;
alter table Attendance drop constraint atten_fk_schedule;
alter table Attendance drop constraint atten_fk_type;
alter table Schedule drop constraint schedule_fk_group;
alter table Schedule drop constraint schedule_fk_lesson;
-- alter table Homework drop constraint lesson_fk_homework;
alter table Lesson drop constraint lesson_fk_course;
alter table Material drop constraint material_fk_lesson;
alter table Task drop constraint task_fk_homework;
alter table Desired_Period drop constraint dperiod_fk_user;
alter table Desired_Period drop constraint dperiod_fk_cource;
alter table Weekday drop constraint weekday_fk_dperiod;
alter table Course drop constraint cource_fk_status;
alter table Course drop constraint cource_fk_trainer;
alter table Message drop constraint message_fk_user;
alter table Message drop constraint message_fk_chat;




--create dependencies
alter table users add constraint user_fr_manager FOREIGN KEY(manager_Id) references users (user_Id);
alter table users ADD constraint users_fk_roles FOREIGN KEY(role_Id) references roles (role_Id);
alter table Notification ADD constraint ntfk_fk_user FOREIGN KEY (user_id) references users (user_Id);
alter table Notification add constraint ntfk_fk_status FOREIGN KEY (status_id) references Notification_Status (id);
alter table chat add constraint chat_fk_group FOREIGN KEY (group_id) references groups (id);

alter table Info_Desk add constraint idesk_emp_fk_user FOREIGN KEY (emp_id) references users (user_Id);
alter table Info_Desk add constraint idesk_fk_qstatus FOREIGN KEY (status_id) references Question_Status (id);

alter table Feedback add constraint fdbk_tr_fk_user FOREIGN KEY (trainer_id) references users (user_Id);
alter table Feedback add constraint fdbk_fk_cource FOREIGN KEY (course_id) references course (course_id);
alter table Feedback add constraint fdbk_emp_fk_user FOREIGN KEY (employee_id) references users (user_Id);
alter table User_Group add constraint ugroup_fk_user FOREIGN KEY (user_id) references users (user_Id);
alter table User_Group add constraint ugroup_fk_group FOREIGN KEY (group_id) references Groups (id);
alter table Groups add constraint group_fk_course FOREIGN KEY (course_id) references Course (course_id);
alter table Groups add constraint group_fr_status FOREIGN KEY (status_id) references Group_Status (id);
alter table User_Chat add constraint uchat_fk_user FOREIGN KEY (user_id) references users (user_Id);
alter table User_Chat add constraint uchat_fk_chat FOREIGN KEY (chat_id) references Chat (id);
alter table Attendance add constraint atten_fk_user FOREIGN KEY (user_id) references users (user_Id);
alter table Attendance add constraint atten_fk_schedule FOREIGN KEY (schedule_id) references Schedule (id);
alter table Attendance add constraint atten_fk_type FOREIGN KEY (type_id) references Attendance_Type(id);
alter table Schedule add constraint schedule_fk_group FOREIGN KEY (group_id) references Groups (id);
alter table Schedule add constraint schedule_fk_lesson FOREIGN KEY (lesson_id) references Lesson (lesson_id);
-- alter table Homework add constraint lesson_fk_homework FOREIGN KEY (lesson_id) references Lesson (lesson_id);
alter table Lesson add constraint lesson_fk_course FOREIGN KEY (course_id) references Course (course_id);
alter table Material add constraint material_fk_lesson FOREIGN KEY (lesson_id) references Lesson (lesson_id);
alter table Task add constraint task_fk_homework FOREIGN KEY (homework_id) references Homework (homework_id);
alter table Desired_Period add constraint dperiod_fk_user FOREIGN KEY (user_id) references users (user_Id);
alter table Desired_Period add constraint dperiod_fk_cource FOREIGN KEY (course_id) references Course (course_id);
alter table Weekday add constraint weekday_fk_dperiod FOREIGN KEY (period_id) references Desired_Period (desired_period_id);
alter table Course add constraint cource_fk_status FOREIGN KEY (course_status_id) references Course_Status(id);
alter table Course add constraint cource_fk_trainer FOREIGN KEY (trainer_id) references users (user_Id);
alter table Message add constraint message_fk_user FOREIGN KEY (sender_id) references users (user_Id);
alter table Message add constraint message_fk_chat FOREIGN KEY (chat_id) references Chat(id);