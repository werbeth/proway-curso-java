/* Cria um banco de dados chamado 'grana_certa'*/
CREATE DATABASE grana_certa;
/* Define que iremos trabalhar utilizando o bd criado*/
USE grana_certa;
/* Criar a tabela de contas */
CREATE TABLE contas(
	id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    tipo TINYINT NOT NULL,
    saldo DOUBLE NOT NULL,
    descricao text
);
/* Criando um registro na tabela de contas */ 
INSERT INTO contas (nome, tipo, saldo, descricao) VALUES 
	("Viacredi", 1, 1000.00, "Conta dos joguinhos");
/* Consultar os registros da tabela de contas */
SELECT id, nome, tipo, saldo, descricao FROM contas;

CREATE TABLE cliente (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cnpj VARCHAR(20) NOT NULL
);

-- Inserção de alguns clientes
INSERT INTO cliente (nome, cnpj) VALUES
    ('Empresa Alpha', '12.345.678/0001-90'),
    ('Beta Soluções LTDA', '98.765.432/0001-10'),
    ('Gamma Corp', '11.222.333/0001-55');

-- Verificar dados inseridos
SELECT * FROM cliente;

CREATE TABLE contas_receber_pagar(
	id INT PRIMARY KEY AUTO_INCREMENT,
	id_cliente INT NOT NULL,
    id_contas INT NOT NULL,
    
    nome VARCHAR(100) NOT NULL,
    tipo TINYINT NOT NULL,
    valor DOUBLE NOT NULL,
    data_prevista DATE NOT NULL,
	data_realizada DATE,
	status TINYINT NOT NULL,
    registros_ativo BIT DEFAULT(1) NOT NULL,
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY(id_cliente) REFERENCES cliente(id),
    FOREIGN KEY(id_contas) REFERENCES contas(id)
);

-- Inserindo uma conta a receber (tipo = 0)
INSERT INTO contas_receber_pagar (
    id_cliente, id_contas, nome, tipo, valor, data_prevista, status
) VALUES (
    1, -- Empresa Alpha
    1, -- Viacredi
    'Recebimento de serviços prestados',
    0, -- Conta a receber
    1500.00,
    '2025-08-25',
    0 -- Status pendente (por exemplo)
);

-- Inserindo uma conta a pagar (tipo = 1)
INSERT INTO contas_receber_pagar (
    id_cliente, id_contaS, nome, tipo, valor, data_prevista, status
) VALUES (
    2, -- Beta Soluções LTDA
    3, -- Caixa Econômica
    'Pagamento de fornecedor de TI',
    1, -- Conta a pagar
    850.00,
    '2025-08-20',
    0 -- Status pendente (por exemplo)
);

SELECT 
    crp.id,
    c.nome AS cliente_nome,
    ct.nome AS conta_nome,
    crp.nome AS transacao_nome,
    crp.tipo,
    crp.valor,
    crp.data_prevista,
    crp.data_realizada,
    crp.status,
    crp.registro_ativo,
    crp.data_criacao
FROM 
    contas_receber_pagar crp
JOIN 
    clientes c ON crp.id_cliente = c.id
JOIN 
    contas ct ON crp.id_conta = ct.id;