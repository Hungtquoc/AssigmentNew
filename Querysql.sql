select s.id,gid,gname ,timeid, date, roomid, lid, g.courseid from [Session] s
                    inner join [Group] g on g.id=s.gid and s.lid='4'
                    
select s.id, s.gid,g.gname,g.courseid.timeid, s.date, s.roomid,s.lid,s.status from [Session] s inner join [Group] g on
                    s.gid= g.id and s.lid= '5' where s.date >=? and s.date <=?
select s.id, s.sname, g.id, g.lectureid, date from Student s inner join stu_group sg
on s.id=sg.sid inner join [Group] g on g.id= sg.groupid
inner join [Session] ses on ses.gid = g.id
select * from Attendance
select a.id, a.sid,a.sesid,s.date,s.gid, checked from Attendance a inner join Session s
on a.sesid= s.id
ALTER TABLE Attendance
ALTER COLUMN checked bit  NULL;
select a.id, a.sid,a.sesid,s.date,s.gid,checked from Attendance a inner join Session s
                    on a.sesid= s.id
select s.id,s.gid,g.gname ,s.timeid, s.date, s.roomid, s.lid, g.courseid,[status] from [Session] s
inner join [Group] g on g.id=s.gid inner join Slot sl on sl.id= s.timeid where s.lid=5 and s.date>='2022-07-11' and s.date<='2022-07-21'
select s.id as studentid,s.sname,g.id,l.id,l.lname,ses.id,g.courseid,g.gname,g.courseid,ses.roomid,ses.timeid,ses.date,c.cname from Student s inner join stu_group sg 
on s.id=sg.sid inner join [Group] g 
on g.id= sg.groupid inner join Lecture l 
on l.id= g.lectureid inner join Session ses
on ses.gid = g.id inner join course c
on c.id= g.courseid

select * from Attendance 
insert into Attendance (sesid, sid, checked)
 select s.id,s.gid,g.gname ,s.timeid, s.date, s.roomid, s.lid, g.courseid from [Session] s
                    inner join [Group] g on g.id=s.gid 
                    inner join slot sl on sl.id = s.timeid 
                    where s.id= 3
select a.aid , a.checked , a.sesid , a.[sid] , s.sname from Attendance a 
inner join [Session] se on a.sesid = se.id
inner join [Student] s on a.[sid] = s.id
where a.[sesid] = ?

