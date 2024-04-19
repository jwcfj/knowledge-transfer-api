create table processos(

    id bigint not null auto_increment,
    nome varchar(100) not null unique,
    descricao varchar(4000) not null,
    primary key(id)
);
