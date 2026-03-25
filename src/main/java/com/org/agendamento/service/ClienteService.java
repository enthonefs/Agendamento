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
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ServicoRepository servicoRepository;

    public List<Cliente> buscarTodos(){
        return clienteRepository.findAll();
    }

    public Cliente criarCliente(Long idServico, Cliente cliente){
        Servico servicoExistente = servicoRepository.findById(idServico).orElseThrow(
                () -> new RuntimeException("Serviço indisponível"));
        cliente.setServicos(servicoExistente);
        return clienteRepository.save(cliente);

    }

    public void atualizarCliente(Long id, Cliente cliente){
        Cliente clienteExistente = clienteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Id de Cliente não encontrado"));

        Cliente clienteAtualizado = Cliente.builder()
                .nome(cliente.getNome() != null ? cliente.getNome() : clienteExistente.getNome())
                .servicos(cliente.getServicos() != null ? cliente.getServicos() : clienteExistente.getServicos())
                .id(clienteExistente.getId())
                .build();

        clienteRepository.saveAndFlush(clienteAtualizado);
    }

    public void deletarCliente(Long id){
        clienteRepository.deleteById(id);
    }
}
