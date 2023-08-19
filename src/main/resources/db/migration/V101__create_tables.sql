create table if not exists user_account
(
    id         bigserial primary key,
    name       varchar(255),
    email      varchar(255),
    password   varchar(255)
);

create table if not exists post
(
    id              bigserial primary key,
    title           varchar(255),
    content         varchar(1000),
    creation_date   date,
    user_id         bigserial,

    foreign key (user_id) references user_account (id)
    on delete cascade
    on update cascade
);

create table if not exists subscription
(
    user_id         bigserial,
    subscriber_id   bigserial,

    foreign key (user_id) references user_account (id)
    on delete cascade
    on update cascade,
    foreign key (subscriber_id) references user_account (id)
    on delete cascade
    on update cascade
);

create table if not exists chat_status
(
    id     bigserial primary key,
    name   varchar(255)
);

create table if not exists chat
(
    id              bigserial primary key,
    creation_date   date,
    creator_id      bigserial,
    user_id         bigserial,
    chat_status_id  bigserial,

    foreign key (creator_id) references user_account (id)
    on delete cascade
    on update cascade,
    foreign key (user_id) references user_account (id)
    on delete cascade
    on update cascade,
    foreign key (chat_status_id) references chat_status (id)
    on delete restrict
    on update cascade
);

create table if not exists message
(
    id           bigserial primary key,
    content      varchar(1000),
    send_date    date,
    sender_id    bigserial,
    chat_id      bigserial,

    foreign key (sender_id) references user_account (id)
    on delete cascade
    on update cascade,
    foreign key (chat_id) references chat (id)
    on delete cascade
    on update cascade
);








