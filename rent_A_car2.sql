create database rent_a_car;
create table cars(
id int primary key auto_increment,
name varchar(50),
vites varchar(50),
genre varchar(50),
price int,
customer_name varchar(50),
is_available varchar(15)
);

insert into cars (name,vites,genre,price,is_available, customer_name) values
("Fiat Punto", "Otomatik","Hatcpack",2000,"Açık","Rezerve Edilebilir"),
("Wolksvogen Polo","Otomatik","Hatcpack",5000,"Kapalı","Rezerve Edilebilir"),
("Honda Civic","Manuel","Sedan",3000,"Açık","Rezerve Edilebilir");
