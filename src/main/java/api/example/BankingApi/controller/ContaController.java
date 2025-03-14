package api.example.BankingApi.controller;

import api.example.BankingApi.model.Conta;
import api.example.BankingApi.model.Transacao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ContaController {

    private final List<Conta> contas = new ArrayList<>(); // Cria uma lista para armazenar os dados na memória

    // Cria o Endpoint inicial "/", retornando o nome do projeto e os integrantes da equipe.
    @GetMapping
    public String home() {
        return "Projeto: Banking API - Integrantes: Gustavo Rangel, Luis Felippe Morais das Neves\r\n" + //
                        "";
    }

    //Endpoint para criar as contas e adicionar na memória local, passando antes por uma validação no método conta.validar();
    @PostMapping("/contas")
    public ResponseEntity<?> criarConta(@RequestBody Conta conta) {
        try {
            conta.setId();
            conta.setDataAbertura();
            conta.validar();
            contas.add(conta);
            return ResponseEntity.ok(conta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"erro\": \"" + e.getMessage() + "\"}");
        }
    }
    
    // Endpoint para listar todas as contas (GET /contas)
    @GetMapping("/contas")
    public ResponseEntity<List<Conta>> listarContas() {
        return ResponseEntity.ok(contas);
    }

    // Endpoint para buscar conta por ID (GET /contas/{id})
    @GetMapping("/contas/{id}")
    public ResponseEntity<Conta> buscarContaPorId(@PathVariable Long id) {
        Optional<Conta> contaEncontrada = contas.stream()
                .filter(conta -> conta.getId().equals(id))
                .findFirst();

        return contaEncontrada.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para buscar conta por CPF (GET /contas/cpf/{cpf})
    @GetMapping("/contas/cpf/{cpf}")
    public ResponseEntity<Conta> buscarContaPorCpf(@PathVariable String cpf) {
        Optional<Conta> contaEncontrada = contas.stream()
                .filter(conta -> conta.getCpf().equals(cpf))
                .findFirst();

        return contaEncontrada.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para realizar depósitos em uma conta (POST /contas/{id}/depositar)
    @PostMapping("/contas/{id}/depositar")
    public ResponseEntity<Conta> realizarDeposito(@PathVariable Long id, @RequestBody Transacao deposito) {
        double valorDeposito = deposito.getValor(); // Obtém o valor do depósito

        if (valorDeposito <= 0) {
            return ResponseEntity.badRequest().body(null); // Verifica se o valor do depósito é válido
        }

        Conta conta = contas.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (conta == null) {
            return ResponseEntity.status(404).body(null);
        }

        // Atualiza o saldo da conta
        double novoSaldo = conta.getSaldo() + valorDeposito;
        conta.setSaldo(novoSaldo);

        return ResponseEntity.ok(conta); // Retorna os dados da conta atualizada
    }

    // Endpoint para realizar saque em uma conta (POST /contas/{id}/saque)
    @PostMapping("/contas/{id}/saque")
    public ResponseEntity<Conta> realizarSaque(@PathVariable Long id, @RequestBody Transacao transacao) {
        double valorSaque = transacao.getValor();

        // Verifica se o valor do saque é válido (positivo)
        if (valorSaque <= 0) {
            return ResponseEntity.badRequest().body(null);  // Retorna erro se o valor do saque for inválido
        }

        // Encontra a conta pelo ID
        Conta conta = contas.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (conta == null) {
            return ResponseEntity.status(404).body(null);  // Retorna erro se a conta não for encontrada
        }

        // Verifica se o saldo é suficiente para o saque
        if (conta.getSaldo() < valorSaque) {
            return ResponseEntity.status(400).body(null);
        }

        double novoSaldo = conta.getSaldo() - valorSaque;
        conta.setSaldo(novoSaldo);

        return ResponseEntity.ok(conta);
    }


    // Endpoint para encerrar uma conta
    @PutMapping("contas/{id}/encerrar")
    public ResponseEntity<String> encerrarConta(@PathVariable Long id) {
        // Busca a conta pelo ID
        Conta conta = contas.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (conta == null) {
            return ResponseEntity.status(404).body("Conta não encontrada");
        }
        // Caso a conta seja encontrada marca a conta como inativa
        conta.setAtiva('N');
        return ResponseEntity.ok("Conta encerrada com sucesso");
    }    
}
