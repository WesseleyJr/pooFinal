CREATE TABLE Funcionario(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	cpf VARCHAR(11) NOT NULL UNIQUE,
	dataNasc DATE NOT NULL,
	salarioBruto NUMERIC(10,2) NOT NULL
);

CREATE TABLE Dependente(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	cpf VARCHAR(11) NOT NULL UNIQUE,
	dataNasc DATE NOT NULL,
	parentesco VARCHAR(50) NOT NULL,
	id_funcionario INT NOT NULL,
	FOREIGN KEY (id_funcionario) REFERENCES Funcionario(id) ON DELETE CASCADE
);

CREATE TABLE Folha_pagamento(
	id SERIAL PRIMARY KEY,
	inss NUMERIC(10,2),
	ir NUMERIC(10,2),
	fgts NUMERIC(10,2),
	planoSaude NUMERIC(10,2),
	valeRefeicao NUMERIC(10,2),
	salarioLiquido NUMERIC(10,2) NOT NULL,
	id_funcionario INT NOT NULL,
	data DATE not null default CURRENT_DATE,
	FOREIGN KEY (id_funcionario) REFERENCES Funcionario(id) ON DELETE CASCADE
);
