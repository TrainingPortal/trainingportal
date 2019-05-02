--delete tables
drop table Users;
drop table Roles;
drop table Course;
drop table FeedBack;
drop table InfoDesk;
drop table Chat;
drop table Material;
drop table Task;
drop table Homework;
drop table Attendance;
drop table AttendanceType;
drop table QuestionStatus;
drop table Message;
drop table Notification;
drop table Groups;
drop table Schedule;
drop table Lesson;
drop table UserGroup;
drop TABLE UserChat;
drop TABLE Weekday;
drop TABLE NotificationStatus;
drop TABLE DesiredPeriod;
drop TABLE GroupStatus;
drop TABLE CourceStatus;



CREATE TABLE Users
(
    userId      NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    name        VARCHAR2(50) ,
    email       VARCHAR2(50) ,
    password    VARCHAR2(128),
    enabled     NUMBER(1),
    token       VARCHAR(36),
    roleId      number NOT NULL,
    managerId number
);

INSERT INTO Users (name, email, password,enabled,token,roleid,managerid) 
values ( 'Vitaliy', 'someemail@rambler.ru','password12345', 1, 'sometoken', 1, 1);

INSERT INTO Users (name, email, password,enabled,token,roleid,managerid) 
values ( 'Oleg Vynnik', 'vinnik@gmail.com','password222', 1, 'token', 1, 1);

INSERT INTO Users (name, email, password,enabled,token,roleid,managerid) 
values ( 'Oleksandr', 'mail@gmail.com','1234567', 1, 'token', 1, 1);

INSERT INTO Users (name, email, password,enabled,token,roleid,managerid) 
values ( 'Andriy', 'mailyandex.ru','45678', 1, 'token', 1, 1);

INSERT INTO Users (name, email, password,enabled,token,roleid,managerid) 
values ( 'Max', 'max@email.com','12345', 1, 'token', 1, 1);

CREATE TABLE roles
(
    roleId      NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    name        VARCHAR(20)
);

INSERT INTO roles (name) VALUES('ROLE_ADMIN');
INSERT INTO roles (name) VALUES('ROLE_EMPLOYEE');
INSERT INTO roles (name) VALUES('ROLE_TRAINER');
INSERT INTO roles (name) VALUES('ROLE_MANAGER');

CREATE TABLE Course
(   
    courceId NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    name VARCHAR2(50),
    course_level VARCHAR2(10),
    course_status_id NUMBER,
    min_number NUMBER,
    max_number NUMBER,
    description VARCHAR2(1000),
    trainer_id NUMBER,
    lessons_number NUMBER

);

CREATE TABLE UserGroup
(  
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    group_id NUMBER,
    user_id NUMBER
);

CREATE TABLE Groups
(  
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    name VARCHAR2(30),
    capacity NUMBER,
    course_id NUMBER,
    status_id NUMBER
);

CREATE TABLE Lesson
(  
    lessonId NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    lessonName VARCHAR2(20),
    lessonDescription VARCHAR2(100),
    lessonDuration NUMBER,
    homeworkId NUMBER,
    courseId NUMBER,
    lesson_number NUMBER
);

CREATE TABLE Homework
(  
    homeworkId NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    homeworkName VARCHAR2(20),
    homeworkDeadlineDate DATE
);

CREATE TABLE Task
(   
    taskId NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    homeworkId NUMBER,
    taskDescription VARCHAR2(200)
);

CREATE TABLE Schedule
(  
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    group_id NUMBER,
    date_lesson DATE,
    lesson_id NUMBER 
);

CREATE TABLE Attendance
(   
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    user_id NUMBER,
    type_id NUMBER,
    schedule_id NUMBER
);

CREATE TABLE AttendanceType
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    type VARCHAR2(20)
);

CREATE TABLE Material
(   
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    lesson_id NUMBER,
    description VARCHAR2(1000)
);

CREATE TABLE Feedback
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    trainer_id NUMBER,
    employee_id NUMBER,
    description VARCHAR2(4000),
    date_feedback DATE,
    course_id NUMBER 
);

CREATE TABLE InfoDesk
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    emp_id NUMBER,
    description VARCHAR2(4000),
    status_id NUMBER
);

CREATE TABLE QuestionStatus
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    name VARCHAR2(20)
);

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

CREATE TABLE UserChat
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    user_id NUMBER,
    chat_id NUMBER
);

CREATE TABLE NotificationStatus
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    name VARCHAR2(20)
);

CREATE TABLE DesiredPeriod
(   
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    date_start DATE,
    date_end DATE,
    user_id NUMBER,
    cource_id NUMBER
);

CREATE TABLE Weekday
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    day_name VARCHAR2(20),
    period_id NUMBER
);

CREATE TABLE GroupStatus
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    name_status VARCHAR2(20)
);

CREATE TABLE CourceStatus
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    name_status VARCHAR2(20)
);





--delete dependencies
alter table users drop constraint user_fr_manager;
alter table users drop constraint users_fk_roles;
alter table Notification drop constraint ntfk_fk_user;
alter table Notification drop constraint ntfk_fk_status;
alter table chat drop constraint chat_fk_group;
alter table InfoDesk drop constraint idesk_emp_fk_user;
alter table InfoDesk drop constraint idesk_fk_qstatus;
alter table Feedback drop constraint fdbk_tr_fk_user;
alter table Feedback drop constraint fdbk_fk_cource;
alter table Feedback drop constraint fdbk_emp_fk_user;
alter table UserGroup drop constraint ugroup_fk_user;
alter table UserGroup drop constraint ugroup_fk_group;
alter table Groups drop constraint group_fk_course;
alter table Groups drop constraint group_fr_status;
alter table UserChat drop constraint uchat_fk_user;
alter table UserChat drop constraint uchat_fk_chat;
alter table Attendance drop constraint atten_fk_user;
alter table Attendance drop constraint atten_fk_schedule;
alter table Attendance drop constraint atten_fk_type;
alter table Schedule drop constraint schedule_fk_group;
alter table Schedule drop constraint schedule_fk_lesson;
alter table Lesson drop constraint lesson_fk_homework;
alter table Lesson drop constraint lesson_fk_course;
alter table Material drop constraint material_fk_lesson;
alter table Task drop constraint task_fk_homework;
alter table DesiredPeriod drop constraint dperiod_fk_user;
alter table DesiredPeriod drop constraint dperiod_fk_cource;
alter table Weekday drop constraint weekday_fk_dperiod;
alter table Course drop constraint cource_fk_status;
alter table Course drop constraint cource_fk_trainer;
alter table Message drop constraint message_fk_user;
alter table Message drop constraint message_fk_chat;




--create dependencies
alter table users add constraint user_fr_manager FOREIGN KEY(managerId) references users (userId);
alter table users ADD constraint users_fk_roles FOREIGN KEY(roleId) references roles (roleId);
alter table Notification ADD constraint ntfk_fk_user FOREIGN KEY (user_id) references users (userId);
alter table Notification add constraint ntfk_fk_status FOREIGN KEY (status_id) references NotificationStatus (id);
alter table chat add constraint chat_fk_group FOREIGN KEY (group_id) references groups (id);
alter table InfoDesk add constraint idesk_emp_fk_user FOREIGN KEY (emp_id) references users (userId);
alter table InfoDesk add constraint idesk_fk_qstatus FOREIGN KEY (status_id) references QuestionStatus (id);
alter table Feedback add constraint fdbk_tr_fk_user FOREIGN KEY (trainer_id) references users (userId);
alter table Feedback add constraint fdbk_fk_cource FOREIGN KEY (course_id) references course (courceId);
alter table Feedback add constraint fdbk_emp_fk_user FOREIGN KEY (employee_id) references users (userId);
alter table UserGroup add constraint ugroup_fk_user FOREIGN KEY (user_id) references users (userId);
alter table UserGroup add constraint ugroup_fk_group FOREIGN KEY (group_id) references Groups (id);
alter table Groups add constraint group_fk_course FOREIGN KEY (course_id) references Course (courceId);
alter table Groups add constraint group_fr_status FOREIGN KEY (status_id) references GroupStatus (id);
alter table UserChat add constraint uchat_fk_user FOREIGN KEY (user_id) references users (userId);
alter table UserChat add constraint uchat_fk_chat FOREIGN KEY (chat_id) references Chat (id);
alter table Attendance add constraint atten_fk_user FOREIGN KEY (user_id) references users (userId);
alter table Attendance add constraint atten_fk_schedule FOREIGN KEY (schedule_id) references Schedule (id);
alter table Attendance add constraint atten_fk_type FOREIGN KEY (type_id) references AttendanceType(id);
alter table Schedule add constraint schedule_fk_group FOREIGN KEY (group_id) references Groups (id);
alter table Schedule add constraint schedule_fk_lesson FOREIGN KEY (lesson_id) references Lesson(lessonId);
alter table Lesson add constraint lesson_fk_homework FOREIGN KEY (homeworkId) references Homework(homeworkId);
alter table Lesson add constraint lesson_fk_course FOREIGN KEY (courseId) references Course (courceId);
alter table Material add constraint material_fk_lesson FOREIGN KEY (lesson_id) references Lesson (lessonId);
alter table Task add constraint task_fk_homework FOREIGN KEY (homeworkId) references Homework (homeworkId);
alter table DesiredPeriod add constraint dperiod_fk_user FOREIGN KEY (user_id) references users (userId);
alter table DesiredPeriod add constraint dperiod_fk_cource FOREIGN KEY (cource_id) references Course (courceId);
alter table Weekday add constraint weekday_fk_dperiod FOREIGN KEY (period_id) references DesiredPeriod (id);
alter table Course add constraint cource_fk_status FOREIGN KEY (course_status_id) references CourceStatus(id);
alter table Course add constraint cource_fk_trainer FOREIGN KEY (trainer_id) references users (userId);
alter table Message add constraint message_fk_user FOREIGN KEY (sender_id) references users (userId);
alter table Message add constraint message_fk_chat FOREIGN KEY (chat_id) references Chat(id);











