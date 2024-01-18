create table processos(

    id bigint not null auto_increment,
    nome varchar(100) not null unique,
    propriedades json not null,
    nome_etapa varchar(100) not null,
    artefato varchar(100) not null,
    instrumento varchar(100) not null,

    primary key(id)

);