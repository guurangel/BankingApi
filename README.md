# API Bancária - Sistema de Contas e Transações

Este é um projeto de uma API bancária que permite realizar operações em contas bancárias, incluindo **depósitos**, **saques** e **transferências via Pix**. A API foi construída utilizando o framework **Spring Boot** e expõe endpoints para interagir com contas e realizar transações.

## Tecnologias Utilizadas

- **Java 11**
- **Spring Boot**
- **Maven**
- **RESTful API**

## Estrutura do Projeto

O projeto segue uma estrutura simples, com as seguintes principais camadas:

1. **Model**: Contém as classes que representam os dados da aplicação.
2. **Controller**: Onde os endpoints da API são definidos.

## Regras de Negócio

A API bancária segue as seguintes regras de negócio para o cadastro e manipulação das contas bancárias:

### Atributos de uma Conta Bancária

Cada conta bancária possui os seguintes atributos:

- **Número**: Identificador único da conta.
- **Agência**: Identificador da agência bancária onde a conta está vinculada.
- **Nome do Titular**: Nome completo do titular da conta.
- **CPF do Titular**: CPF do titular da conta.
- **Data de Abertura**: Data e hora em que a conta foi aberta.
- **Saldo Inicial**: Valor inicial presente na conta quando ela é aberta.
- **Ativa**: Indicador de status da conta (S para ativa, N para inativa).
- **Tipo**: Tipo da conta bancária. Pode ser um dos seguintes valores:
  - **CORRENTE**
  - **POUPANÇA**
  - **SALÁRIO**

### Validações ao Criar uma Conta

Durante o cadastro de uma nova conta, as seguintes validações são aplicadas para garantir que os dados enviados sejam válidos:

1. **Nome do Titular**:
   - **Obrigatório**: O nome do titular deve ser informado. Se não for fornecido, o sistema retornará um erro de validação.
   
2. **CPF do Titular**:
   - **Obrigatório**: O CPF do titular deve ser informado. Se o CPF estiver ausente ou inválido, um erro será retornado.
   
3. **Data de Abertura**:
   - **Não pode ser no futuro**: A data de abertura da conta não pode ser uma data futura. Se o sistema receber uma data inválida, ele retornará um erro.

4. **Saldo Inicial**:
   - **Não pode ser negativo**: O saldo inicial da conta não pode ser menor que zero. Se o valor for negativo, o sistema retornará um erro de validação.

5. **Tipo de Conta**:
   - **Tipo válido**: O tipo da conta deve ser um dos seguintes: **CORRENTE**, **POUPANÇA**, ou **SALÁRIO**. Se um tipo inválido for informado, o sistema retornará um erro.

### Tratamento de Erros

Caso o usuário envie dados inválidos ao criar uma conta, a API retornará um **erro 400 (Bad Request)** com a mensagem de erro específica no corpo da resposta. A mensagem indicará qual campo possui o erro e o motivo do mesmo.

## Endpoints Disponíveis

### 1. **Criar uma Conta**
- **Método**: `POST`
- **Endpoint**: `/contas`
- **Descrição**: Cria uma nova conta bancária.
- **Corpo da requisição**:
    ```json
    {
      "numero": 123456,
      "agencia": "0001",
      "nome": "João Silva",
      "cpf": "12345678900",
      "saldo": 1000.00,
      "tipo": "CORRENTE"
    }
    ```
- **Resposta**:
    ```json
    {
      "id": 1,
      "numero": 123456,
      "agencia": "0001",
      "nome": "João Silva",
      "cpf": "12345678900",
      "dataAbertura": "2025-03-14T12:00:00",
      "saldo": 1000.00,
      "ativa": "S",
      "tipo": "CORRENTE"
    }
    ```

### 2. **Realizar Depósito**
- **Método**: `POST`
- **Endpoint**: `/contas/{id}/depositar`
- **Descrição**: Realiza um depósito em uma conta.
- **Parâmetros**:
  - `id`: ID da conta a ser depositado.
- **Corpo da requisição**:
... (86 linhas)
