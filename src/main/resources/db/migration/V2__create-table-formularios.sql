-- Version: 2
CREATE TABLE formularios (
    id bigint not null auto_increment,
    perguntas json not null,
    alternativas json not null,
    processos_alternativas json not null,

    primary key(id)

);

