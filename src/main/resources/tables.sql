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
drop TABLE CourseStatus;



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

INSERT INTO users(name, email, password, enabled, roleId)
        VALUES ('adminBoss', 'boss@gmail.com', 'password123', 1, 1);
INSERT INTO users(name, email, password, enabled, roleId)
        VALUES ('userTrainer1', 'userTrainer@gmail.com', 'password123', 1, 3);
INSERT INTO users(name, email, password, enabled, roleId)
        VALUES ('userTrainer2', 'userTrainer@gmail.com', 'password123', 1, 3);
INSERT INTO users(name, email, password, enabled, roleId)
        VALUES ('userTrainer3', 'userTrainer@gmail.com', 'password123', 1, 3);
INSERT INTO users(name, email, password, enabled, roleId)
        VALUES ('userTrainer4', 'userTrainer@gmail.com', 'password123', 1, 3);
INSERT INTO users(name, email, password, enabled, roleId)
        VALUES ('userTrainer5', 'userTrainer@gmail.com', 'password123', 1, 3);
INSERT INTO users(name, email, password, enabled, roleId)
        VALUES ('userTrainer6', 'userTrainer@gmail.com', 'password123', 1, 3);
INSERT INTO users(name, email, password, enabled, roleId)
        VALUES ('userTrainer7', 'userTrainer@gmail.com', 'password123', 1, 3);
INSERT INTO users(name, email, password, enabled, roleId)
        VALUES ('userTrainer8', 'userTrainer@gmail.com', 'password123', 1, 3);
INSERT INTO users(name, email, password, enabled, roleId)
        VALUES ('userManager1', 'userManager@gmail.com', 'password123', 1, 4);
INSERT INTO users(name, email, password, enabled, roleId)
        VALUES ('userManager2', 'userManager@gmail.com', 'password123', 1, 4);
INSERT INTO users(name, email, password, enabled, roleId)
        VALUES ('userManager3', 'userManager@gmail.com', 'password123', 1, 4);
INSERT INTO users(name, email, password, enabled, roleId)
        VALUES ('userManager4', 'userManager@gmail.com', 'password123', 1, 4);
INSERT INTO users(name, email, password, enabled, roleId)
        VALUES ('userManager5', 'userManager@gmail.com', 'password123', 1, 4);
INSERT INTO users(name, email, password, enabled, roleId)
        VALUES ('userManager6', 'userManager@gmail.com', 'password123', 1, 4);
INSERT INTO users(name, email, password, enabled, roleId)
        VALUES ('userManager7', 'userManager@gmail.com', 'password123', 1, 4);
INSERT INTO users(name, email, password, enabled, roleId)
        VALUES ('userManager8', 'userManager@gmail.com', 'password123', 1, 4);
INSERT INTO users(name, email, password, enabled, roleId)
        VALUES ('userManager9', 'userManager@gmail.com', 'password123', 1, 4);
INSERT INTO users(name, email, password, enabled, roleId)
        VALUES ('userManager10', 'userManager@gmail.com', 'password123', 1, 4);
        INSERT INTO users(name, email, password, enabled, roleId, managerId)
        VALUES ('userUser1', 'userUser@gmail.com', 'password123', 1, 2, 10);
INSERT INTO users(name, email, password, enabled, roleId, managerId)
        VALUES ('userUser2', 'userUser@gmail.com', 'password123', 1, 2, 10);
INSERT INTO users(name, email, password, enabled, roleId, managerId)
        VALUES ('userUser3', 'userUser@gmail.com', 'password123', 1, 2, 11);
INSERT INTO users(name, email, password, enabled, roleId, managerId)
        VALUES ('userUser4', 'userUser@gmail.com', 'password123', 1, 2, 11 );
INSERT INTO users(name, email, password, enabled, roleId, managerId)
        VALUES ('userUser5', 'userUser@gmail.com', 'password123', 1, 2, 11);
INSERT INTO users(name, email, password, enabled, roleId, managerId)
        VALUES ('userUser6', 'userUser@gmail.com', 'password123', 1, 2, 12);
INSERT INTO users(name, email, password, enabled, roleId, managerId)
        VALUES ('userUser7', 'userUser@gmail.com', 'password123', 1, 2, 13);
INSERT INTO users(name, email, password, enabled, roleId, managerId)
        VALUES ('userUser8', 'userUser@gmail.com', 'password123', 1, 2, 13);
INSERT INTO users(name, email, password, enabled, roleId, managerId)
        VALUES ('userUser9', 'userUser@gmail.com', 'password123', 1, 2, 15);
INSERT INTO users(name, email, password, enabled, roleId, managerId)
        VALUES ('userUser10', 'userUser@gmail.com', 'password123', 1, 2, 17);



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
    courseId NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    name VARCHAR2(50),
    course_level VARCHAR2(40),
    course_status_id NUMBER,
    min_number NUMBER,
    max_number NUMBER,
    description VARCHAR2(1000),
    trainer_id NUMBER,
    lessons_number NUMBER

);

INSERT INTO Course(name, course_level,course_status_id,min_number, max_number,description,trainer_id,lessons_number)
        VALUES ('Amazing English', 'B2', 1,5,30,'very good course', 2,10);
INSERT INTO Course(name, course_level,course_status_id,min_number, max_number,description,trainer_id,lessons_number)
        VALUES ('Good English', 'B2', 1,5,30,'very good course', 3,10);
INSERT INTO Course(name, course_level,course_status_id,min_number, max_number,description,trainer_id,lessons_number)
        VALUES ('Very good English', 'B2', 1,5,30,'very good course', 4,10);
INSERT INTO Course(name, course_level,course_status_id,min_number, max_number,description,trainer_id,lessons_number)
        VALUES ('Very amazing English', 'B2', 1,5,30,'very good course', 5,10);
INSERT INTO Course(name, course_level,course_status_id,min_number, max_number,description,trainer_id,lessons_number)
        VALUES ('Amazing very good English', 'B2', 1,5,30,'very good course', 6,10);



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
    lessonId NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    lessonName VARCHAR2(20),
    lessonDescription VARCHAR2(100),
    lessonDuration NUMBER,
    homeworkId NUMBER,
    courseId NUMBER,
    lesson_number NUMBER
);

INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-1','very helpful description', 60, 1, 1, 1);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-2','very helpful description', 60, 2, 1, 2);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-3','very helpful description', 60, 3, 1, 3);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-4','very helpful description', 60, 4, 1, 4);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-5','very helpful description', 60, 5, 1, 5);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-6','very helpful description', 60, 6, 1, 6);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-7','very helpful description', 60, 7, 1, 7);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-8','very helpful description', 60, 8, 1, 8);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-9','very helpful description', 60, 9, 1, 9);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-10','very helpful description', 60, 10, 1, 10);

INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-1','very helpful description', 60, 1, 2, 1);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-2','very helpful description', 60, 2, 2, 2);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-3','very helpful description', 60, 3, 2, 3);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-4','very helpful description', 60, 4, 2, 4);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-5','very helpful description', 60, 5, 2, 5);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-6','very helpful description', 60, 6, 2, 6);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-7','very helpful description', 60, 7, 2, 7);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-8','very helpful description', 60, 8, 2, 8);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-9','very helpful description', 60, 9, 2, 9);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-10','very helpful description', 60, 10, 2, 10);

INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-1','very helpful description', 60, 1, 3, 1);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-2','very helpful description', 60, 2, 3, 2);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-3','very helpful description', 60, 3, 3, 3);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-4','very helpful description', 60, 4, 3, 4);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-5','very helpful description', 60, 5, 3, 5);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-6','very helpful description', 60, 6, 3, 6);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-7','very helpful description', 60, 7, 3, 7);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-8','very helpful description', 60, 8, 3, 8);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-9','very helpful description', 60, 9, 3, 9);
INSERT INTO Lesson (lessonName,lessonDescription, lessonDuration, homeworkId, courseId, lesson_number)
            values ('Lesson-10','very helpful description', 60, 10, 3, 10);


CREATE TABLE Homework
(
    homeworkId NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    homeworkName VARCHAR2(20),
    homeworkDeadlineDate DATE
);

INSERT INTO Homework (homeworkName) values ('Homework-1');
INSERT INTO Homework (homeworkName) values ('Homework-2');
INSERT INTO Homework (homeworkName) values ('Homework-3');
INSERT INTO Homework (homeworkName) values ('Homework-4');
INSERT INTO Homework (homeworkName) values ('Homework-5');
INSERT INTO Homework (homeworkName) values ('Homework-6');
INSERT INTO Homework (homeworkName) values ('Homework-7');
INSERT INTO Homework (homeworkName) values ('Homework-8');
INSERT INTO Homework (homeworkName) values ('Homework-9');
INSERT INTO Homework (homeworkName) values ('Homework-10');

CREATE TABLE Task
(
    taskId NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    homeworkId NUMBER,
    taskDescription VARCHAR2(200)
);

INSERT INTO Task (homeworkId, taskDescription) values (1, 'some helpful description1');
INSERT INTO Task (homeworkId, taskDescription) values (1, 'some helpful description2');
INSERT INTO Task (homeworkId, taskDescription) values (1, 'some helpful description2');
INSERT INTO Task (homeworkId, taskDescription) values (2, 'some helpful description1');
INSERT INTO Task (homeworkId, taskDescription) values (2, 'some helpful description2');
INSERT INTO Task (homeworkId, taskDescription) values (3, 'some helpful description1');
INSERT INTO Task (homeworkId, taskDescription) values (3, 'some helpful description2');
INSERT INTO Task (homeworkId, taskDescription) values (3, 'some helpful description3');
INSERT INTO Task (homeworkId, taskDescription) values (4, 'some helpful description');
INSERT INTO Task (homeworkId, taskDescription) values (5, 'some helpful description');
INSERT INTO Task (homeworkId, taskDescription) values (6, 'some helpful description1');
INSERT INTO Task (homeworkId, taskDescription) values (6, 'some helpful description2');


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

CREATE TABLE AttendanceType
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    type VARCHAR2(70)
);

INSERT INTO AttendanceType (type)values('Present');
INSERT INTO AttendanceType (type)values('Absent without reason');
INSERT INTO AttendanceType (type)values('Absent due tu bussines trip');
INSERT INTO AttendanceType (type)values('Absent due to sick leave');
INSERT INTO AttendanceType (type)values('Absent due to project activities');

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

CREATE TABLE InfoDesk
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    emp_id NUMBER,
    description VARCHAR2(4000),
    status_id NUMBER
);

INSERT INTO InfoDesk (emp_id, description, status_id) values(21, 'Dear Admin, how does it work?',1);
INSERT INTO InfoDesk (emp_id, description, status_id) values(21, 'Dear Admin, who is my Manager?',2);
INSERT INTO InfoDesk (emp_id, description, status_id) values(21, 'Dear Admin, why is my Manager so angry?',3);
INSERT INTO InfoDesk (emp_id, description, status_id) values(21, 'Where am i?',4);
INSERT INTO InfoDesk (emp_id, description, status_id) values(21, 'Where is my money, Jhonny???',5);

CREATE TABLE QuestionStatus
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    name VARCHAR2(20)
);


INSERT INTO QuestionStatus (name) values('Draft');
INSERT INTO QuestionStatus (name) values('Open');
INSERT INTO QuestionStatus (name) values('In Progress');
INSERT INTO QuestionStatus (name) values('Answered');
INSERT INTO QuestionStatus (name) values('Reopen');




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

INSERT INTO NotificationStatus (name) values('Posted');
INSERT INTO NotificationStatus (name) values('Not posted');
INSERT INTO NotificationStatus (name) values('Posted');
INSERT INTO NotificationStatus (name) values('Opened');
INSERT INTO NotificationStatus (name) values('Closed');

CREATE TABLE DesiredPeriod
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    date_start DATE,
    date_end DATE,
    user_id NUMBER,
    course_id NUMBER
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

INSERT INTO GroupStatus (name_status) values ('Open');
INSERT INTO GroupStatus (name_status) values ('Full');

CREATE TABLE CourseStatus
(
    id NUMBER GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1) NOT NULL PRIMARY KEY,
    name_status VARCHAR2(20)
);


INSERT INTO CourseStatus (name_status) values ('Open');
INSERT INTO CourseStatus (name_status) values ('Closed');
INSERT INTO CourseStatus (name_status) values ('Stoped');



COMMIT;


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
alter table Feedback add constraint fdbk_fk_cource FOREIGN KEY (course_id) references course (courseId);
alter table Feedback add constraint fdbk_emp_fk_user FOREIGN KEY (employee_id) references users (userId);
alter table UserGroup add constraint ugroup_fk_user FOREIGN KEY (user_id) references users (userId);
alter table UserGroup add constraint ugroup_fk_group FOREIGN KEY (group_id) references Groups (id);
alter table Groups add constraint group_fk_course FOREIGN KEY (course_id) references Course (courseId);
alter table Groups add constraint group_fr_status FOREIGN KEY (status_id) references GroupStatus (id);
alter table UserChat add constraint uchat_fk_user FOREIGN KEY (user_id) references users (userId);
alter table UserChat add constraint uchat_fk_chat FOREIGN KEY (chat_id) references Chat (id);
alter table Attendance add constraint atten_fk_user FOREIGN KEY (user_id) references users (userId);
alter table Attendance add constraint atten_fk_schedule FOREIGN KEY (schedule_id) references Schedule (id);
alter table Attendance add constraint atten_fk_type FOREIGN KEY (type_id) references AttendanceType(id);
alter table Schedule add constraint schedule_fk_group FOREIGN KEY (group_id) references Groups (id);
alter table Schedule add constraint schedule_fk_lesson FOREIGN KEY (lesson_id) references Lesson(lessonId);
alter table Lesson add constraint lesson_fk_homework FOREIGN KEY (homeworkId) references Homework(homeworkId);
alter table Lesson add constraint lesson_fk_course FOREIGN KEY (courseId) references Course (courseId);
alter table Material add constraint material_fk_lesson FOREIGN KEY (lesson_id) references Lesson (lessonId);
alter table Task add constraint task_fk_homework FOREIGN KEY (homeworkId) references Homework (homeworkId);
alter table DesiredPeriod add constraint dperiod_fk_user FOREIGN KEY (user_id) references users (userId);
alter table DesiredPeriod add constraint dperiod_fk_cource FOREIGN KEY (course_id) references Course (courseId);
alter table Weekday add constraint weekday_fk_dperiod FOREIGN KEY (period_id) references DesiredPeriod (id);
alter table Course add constraint cource_fk_status FOREIGN KEY (course_status_id) references CourseStatus(id);
alter table Course add constraint cource_fk_trainer FOREIGN KEY (trainer_id) references users (userId);
alter table Message add constraint message_fk_user FOREIGN KEY (sender_id) references users (userId);
alter table Message add constraint message_fk_chat FOREIGN KEY (chat_id) references Chat(id);











