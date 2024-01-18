create table stakeholders(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    cpf varchar(11) not null unique,
    filiacao varchar(100) not null,
    senioridade varchar(100) not null,
    cargo varchar(100) not null,

    primary key(id)

);