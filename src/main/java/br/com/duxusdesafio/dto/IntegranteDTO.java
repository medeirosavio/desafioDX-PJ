package br.com.duxusdesafio.dto;

import br.com.duxusdesafio.model.Integrante;
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

    public IntegranteDTO(Integrante integrante) {
        this.id = integrante.getId();
        this.nome = integrante.getNome();
        this.funcao = integrante.getFuncao();
        this.franquia = integrante.getFranquia();
    }


}
