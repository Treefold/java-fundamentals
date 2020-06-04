-- test table and mock data
create table test (x number);
insert into test values (17);
select * from test;
drop table test;

-- departments table and mock data
create table departments (
    dept_id number primary key,
    name varchar2(25) unique
);
insert into departments values (1, 'Algebra');

-- teachers table and mock data
create table teachers (
    teacher_id number primary key,
    cnp varchar2 (15),
    surname varchar2(30),
    name varchar2(30),
    gender varchar2(10),
    phone varchar2(15),
    mail varchar2(30),
    salary number (10,2),
    job varchar2 (15),
    hire_date varchar2(15),
    subject varchar2(20),
    dept_id number,
    constraint fk_teachers_dept foreign key (dept_id) references departments(dept_id)
);
insert into teachers (teacher_id) values (10);
insert into teachers (teacher_id) values (1);
insert into teachers (teacher_id, dept_id) values (2, 1);

-- classes table and mock data
create table classes (
    class_id number primary key,
    name varchar2(25) unique
);
insert into classes values (1, '2321');
insert into classes values (2, '2322');
    
-- students table and mock data
create table students (
    student_id number primary key,
    cnp varchar2 (15),
    surname varchar2(30),
    name varchar2(30),
    gender varchar2(10),
    phone varchar2(15),
    mail varchar2(30),
    class_id number,
    constraint fk_student_class foreign key (class_id) references classes(class_id)
);
insert into students(student_id) values (1);
insert into students(student_id, class_id) values (2, 1);
insert into students(student_id, class_id) values (3, 2);

commit;

---- hours table and mock data
--create table hours (
--    class_id number,
--    teacher_id number,
--    place varchar2 (20),
--    weekday number (1),
--    beginsAt number (1),
--    
--    constraint c_hour_day   check (weekday  >= 0 and weekday <= 4),
--    constraint c_hour_begin check (beginsAt >= 0 and beginsAt <= 5),
--    constraint fk_hour_class   foreign key (class_id)   references classes  (class_id),
--    constraint fk_hour_teacher foreign key (teacher_id) references teachers (teacher_id)
--);
--insert into hours values (1, 2, null, 2, 2);

commit;
