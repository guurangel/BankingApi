package api.example.BankingApi.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Tipos {
    CORRENTE, POUPANCA, SALARIO;

    @JsonCreator
    public static Tipos fromString(String valor) {
        // Verifica se o valor enviado no corpo do post não é nulo, se for nulo cai na exception.
        if (valor == null) {
            throw new IllegalArgumentException("O tipo de conta não pode ser nulo.");
        }
        // Converte as entradas para maiúsculo e verifica se o contéudo é igual ao definido no escopo do enum Tipos. Caso não seja idêntico ao definido no enum, retornado uma exception.
        try {
            return Tipos.valueOf(valor.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                "Tipo de conta inválido! Use um dos seguintes valores: CORRENTE, POUPANCA ou SALARIO."
            );
        }
    }
}
