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
    ```json
    {
      "valor": 500.00
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
      "saldo": 1500.00,
      "ativa": "S",
      "tipo": "CORRENTE"
    }
    ```

### 3. **Realizar Saque**
- **Método**: `POST`
- **Endpoint**: `/contas/{id}/sacar`
- **Descrição**: Realiza um saque de uma conta.
- **Parâmetros**:
  - `id`: ID da conta de onde será feito o saque.
- **Corpo da requisição**:
    ```json
    {
      "valor": 300.00
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
      "saldo": 1200.00,
      "ativa": "S",
      "tipo": "CORRENTE"
    }
    ```

### 4. **Realizar Transferência (Pix)**
- **Método**: `POST`
- **Endpoint**: `/contas/pix`
- **Descrição**: Realiza uma transferência Pix entre duas contas.
- **Corpo da requisição**:
    ```json
    {
      "contaOrigemId": 1,
      "contaDestinoId": 2,
      "valor": 500.00
    }
    ```
- **Resposta**:
    ```json
    {
      "message": "Pix realizado com sucesso. Saldo da conta de origem atualizado."
    }
    ```

### 5. **Obter Detalhes de uma Conta**
- **Método**: `GET`
- **Endpoint**: `/contas/{id}`
- **Descrição**: Retorna os detalhes de uma conta bancária.
- **Parâmetros**:
  - `id`: ID da conta.
- **Resposta**:
    ```json
    {
      "id": 1,
      "numero": 123456,
      "agencia": "0001",
      "nome": "João Silva",
      "cpf": "12345678900",
      "dataAbertura": "2025-03-14T12:00:00",
      "saldo": 1200.00,
      "ativa": "S",
      "tipo": "CORRENTE"
    }
    ```
