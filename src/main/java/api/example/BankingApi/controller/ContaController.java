package api.example.BankingApi.controller;

import api.example.BankingApi.model.Conta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ContaController {

    private final List<Conta> contas = new ArrayList<>(); // Lista em memória

    // Endpoint inicial "/"
    @GetMapping
    public String home() {
        return "Projeto: Banking API - Integrantes: Gustavo Rangel, Luis Felippe Morais das Neves\r\n" + //
                        "";
    }

    // Criar conta (POST /contas)
    @PostMapping("/contas")
    public ResponseEntity<Conta> criarConta(@RequestBody Conta conta) {
        conta.setId(); // Gera ID automaticamente
        conta.setDataAbertura(); // Define a data de abertura
        contas.add(conta);
        return ResponseEntity.ok(conta);
    }

    // Listar todas as contas (GET /contas)
    @GetMapping("/contas")
    public ResponseEntity<List<Conta>> listarContas() {
        return ResponseEntity.ok(contas);
    }

    // Buscar conta por ID (GET /contas/{id})
    @GetMapping("/contas/{id}")
    public ResponseEntity<Conta> buscarContaPorId(@PathVariable Long id) {
        Optional<Conta> contaEncontrada = contas.stream()
                .filter(conta -> conta.getId().equals(id))
                .findFirst();

        return contaEncontrada.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Buscar conta por CPF (GET /contas/cpf/{cpf})
    @GetMapping("/contas/cpf/{cpf}")
    public ResponseEntity<Conta> buscarContaPorCpf(@PathVariable String cpf) {
        Optional<Conta> contaEncontrada = contas.stream()
                .filter(conta -> conta.getCpf().equals(cpf))
                .findFirst();

        return contaEncontrada.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
