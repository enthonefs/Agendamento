package com.org.agendamento.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "servico_id")
    private Servico servico;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime horario;

    @Column(name = "status")
    private StatusAgendamento status;
}
