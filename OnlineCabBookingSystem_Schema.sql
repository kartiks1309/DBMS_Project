
-- Tables Creation start

#Customer Table
create table customer(
   Username varchar(100) not null,
   Email varchar(320) unique not null,
   Phone bigint not null,
   Pin varchar(100) not null,
   primary key(Email)
);

#Cab Table
create table cablist(
   Cabid varchar(50) unique not null,
   Drivername varchar(100) not null,
   Driverphone varchar(20) not null,
   City varchar(100) not null,
   Avail int not null,
   primary key( Cabid)
);

#Admins Table
create table admins(
   Id varchar(100) unique not null,
   Pin varchar(100) not null,
   primary key(Id)
);

#Cab Bookings Table
create table bookings(
   BookingId int not null auto_increment,
   CabId varchar(50) not null,
   Email varchar(320) not null,
   StartLoc varchar(400) not null,
   EndLoc varchar(400) not null,
   Distance int not null,
   BillAmount int not null,
   primary key(BookingId)
);

-- Tables Creation end

-- Filling Admins Table

insert into admins VALUES ("IIT2020126", "123456");
insert into admins VALUES ("IIT2020127", "123456"); 
insert into admins VALUES ("IIT2020128", "123456"); 
insert into admins VALUES ("IIT2020130", "123456");  

-- Queries to view all tables
SELECT * FROM cabs_db.customer;
SELECT * FROM cabs_db.cablist;
SELECT * FROM cabs_db.admins;
SELECT * FROM cabs_db.bookings;
