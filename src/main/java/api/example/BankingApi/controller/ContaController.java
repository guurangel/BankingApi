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
}
