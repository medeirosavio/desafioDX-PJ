package br.com.duxusdesafio.dto;

import br.com.duxusdesafio.model.Time;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeDTO {
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    private List<ComposicaoTimeDTO> composicao;

    public TimeDTO(Time time) {
        this.id = time.getId();
        this.data = time.getData();
        // Converte a lista de ComposicaoTime para ComposicaoTimeDTO, assumindo que esse DTO exista
        if (time.getComposicao() != null) {
            this.composicao = time.getComposicao().stream()
                    .map(ComposicaoTimeDTO::new)
                    .collect(Collectors.toList());
        } else {
            this.composicao = new ArrayList<>();
        }
    }

}


