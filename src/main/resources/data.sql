 DELETE FROM empresa_coletora;
 DELETE FROM empresa_geradora; 
 DELETE FROM item_residuo; 

INSERT INTO item_residuo (nome) VALUES ('Plástico');
INSERT INTO item_residuo (nome) VALUES ('Vidro');
INSERT INTO item_residuo (nome) VALUES ('Pilha');
INSERT INTO item_residuo (nome) VALUES ('Bateria');
INSERT INTO item_residuo (nome) VALUES ('Eletrônico');
INSERT INTO item_residuo (nome) VALUES ('Lâmpada');
INSERT INTO item_residuo (nome) VALUES ('Madeira');
INSERT INTO item_residuo (nome) VALUES ('Papel');
INSERT INTO item_residuo (nome) VALUES ('Papelão');
INSERT INTO item_residuo (nome) VALUES ('Metal');
INSERT INTO item_residuo (nome) VALUES ('óleo\Lubrificante');
INSERT INTO item_residuo (nome) VALUES ('Pneu');
INSERT INTO item_residuo (nome) VALUES ('Hospitalar');
INSERT INTO item_residuo (nome) VALUES ('Tinta');
INSERT INTO item_residuo (nome) VALUES ('Concreto');
INSERT INTO item_residuo (nome) VALUES ('Tijolo');
INSERT INTO item_residuo (nome) VALUES ('Filtro de óleo');
INSERT INTO item_residuo (nome) VALUES ('Telha');


INSERT INTO empresa_geradora (nome, cpnj, endereco, telefone) 
VALUES (
    'Laika Construções',
    '00.131.222/0021-43',
    'Rua das Guria, 323 - Centro',
    '(51) 94002-8922',
);

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
INSERT INTO empresa_coletora (nome, endereco, cnpj, horario_funcionamento, data, telefone, descricao) 
VALUES (
    'Coleta e Cia Ltd-Me',
    'Rua dos Guri, 173 - Centro',
    '00.171.222/0701-33',
    '09:00:00', 
    '2025-12-02', 
    '(51) 99845-5452',
    'Descarte consciente.'
);