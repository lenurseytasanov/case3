create table if not exists Employee
(
    id bigint generated always as identity,
    primary key (id)
);

create table if not exists Task
(
    id bigint generated always as identity,
    text text not null,
    employee bigint not null,
    project bigint not null,
    parent bigint,
    state text not null,
    stage text not null,
    priority bigint not null,
    type text not null,
    estimation bigint not null,
    primary key (id),
    foreign key (employee) references Employee(id),
    foreign key (project) references Project(id),
    foreign key (parent) references Task(id)
);

create table if not exists Project
(
    id bigint generated always as identity,
    team bigint not null,
    customer bigint not null,
    primary key (id),
    foreign key (team) references Team(id),
    foreign key (customer) references Customer(id)
);

create table if not exists Customer
(
    id bigint generated always as identity
    primary key (id)
);

create table if not exists Employee
(
    id bigint generated always as identity,
    primary key (id)
);
