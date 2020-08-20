drop table if exists  department;
create table department ( 
   did int(20) not null auto_increment,
	 dname varchar(255) default null,
	 jobdesc varchar(255) ,
	 primary key(did)
)engine = innodb default charset utf8;

drop table if exists employee;

create table employee (
    eid int(20) not null auto_increment,
		ename varchar(255),
		gender int(2),
		birthday datetime,
		telnumber varchar(11),
		jobname varchar(255),
		did int(20) ,
		PRIMARY key(eid),
		CONSTRAINT fk_did FOREIGN key (did) REFERENCES department(did)
)engine = innodb default charset utf8;

/*
 2、支持手机号数据脱敏
*/
select concat(left(telnumber,3),'****',right(telnumber,4)) as telephone  from employee;

/*
3、SQL统计各部门的员工人数
*/
select did,count(*) from employee group by did;

/*
4、SQL查询每个部门年龄最大的员工
*/
select max(TIMESTAMPDIFF(YEAR,birthday,SYSDATE())) years,did from employee group by did;
select e.eid,e.ename,e.gender,e.telnumber,e.jobname
from 
(select max(TIMESTAMPDIFF(YEAR,birthday,SYSDATE())) years,did 
       from employee group by did) my 
			  inner join employee e on my.did=e.did 
				 where TIMESTAMPDIFF(YEAR,e.birthday,SYSDATE())=my.years ;

/*
5、SQL手机号模糊查询
*/
 

