-- Criação da tabela de tipos de notificação
CREATE TABLE T_SOSE_TipoNotificacao (
                                        Id INT PRIMARY KEY,
                                        Descricao VARCHAR(50) NOT NULL
);

-- Inserção de valores iniciais
INSERT INTO T_SOSE_TipoNotificacao (Id, Descricao) VALUES (1, 'Informativa');
INSERT INTO T_SOSE_TipoNotificacao (Id, Descricao) VALUES (2, 'Alerta');

-- Criação da tabela de usuários
CREATE TABLE T_SOSE_Usuario (
                                Id INT PRIMARY KEY,
                                Email VARCHAR(100) NOT NULL,
                                Senha VARCHAR(255) NOT NULL,
                                DataCadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                Status CHAR(1) NOT NULL
);

-- Inserção de valores iniciais
INSERT INTO T_SOSE_Usuario (Id, Email, Senha, Status) VALUES (1, 'admin@sose.com', '123456', 'A');
INSERT INTO T_SOSE_Usuario (Id, Email, Senha, Status) VALUES (2, 'user@sose.com', 'senha123', 'I');

-- Criação da tabela de tipo de usuário
CREATE TABLE T_SOSE_TipoUsuario (
                                    Id INT PRIMARY KEY,
                                    Descricao VARCHAR(50) NOT NULL
);

-- Inserção de valores iniciais
INSERT INTO T_SOSE_TipoUsuario (Id, Descricao) VALUES (1, 'Administrador');
INSERT INTO T_SOSE_TipoUsuario (Id, Descricao) VALUES (2, 'Usuário Comum');

-- Criação da tabela de relação entre usuário e tipo de usuário
CREATE TABLE T_SOSE_Usuario_TipoUsuario (
                                            IdUsuario INT,
                                            IdTipoUsuario INT,
                                            PRIMARY KEY (IdUsuario, IdTipoUsuario),
                                            FOREIGN KEY (IdUsuario) REFERENCES T_SOSE_Usuario(Id),
                                            FOREIGN KEY (IdTipoUsuario) REFERENCES T_SOSE_TipoUsuario(Id)
);

-- Criação da tabela de painel solar
CREATE TABLE T_SOSE_PainelSolar (
                                    Id INT PRIMARY KEY,
                                    IdUsuario INT,
                                    Nome VARCHAR(100) NOT NULL,
                                    Localizacao VARCHAR(100),
                                    CapacidadeProducao DECIMAL(10,2),
                                    FOREIGN KEY (IdUsuario) REFERENCES T_SOSE_Usuario(Id)
);

-- Criação da tabela de eficiência do painel
CREATE TABLE T_SOSE_EficienciaPainel (
                                         Id INT PRIMARY KEY,
                                         Eficiencia DECIMAL(5,2) NOT NULL,
                                         DataCalculo TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                         IdPainelSolar INT,
                                         FOREIGN KEY (IdPainelSolar) REFERENCES T_SOSE_PainelSolar(Id)
);

-- Criação da tabela de sensores
CREATE TABLE T_SOSE_Sensor (
                               Id INT PRIMARY KEY,
                               IdTipoSensor INT,
                               IdPainelSolar INT,
                               UnidadeMedida VARCHAR(20),
                               FOREIGN KEY (IdPainelSolar) REFERENCES T_SOSE_PainelSolar(Id)
);

-- Criação da tabela de leituras de sensores
CREATE TABLE T_SOSE_LeituraSensor (
                                      Id INT PRIMARY KEY,
                                      DataLeitura TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                      ValorLeitura DECIMAL(10,2),
                                      IdSensor INT,
                                      FOREIGN KEY (IdSensor) REFERENCES T_SOSE_Sensor(Id)
);

-- Criação da tabela de auditoria
CREATE TABLE T_SOSE_Auditoria (
                                  Id INT PRIMARY KEY,
                                  NomeColuna VARCHAR(100),
                                  DadoAntigo VARCHAR(200),
                                  DadoNovo VARCHAR(200),
                                  Usuario VARCHAR(100),
                                  DataAlteracao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
