drop database MyJoinsDB;
create database MyJoinsDB;
use myjoinsdb;
create table employees
(
    emp_id int auto_increment,
    name   varchar(15),
    phone  varchar(15),
    primary key (emp_id)
);
create table positions
(
    emp_id   int,
    salary   int,
    position varchar(10),
    foreign key (emp_id) references employees (emp_id)
);
create table personal_info
(
    emp_id        int,
    family_status varchar(10),
    birth_date    date,
    city          varchar(10),
    foreign key (emp_id) references employees (emp_id)
);
insert into employees (name, phone)
VALUES ('Olena', '0999999999'),
       ('Oleg', '0505050505'),
       ('Vasyl', '0666666666');
insert into positions (emp_id, salary, position)
values (1, 3000, 'manager'),
       (2, 1000, 'clerk'),
       (3, 5000, 'director');
insert into personal_info (emp_id, family_status, birth_date, city)
VALUES (1, 'single', '1990-11-10', 'Kyiv'),
       (2, 'single', '2000-11-10', 'Kyiv'),
       (3, 'married', '1995-11-10', 'Vienn');

SELECT e.name, e.phone, p_info.city from employees e inner join personal_info p_info on e.emp_id = p_info.emp_id;
select * from employees;
select * from personal_info;

select e.name, e.phone, pi.birth_date from employees e inner join personal_info pi on e.emp_id = pi.emp_id where family_status = 'single';

SELECT e.name, e.phone, pi.birth_date from employees e inner join personal_info pi on e.emp_id = pi.emp_id
inner join positions on e.emp_id = positions.emp_id where position = 'manager';