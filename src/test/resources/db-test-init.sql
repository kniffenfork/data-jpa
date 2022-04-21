create table users
(
    id          varchar primary key,
    phone       varchar(20)  not null,
    email       varchar(50)  not null,
    first_name  varchar(100) not null,
    last_name   varchar(100) not null,
    middle_name varchar(100)
);

create table rooms
(
    id          varchar primary key,
    room_number varchar(100) not null,
    floor       integer      not null,
    room_type   varchar(50),
    description varchar,
    price       integer      not null
);

create table bookings
(
    id        varchar primary key,
    check_in  date    not null,
    check_out date    not null,
    user_id   varchar not null references users,
    room_id   varchar not null references users
);
