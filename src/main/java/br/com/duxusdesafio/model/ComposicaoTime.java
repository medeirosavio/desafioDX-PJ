package br.com.duxusdesafio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "composicao_time")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComposicaoTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_time", nullable = false)
    private Time time;

    @ManyToOne
    @JoinColumn(name = "id_integrante", nullable = false)
    private Integrante integrante;
}