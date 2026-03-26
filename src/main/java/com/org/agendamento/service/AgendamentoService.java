package com.org.agendamento.service;

import com.org.agendamento.exceptions.ResourceNotFoundException;
import com.org.agendamento.model.Agendamento;
import com.org.agendamento.model.Cliente;
import com.org.agendamento.model.Servico;
import com.org.agendamento.repository.AgendamentoRepository;
import com.org.agendamento.repository.ClienteRepository;
import com.org.agendamento.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final ServicoRepository servicoRepository;

    public Agendamento agendar(Long idCliente, Long idServico, Agendamento agendamento){
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(
                () -> new ResourceNotFoundException("Cliente não encontrado."));
        agendamento.setCliente(cliente);

        Servico servico = servicoRepository.findById(idServico).orElseThrow(
                () -> new ResourceNotFoundException("Serviço indisponível."));
        agendamento.setServico(servico);

        LocalDateTime horaAgendamento = agendamento.getHorario();
        LocalDateTime horaFinal = agendamento.getHorario().plusMinutes(1);

        //FIXME implementar lógica restante do método



        return agendamentoRepository.save(agendamento);

    }

    public List<Agendamento> listarAgendamentos(){
        return agendamentoRepository.findAll();
    }

    public Agendamento atualizarAgendamento(Long id, Agendamento agendamento){
        Agendamento agendamentoExistente = agendamentoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Agendamento não encontrado."));

        Agendamento agendamentoAtualizado = Agendamento.builder()
                .cliente(agendamentoExistente.getCliente())
                .servico(agendamentoExistente.getServico())
                .horario(agendamento.getHorario() != null ? agendamento.getHorario() : agendamentoExistente.getHorario())
                .status(agendamento.getStatus() != null ? agendamento.getStatus() : agendamentoExistente.getStatus())
                .build();

        return agendamentoRepository.saveAndFlush(agendamentoAtualizado);
    }

    public void deletarAgendamento(Long id){
        agendamentoRepository.deleteById(id);
    }
}
