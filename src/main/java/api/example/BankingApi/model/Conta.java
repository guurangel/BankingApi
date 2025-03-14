package api.example.BankingApi.model;

import java.time.LocalDateTime;
import java.util.Random;

public class Conta {
    private Long id;
    private Long numero; // Número da conta
    private String agencia; // Agência da conta
    private String nome; // Nome do titular
    private char cpf; // CPF do titular
    private LocalDateTime dataAbertura; // Data de abertura da conta
    private double saldoInicial; // Saldo inicial
    private char ativa; // Indica se a conta está ativa ('s' ou 'n')
    private Tipos tipo;

    public Conta(Long id, Long numero, String agencia, String nome, char cpf, LocalDateTime dataAbertura, double saldoInicial,
            char ativa, Tipos tipo) {
        this.id = Math.abs(new Random().nextLong());
        this.numero = numero;
        this.agencia = agencia;
        this.nome = nome;
        this.cpf = cpf;
        this.dataAbertura = LocalDateTime.now();
        this.saldoInicial = saldoInicial;
        this.ativa = 's';
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public Long getNumero() {
        return numero;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getNome() {
        return nome;
    }

    public char getCpf() {
        return cpf;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public char getAtiva() {
        return ativa;
    }

    public Tipos getTipo() {
        return tipo;
    }
}
