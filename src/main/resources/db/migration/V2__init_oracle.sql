-- Criação da tabela de tipos de notificação
CREATE TABLE T_SOSE_TipoNotificacao (
                                        Id NUMBER(11) PRIMARY KEY,
                                        Descricao VARCHAR2(50) NOT NULL
);

-- Inserção de valores iniciais
INSERT INTO T_SOSE_TipoNotificacao (Id, Descricao) VALUES (1, 'Informativa');
INSERT INTO T_SOSE_TipoNotificacao (Id, Descricao) VALUES (2, 'Alerta');

-- Criação da tabela de usuários
CREATE TABLE T_SOSE_Usuario (
                                Id NUMBER(11) PRIMARY KEY,
                                Email VARCHAR2(100) NOT NULL,
                                Senha VARCHAR2(255) NOT NULL,
                                DataCadastro DATE DEFAULT SYSDATE,
                                Status CHAR(1) NOT NULL
);

-- Inserção de valores iniciais
INSERT INTO T_SOSE_Usuario (Id, Email, Senha, Status) VALUES (1, 'admin@sose.com', '123456', 'A');
INSERT INTO T_SOSE_Usuario (Id, Email, Senha, Status) VALUES (2, 'user@sose.com', 'senha123', 'I');

-- Criação da tabela de tipo de usuário
CREATE TABLE T_SOSE_TipoUsuario (
                                    Id NUMBER(11) PRIMARY KEY,
                                    Descricao VARCHAR2(50) NOT NULL
);

-- Inserção de valores iniciais
INSERT INTO T_SOSE_TipoUsuario (Id, Descricao) VALUES (1, 'Administrador');
INSERT INTO T_SOSE_TipoUsuario (Id, Descricao) VALUES (2, 'Usuário Comum');

-- Criação da tabela de relação entre usuário e tipo de usuário
CREATE TABLE T_SOSE_Usuario_TipoUsuario (
                                            IdUsuario NUMBER(11),
                                            IdTipoUsuario NUMBER(11),
                                            PRIMARY KEY (IdUsuario, IdTipoUsuario),
                                            FOREIGN KEY (IdUsuario) REFERENCES T_SOSE_Usuario(Id),
                                            FOREIGN KEY (IdTipoUsuario) REFERENCES T_SOSE_TipoUsuario(Id)
);

-- Criação da tabela de painel solar
CREATE TABLE T_SOSE_PainelSolar (
                                    Id NUMBER(11) PRIMARY KEY,
                                    IdUsuario NUMBER(11),
                                    Nome VARCHAR2(100) NOT NULL,
                                    Localizacao VARCHAR2(100),
                                    CapacidadeProducao NUMBER(10,2),
                                    FOREIGN KEY (IdUsuario) REFERENCES T_SOSE_Usuario(Id)
);

-- Criação da tabela de eficiência do painel
CREATE TABLE T_SOSE_EficienciaPainel (
                                         Id NUMBER(11) PRIMARY KEY,
                                         Eficiencia NUMBER(5,2) NOT NULL,
                                         DataCalculo DATE DEFAULT SYSDATE,
                                         IdPainelSolar NUMBER(11),
                                         FOREIGN KEY (IdPainelSolar) REFERENCES T_SOSE_PainelSolar(Id)
);

-- Criação da tabela de sensores
CREATE TABLE T_SOSE_Sensor (
                               Id NUMBER(11) PRIMARY KEY,
                               IdTipoSensor NUMBER(11),
                               IdPainelSolar NUMBER(11),
                               UnidadeMedida VARCHAR2(20),
                               FOREIGN KEY (IdPainelSolar) REFERENCES T_SOSE_PainelSolar(Id)
);

-- Criação da tabela de leituras de sensores
CREATE TABLE T_SOSE_LeituraSensor (
                                      Id NUMBER(11) PRIMARY KEY,
                                      DataLeitura DATE DEFAULT SYSDATE,
                                      ValorLeitura NUMBER(10,2),
                                      IdSensor NUMBER(11),
                                      FOREIGN KEY (IdSensor) REFERENCES T_SOSE_Sensor(Id)
);

-- Criação da tabela de auditoria
CREATE TABLE T_SOSE_Auditoria (
                                  Id NUMBER(11) PRIMARY KEY,
                                  NomeColuna VARCHAR2(100),
                                  DadoAntigo VARCHAR2(200),
                                  DadoNovo VARCHAR2(200),
                                  Usuario VARCHAR2(100),
                                  DataAlteracao DATE DEFAULT SYSDATE
);
