package br.com.duxusdesafio.service;

import br.com.duxusdesafio.dto.ComposicaoTimeDTO;
import br.com.duxusdesafio.dto.IntegranteDTO;
import br.com.duxusdesafio.dto.TimeDTO;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CadastroService {

    private final IntegranteRepository integranteRepository;
    private final TimeRepository timeRepository;

    public IntegranteDTO salvarIntegrante(IntegranteDTO dto) {
        Integrante integrante = new Integrante();
        integrante.setNome(dto.getNome());
        integrante.setFuncao(dto.getFuncao());
        integrante.setFranquia(dto.getFranquia());
        return toDTO(integranteRepository.save(integrante));
    }

    public TimeDTO salvarTime(TimeDTO dto) {
        Time time = new Time();
        time.setData(dto.getData());

        List<ComposicaoTime> composicoes = new ArrayList<>();

        for (ComposicaoTimeDTO compDTO : dto.getComposicao()) {
            Integrante integrante = integranteRepository.findById(compDTO.getIntegranteId())
                    .orElseThrow(() -> new IllegalArgumentException("Integrante não encontrado: " + compDTO.getIntegranteId()));

            ComposicaoTime composicao = new ComposicaoTime();
            composicao.setIntegrante(integrante);
            composicao.setTime(time); // ligação bidirecional

            composicoes.add(composicao);
        }

        time.setComposicao(composicoes);
        time = timeRepository.save(time);

        return toDTO(time);
    }

    private IntegranteDTO toDTO(Integrante integrante) {
        return new IntegranteDTO(integrante.getId(), integrante.getNome(), integrante.getFuncao(), integrante.getFranquia());
    }

    private TimeDTO toDTO(Time time) {
        List<ComposicaoTimeDTO> composicaoDTOs = time.getComposicao().stream()
                .map(c -> new ComposicaoTimeDTO(c.getIntegrante().getId()))
                .collect(Collectors.toList());

        return new TimeDTO(time.getId(), time.getData(), composicaoDTOs);
    }
}

