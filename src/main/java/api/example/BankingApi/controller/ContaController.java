package api.example.BankingApi.controller;

import api.example.BankingApi.model.Conta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class ContaController {

    private final List<Conta> contas = new ArrayList<>(); // Lista em memória

    // Endpoint inicial "/"
    @GetMapping
    public String home() {
        return "Projeto: Banking API - Integrantes: Gustavo Rangel, Luis Felippe Morais das Neves";
    }

    // Criar conta (POST /contas)
    @PostMapping("/contas")
    public ResponseEntity<Conta> criarConta(@RequestBody Conta conta) {
        conta.setId(); // Gera ID automaticamente
        conta.setDataAbertura(); // Define a data de abertura
        contas.add(conta);
        return ResponseEntity.ok(conta);
    }

    // Listar contas (GET /contas)
    @GetMapping("/contas")
    public ResponseEntity<List<Conta>> listarContas() {
        return ResponseEntity.ok(contas);
    }
}
