-- create table if not exists account (id bigint, code varchar(32), name varchar(32), input_date date);}"
select * from account;
delete from account;
insert into account values(1,'001', '张三', '2015-05-13 17:06:02');
insert into account values(2,'002', '李四', '2015-06-13 17:06:02');
select * from account;