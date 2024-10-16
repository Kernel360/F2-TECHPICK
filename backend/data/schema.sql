create database if not exists techpick_db_v1;

create table techpick_db_v1.job_group
(
    id             bigint auto_increment primary key,
    job_group_name varchar(255) not null,
    constraint UK1tcimcnvw2qo8ivpeyxtmvskw
        unique (job_group_name)
);

create table techpick_db_v1.link
(
    id          bigint auto_increment primary key,
    description varchar(600) null,
    image_url   varchar(600) null,
    title       varchar(100) not null,
    url         varchar(600) not null,
    constraint UK4dycbe6q8trcendnql3b13cuf
        unique (url)
);

create table techpick_db_v1.link_view_event
(
    id         bigint auto_increment primary key,
    created_at datetime(6) not null,
    updated_at datetime(6) not null,
    link_id    bigint      not null,
    user_id    bigint      not null
);

create table techpick_db_v1.rss_blog
(
    id           bigint auto_increment primary key,
    created_at   datetime(6)  not null,
    updated_at   datetime(6)  not null,
    rss_feed_url varchar(255) not null,
    constraint UKocjrcnjap3hyou2vnr52ahd12
        unique (rss_feed_url)
);

create table techpick_db_v1.rss_raw_data
(
    id                     bigint auto_increment primary key,
    created_at             datetime(6)  not null,
    updated_at             datetime(6)  not null,
    creator                varchar(255) null,
    description            longblob     null,
    guid                   varchar(255) null,
    joined_category        varchar(255) null,
    published_at           varchar(255) null,
    rss_supporting_blog_id bigint       null,
    title                  varchar(255) null,
    url                    varchar(600) null
);

create table techpick_db_v1.user
(
    id                 bigint auto_increment primary key,
    created_at         datetime(6)                                    not null,
    updated_at         datetime(6)                                    not null,
    age_group          enum ('G10', 'G20', 'G30', 'G40', 'G50')       null,
    deleted_at         datetime(6)                                    null,
    email              varchar(255)                                   not null,
    nickname           varchar(255)                                   not null,
    password           varchar(255)                                   null,
    role               enum ('ROLE_ADMIN', 'ROLE_GUEST', 'ROLE_USER') not null,
    social_provider    enum ('GOOGLE', 'KAKAO', 'NAVER')              null,
    social_provider_id varchar(255)                                   null,
    tel                varchar(255)                                   null,
    job_group_id       bigint                                         null,
    constraint FKonvrpl22wvb63ajpi7u8yc9s
        foreign key (job_group_id) references techpick_db_v1.job_group (id)
);

create table techpick_db_v1.folder
(
    id               bigint auto_increment primary key,
    created_at       datetime(6)                                             not null,
    updated_at       datetime(6)                                             not null,
    folder_type      enum ('GENERAL', 'RECYCLE_BIN', 'ROOT', 'UNCLASSIFIED') not null,
    name             varchar(255)                                            not null,
    parent_folder_id bigint                                                  null,
    user_id          bigint                                                  not null,
    constraint FK57g7veis1gp5wn3g0mp0x57pl
        foreign key (parent_folder_id) references techpick_db_v1.folder (id)
            on delete cascade,
    constraint FK5fd2civdi8s832iyrufpk400k
        foreign key (user_id) references techpick_db_v1.user (id),
    constraint UC_FOLDER_NAME_PER_USER
        unique(user_id, name, parent_folder_id)
);

create table techpick_db_v1.folder_structure
(
    id         bigint auto_increment primary key,
    created_at datetime(6) not null,
    updated_at datetime(6) not null,
    structure  longblob    not null,
    user_id    bigint      null,
    constraint UKr3mlb0f6nq0j5yvv5wjrejebe
        unique (user_id),
    constraint FKehshurqty8xg1nocea0qouhub
        foreign key (user_id) references techpick_db_v1.user (id)
);

create table techpick_db_v1.pick
(
    id               bigint auto_increment primary key,
    created_at       datetime(6)  not null,
    updated_at       datetime(6)  not null,
    custom_title     varchar(255) null,
    memo             varchar(255) null,
    link_id          bigint       not null,
    parent_folder_id bigint       not null,
    user_id          bigint       not null,
    constraint FKbilrp2m7mc9ssut5d85loj5d7
        foreign key (user_id) references techpick_db_v1.user (id),
    constraint FKf3o2jbamw9l96i1lwvaytuik7
        foreign key (link_id) references techpick_db_v1.link (id),
    constraint FKhfrafg7f40ym7wgrtp9j45pha
        foreign key (parent_folder_id) references techpick_db_v1.folder (id)
            on delete cascade
);

create table techpick_db_v1.tag
(
    id           bigint auto_increment primary key,
    color_number int          not null,
    name         varchar(255) not null,
    tag_order    int          not null,
    user_id      bigint       not null,
    constraint FKld85w5kr7ky5w4wda3nrdo0p8
        foreign key (user_id) references techpick_db_v1.user (id)
);

create table techpick_db_v1.pick_tag
(
    id      bigint auto_increment
primary key,
    pick_id bigint not null,
    tag_id  bigint not null,
    constraint FK9e42g0lyb0ss1pjhvdrqqh0a8
        foreign key (tag_id) references techpick_db_v1.tag (id),
    constraint FKcbtnw1dxhgh641h8yjp9nwnav
        foreign key (pick_id) references techpick_db_v1.pick (id)
);