package com.org.agendamento.service;

import com.org.agendamento.model.Cliente;
import com.org.agendamento.model.Servico;
import com.org.agendamento.repository.ClienteRepository;
import com.org.agendamento.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final ClienteRepository clienteRepository;

    public List<Servico> buscarTodos(){
        return servicoRepository.findAll();
    }

    public void criarServico(Long id, Servico servico){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Cliente não encontrado."));

        cliente.setServico(servico);
        servicoRepository.save(servico);
    }


    public void atualizarAgendamento(Long id, Servico servico){
        Servico servicoAtual = servicoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Agendamento não encontrado."));

        Servico servicoAtualizado = Servico.builder()
                .servico(servico.getServico() != null ? servico.getServico() : servicoAtual.getServico())
                .valor(servico.getValor() != 0 ? servico.getValor() : servicoAtual.getValor())
                .dateTime(servico.getDateTime() != null ? servico.getDateTime() : servicoAtual.getDateTime())
                .id(servicoAtual.getId())
                .build();

        servicoRepository.saveAndFlush(servicoAtualizado);

    }

    public void apagarAgendamento(Long id){
        servicoRepository.deleteById(id);
    }
}
