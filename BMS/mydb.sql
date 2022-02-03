create database Bankmanagementsystem;

use Bankmanagementsystem;

create table signup(formno varchar(20) primary key, name varchar(30), fname varchar(30), dob varchar(20), gender varchar(20), email varchar(20), marital varchar(20), address varchar(40), city varchar(20), pincode varchar(20), state varchar(30));

create table PersonalDetails(formno varchar(20), religion varchar(20), category varchar(30), income varchar(30), education varchar(20), occupation varchar(20), pan varchar(20), aadhar varchar(20), scitizen varchar(40), eaccount varchar(20),foreign key (formno) references signup(formno) on delete cascade);

create table AccountDetails(formno varchar(20), atype varchar(20), cardno varchar(30), pin varchar(20), facility varchar(100),foreign key (formno) references signup(formno) on delete cascade,primary key(pin));

create table login(formno varchar(20), cardno varchar(30), pin varchar(20), foreign key (formno) references signup(formno) on delete cascade);

create table bank(pin varchar(10), date varchar(40), mode varchar(30), amount varchar(20));
