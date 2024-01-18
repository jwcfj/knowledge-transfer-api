-- Removendo colunas "perguntas", "alternativas" e "processos_alternativas"
ALTER TABLE processos
DROP COLUMN propriedades,
DROP COLUMN nome_etapa,
DROP COLUMN artefato,
DROP COLUMN instrumento;