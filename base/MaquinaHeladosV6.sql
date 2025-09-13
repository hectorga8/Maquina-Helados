create table helados (
    posicion integer not null PRIMARY key,
    nombre varchar(255) not null,
    precio float not null,
    tipo varchar(50) not null,
    cantidad integer not null
);

create table venta (
    id integer primary key,
    fecha_hora timestamp default current_timestamp,
    posicion integer not null,
    foreign key (posicion) references helado(posicion)
);


insert into helados (posicion, nombre, precio, tipo, cantidad)
VALUES
(0,"ChocoBomba",1.1,"tarrina",5),
(1,"FresaGuapi",0.8,"palo",5),
(2,"LimonCanela", 1.5, "cucurucho",5),
(3,"FrigLemon", 1.8, "tarrina",5),
(4,"PiñaHelada", 1, "palo",5),
(5,"MoraGuay", 1.2, "cucurucho",5),
(6,"ChochoLoco", 1.8, "tarrina",5),
(7,"TuttiFrutti", 1.3, "palo",5),
(8,"MentaAzul", 1.2, "cucurucho",5);

