
drop table user if exists;

create table user (
  int integer,
  name varchar(20)
);

insert into user  values(1, 'hg' );
insert into user values(2, 'Opel' );



drop table if exists mail;

create table mail
(
  id          int         auto_increment not null comment '主键id',
  create_time datetime    not null  comment '创建时间',
  modify_time timestamp   not null  comment '修改时间',
  web_id      int         not null  comment '站点id，1表示新浪，2表示QQ，3表示搜狐，4表示火狐',
  mail        varchar(50) not null  comment '邮箱名',
  use_for     varchar(30)           comment '邮箱用途',
  primary key(id),
  index use_for(use_for),
  unique index web_id_mail(web_id, mail)
)charset=utf8 engine=innodb comment='邮箱表';