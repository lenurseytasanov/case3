create table if not exists Team
(
    id bigint generated always as identity,
    primary key (id)
);

create table if not exists Customer
(
    id bigint generated always as identity,
    firstname text not null,
    lastname text not null,
    primary key (id)
);

create table if not exists Project
(
    id bigint generated always as identity,
    name text not null,
    team_id bigint,
    customer_id bigint,
    primary key (id),
    foreign key (team_id) references Team(id),
    foreign key (customer_id) references Customer(id)
);

create table if not exists Employee
(
    id bigint generated always as identity,
    team_id bigint,
    role text not null,
    firstname text not null,
    lastname text not null,
    username text unique not null,
    password text not null,
    primary key (id),
    foreign key (team_id) references Team(id)
);

create table if not exists Task
(
    id bigint generated always as identity,
    text text not null,
    employee_id bigint not null,
    project_id bigint not null,
    parent_task_id bigint,
    state text not null,
    stage text not null,
    priority bigint not null,
    type text not null,
    start_date timestamp with time zone not null,
    primary key (id),
    foreign key (employee_id) references Employee(id),
    foreign key (project_id) references Project(id),
    foreign key (parent_task_id) references Task(id)
);