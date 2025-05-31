package br.com.duxusdesafio.model;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_integrante")
    private Integrante integrante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_time")
    private Time time;

}