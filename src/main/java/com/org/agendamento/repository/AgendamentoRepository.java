package com.org.agendamento.repository;

import com.org.agendamento.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDateTime;
import java.util.List;


public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    Agendamento findByIdAndHorarioBetween(Long idServico, LocalDateTime dataHoraIncial,
                                                           LocalDateTime dataHoraFinal);

    List<Agendamento> findByHorarioBetween(LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal);


}
