create database bank;

use bank;

create table user (
   id int not null auto_increment,
   login varchar(50) not null,
   password varchar(200) not null,
   primary key (id)
);

insert into user (id, login, password) values (1, 'Profesor', '1234');
insert into user (id, login, password) values (2, 'Arturito', '4321');

create table bank (
   code varchar(4) not null,
   name varchar(100) not null,   
   primary key (code)   
);

insert into bank (code, name) values ('1234', 'Nairobi Bank');
insert into bank (code, name) values ('1235', 'Denver Bank');
insert into bank (code, name) values ('1236', 'Moscow Bank');
insert into bank (code, name) values ('1237', 'Tokyo Bank');

create table fav_account (
   id int not null auto_increment,
   user_id int not null,
   iban varchar(20) not null,
   fav_name varchar(200) not null,
   bank_name varchar(100),
   status varchar(100),
   primary key (id),
   foreign key (user_id) references user(id)
);

