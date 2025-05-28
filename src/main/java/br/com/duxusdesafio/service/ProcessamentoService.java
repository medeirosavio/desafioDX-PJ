package br.com.duxusdesafio.service;

import br.com.duxusdesafio.dto.IntegranteDTO;
import br.com.duxusdesafio.dto.TimeDTO;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProcessamentoService {

    private final TimeRepository timeRepository;
    private final IntegranteRepository integranteRepository;

    public TimeDTO buscarTimeDaData(LocalDate data) {
        Time time = timeRepository.findByData(data)
                .orElseThrow(() -> new EntityNotFoundException("Time não encontrado para a data: " + data));
        return new TimeDTO(time); // Supondo que o DTO tenha construtor que aceita a entidade
    }

    public IntegranteDTO buscarIntegranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal) {
        List<Integrante> integrantes;

        if (dataInicial != null && dataFinal != null) {
            // Usar o método corrigido que busca integrantes filtrando pela data do Time
            integrantes = integranteRepository.findAllByTimeDataBetween(dataInicial, dataFinal);
        } else {
            integrantes = integranteRepository.findAll();
        }

        Integrante maisUsado = integrantes.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new RuntimeException("Nenhum integrante encontrado"))
                .getKey();

        return new IntegranteDTO(maisUsado);
    }


    public Map<String, String> buscarFuncaoMaisComum(LocalDate dataInicial, LocalDate dataFinal) {
        List<Integrante> integrantes = buscarIntegrantesPorPeriodo(dataInicial, dataFinal);

        String funcaoMaisComum = integrantes.stream()
                .collect(Collectors.groupingBy(Integrante::getFuncao, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Desconhecida");

        return Map.of("funcao", funcaoMaisComum);
    }

    public Map<String, String> buscarFranquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal) {
        List<Integrante> integrantes = buscarIntegrantesPorPeriodo(dataInicial, dataFinal);

        String franquiaMaisFamosa = integrantes.stream()
                .collect(Collectors.groupingBy(Integrante::getFranquia, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Desconhecida");

        return Map.of("franquia", franquiaMaisFamosa);
    }

    public Map<String, Long> contarPorFranquia(LocalDate dataInicial, LocalDate dataFinal) {
        List<Integrante> integrantes = buscarIntegrantesPorPeriodo(dataInicial, dataFinal);

        return integrantes.stream()
                .collect(Collectors.groupingBy(Integrante::getFranquia, Collectors.counting()));
    }

    public Map<String, Long> contarPorFuncao(LocalDate dataInicial, LocalDate dataFinal) {
        List<Integrante> integrantes = buscarIntegrantesPorPeriodo(dataInicial, dataFinal);

        return integrantes.stream()
                .collect(Collectors.groupingBy(Integrante::getFuncao, Collectors.counting()));
    }

    private List<Integrante> buscarIntegrantesPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        if (dataInicial != null && dataFinal != null) {
            return integranteRepository.findAllByTimeDataBetween(dataInicial, dataFinal);
        }
        return integranteRepository.findAll();
    }

}

