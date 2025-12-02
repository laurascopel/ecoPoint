 DELETE FROM empresa_coletora;
 DELETE FROM item_residuo; 

INSERT INTO item_residuo (nome) VALUES ('Plástico');
INSERT INTO item_residuo (nome) VALUES ('Vidro');

INSERT INTO empresa_coletora (nome, endereco, cnpj, horario_funcionamento, data, telefone, descricao) 
VALUES (
    'EcoColeta Rápida Ltda',
    'Rua das Palmeiras, 123 - Centro',
    '00.111.222/0001-33',
    '08:00:00', 
    '2025-12-02', 
    '(51) 99876-5432',
    'Especializada na coleta de resíduos recicláveis para Porto Alegre.'
);