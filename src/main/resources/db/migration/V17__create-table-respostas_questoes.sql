
CREATE TABLE respostas_questoes (
    id bigint not null auto_increment,
    respostaDeFormulario_id bigint,
    questao_id bigint,
    alternativa_id bigint,
    processo_id bigint,

    primary key(id)

);

