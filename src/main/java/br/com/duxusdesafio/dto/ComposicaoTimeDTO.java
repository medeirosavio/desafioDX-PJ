package br.com.duxusdesafio.dto;

import br.com.duxusdesafio.model.ComposicaoTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComposicaoTimeDTO {
    private Long integranteId;

    public ComposicaoTimeDTO(ComposicaoTime composicaoTime) {
        this.integranteId = composicaoTime.getIntegrante() != null
                ? composicaoTime.getIntegrante().getId()
                : null;
    }
}
