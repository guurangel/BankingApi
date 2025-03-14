package api.example.BankingApi.model;

import java.time.LocalDateTime;
import java.util.Random;

public class Conta {
    private Long id;
    private Long numero;
    private String agencia;
    private String nome;
    private String cpf;
    private LocalDateTime dataAbertura;
    private double saldo;
    private char ativa;
    private Tipos tipo;

    public Conta(Long numero, String agencia, String nome, String cpf, double saldo, Tipos tipo) {
        this.numero = numero;
        this.agencia = agencia;
        this.nome = nome;
        this.cpf = cpf;
        this.saldo = saldo;
        this.ativa = 'S';
        this.tipo = tipo;
    }

    public void setId() {
        this.id = Math.abs(new Random().nextLong());
    }

    public void setDataAbertura() {
        this.dataAbertura = LocalDateTime.now();
    }

    public void setAtiva(char ativa) {
        this.ativa = ativa;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    public void validar(){
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do titular é obrigatório.");
        }
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF do titular é obrigatório.");
        }
        if (dataAbertura != null && dataAbertura.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data de abertura não pode ser no futuro.");
        }
        if (saldo < 0) {
            throw new IllegalArgumentException("O saldo inicial não pode ser negativo.");
        }
    }

    public Long getId() {
        return id;
    }

 
    public LocalDateTime getDataAbertura() {
        return dataAbertura;
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

    public String getCpf() {
        return cpf;
    }

    public double getSaldo() {
        return saldo;
    }

    public char getAtiva() {
        return ativa;
    }

    public Tipos getTipo() {
        return tipo;
    }
}
