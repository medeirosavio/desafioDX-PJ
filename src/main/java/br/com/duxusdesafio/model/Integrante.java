package br.com.duxusdesafio.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "integrante")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Integrante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String franquia;

    private String nome;

    private String funcao;
}

