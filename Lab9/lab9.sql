create table lab9.Grades(
ID int,
SID int,
ClassName varchar(200),
Grade varchar(200)
);

create table lab9.StudentInfo(
SID int, 
Name varchar(200)
);

insert into lab9.Grades (ID, SID, ClassName, Grade)
	values (1, 111, "ART123", "F"),
			(2, 111, "BUS456", "A-"),
            (3, 111, "REL100", "D-"),
            (4, 113, "ECO966", "A-"),
            (5, 113, "BUS456", "B+"),
            (6, 112, "BUS456", "A"),
            (7, 112, "ECO966", "B+");
            
            
insert into lab9.StudentInfo (SID, Name)
	values(111, "Jack Xu"),
			(112, "Daniel Mizrahi"),
            (113, "Emily Jin");
            
