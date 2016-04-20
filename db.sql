drop table if exists stu;
create table stu
(id int auto_increment primary key,
uname varchar(20),
psd char(32),
sex char(1),
xh char(3),
groupid int);

desc stu;

select * from stu;

