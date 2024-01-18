-- Versão: 3
-- Removendo colunas "perguntas", "alternativas" e "processos_alternativas"
ALTER TABLE formularios
DROP COLUMN perguntas,
DROP COLUMN alternativas,
DROP COLUMN processos_alternativas;
