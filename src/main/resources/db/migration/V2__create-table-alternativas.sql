CREATE TABLE alternativas (
    id bigint not null auto_increment,
    pergunta varchar(100) not null,
    processo_id bigint,
    primary key(id)

);