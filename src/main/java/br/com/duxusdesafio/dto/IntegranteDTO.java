package br.com.duxusdesafio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntegranteDTO {
    private Long id;
    private String nome;
    private String funcao;
    private String franquia;
}
