# SolarSense API - README

## 📋 **Descrição do Projeto**

A **SolarSense API** é uma aplicação para gerenciamento de sistemas de energia solar, oferecendo funcionalidades como cadastro de usuários, painéis solares, sensores e leitura de dados para monitoramento e otimização do desempenho.

---

## 🚀 **Funcionalidades Principais**

1. **Gestão de Usuários:** Cadastro, autenticação e gerenciamento de perfis.
2. **Painéis Solares:** Cadastro e monitoramento de painéis.
3. **Sensores:** Configuração e leitura de dados.
4. **Leituras de Sensores:** Armazenamento e validação de dados em tempo real.
5. **Notificações:** Alertas configuráveis para usuários.
6. **Relatórios de Desempenho:** Análise de eficiência e produção de energia.
7. **Configurações do Sistema:** Personalização de parâmetros do sistema.

---

## ⚙️ **Estrutura do Projeto**

### **Entidades e Validações**

#### **1. Leitura Sensor**
- **Validações:**
    - A data de leitura não pode ser nula.
    - A leitura do sensor não pode ser nula.
    - O valor deve ser no mínimo 0.
    - A descrição deve ter no máximo 200 caracteres.

#### **2. Sensor**
- **Validações:**
    - O valor do painel solar não pode ser nulo.
    - O tipo do sensor não pode ser nulo.
    - A unidade de medida não pode ser nula.

#### **3. Tipo Sensor**
- **Validações:**
    - A descrição é obrigatória.
    - O tamanho máximo da descrição é 100 caracteres.

#### **4. Unidade de Medida**
- **Validações:**
    - O valor é obrigatório.
    - O tamanho máximo é 20 caracteres.

#### **5. Eficiência do Painel**
- **Validações:**
    - O valor do painel solar não pode ser nulo.
    - A eficiência não pode ser nula.
    - O valor mínimo de eficiência deve ser 0.0.
    - A data de cálculo não pode ser nula.

#### **6. Painel Solar**
- **Validações:**
    - O usuário não pode ser nulo.
    - O nome do painel é obrigatório.
    - O tamanho máximo do nome é 100 caracteres.
    - A capacidade de produção não pode ser nula.

#### **7. Relatório de Desempenho**
- **Validações:**
    - O valor do painel solar não pode ser nulo.
    - A eficiência média não pode ser nula.
    - As horas de produção não podem ser nulas.
    - O tempo mínimo de produção deve ser 0.0.
    - O tamanho máximo da análise de problemas é 250 caracteres.

#### **8. Notificação**
- **Validações:**
    - A mensagem é obrigatória.
    - A data de envio não pode ser nula.
    - O tipo de notificação não pode ser nulo.
    - O usuário não pode ser nulo.

#### **9. Tipo de Notificação**
- **Validações:**
    - O valor do tipo de usuário é obrigatório.
    - A descrição é obrigatória.
    - O tamanho máximo da descrição é 50 caracteres.

#### **10. Configuração do Sistema**
- **Validações:**
    - O usuário não pode ser nulo.
    - O limite de eficiência não pode ser nulo.
    - O valor mínimo de eficiência é 0.
    - A frequência de atualização não pode ser nula.
    - A frequência mínima de atualização é 1.

#### **11. Tipo de Usuário Cliente**
- **Validações:**
    - O valor do tipo de usuário é obrigatório.

---

## 🛠️ **Configurações do Ambiente**

### **Tecnologias Utilizadas**
- **Java 17+**
- **Spring Boot**
- **oracle** 
- **Spring Data JPA**
- **RabbitMQ** (para mensageria)
- **Spring AI** (para funcionalidades inteligentes)
- **Spring Boot Actuator** (monitoramento)

### **Pré-requisitos**
1. **Java 17** instalado.
2. Maven configurado para gerenciar dependências.
3. RabbitMQ para mensageria (configurado localmente ou em um servidor).

---

## 🔧 **Instruções para Execução**

### **Passos para Configurar o Projeto**
1. Clone o repositório:
   ```bash
   git clone https://github.com/ArthurMitsuoYamamoto/GS_JAVA.git
   ```
2. Acesse o diretório do projeto:
   ```bash
   cd solar-sense
   ```
3. Configure o `application.properties` com suas credenciais e detalhes de banco de dados, se necessário. Possibilidade de usar o mysql caso deseje.

4. Execute o projeto:
   ```bash
   mvn spring-boot:run
   ```

### **Acesso**
- A aplicação estará disponível em: `http://localhost:8080`


---

## 📊 **Monitoramento**
Utilize o **Spring Boot Actuator** para acessar endpoints de monitoramento como `/actuator/health`.

---

## 📢 **Contato**
Para dúvidas ou contribuições, entre em contato com a equipe de desenvolvimento:  
**Email:** support@solarsense.com  
**GitHub:** [SolarSense API](https://github.com/ArthurMitsuoYamamoto/GS_JAVA.git)

--- 

Este **README** é ajustável conforme as necessidades do projeto. Se precisar de mais seções ou alterações, basta informar! 😊