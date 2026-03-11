package com.org.agendamento.controller;

import com.org.agendamento.model.Servico;
import com.org.agendamento.service.ServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servico")
@RequiredArgsConstructor
public class ServicoController {

    private final ServicoService service;

    @GetMapping
    public ResponseEntity<List<Servico>> buscarTodos(){
        return ResponseEntity.ok().body(service.buscarTodos());
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> agendar(@PathVariable Long id, @RequestBody Servico servico){
        service.criarServico(id, servico);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody Servico servico){
        service.atualizarAgendamento(id, servico);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAgendamento(@PathVariable Long id){
        service.apagarAgendamento(id);
        return ResponseEntity.ok().build();
    }
}
