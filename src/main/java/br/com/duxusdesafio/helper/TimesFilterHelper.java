package br.com.duxusdesafio.helper;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Time;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TimesFilterHelper {

    public List<Time> filtrarTimesPorPeriodo(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        return todosOsTimes.stream()
                .filter(time -> (dataInicial == null || !time.getData().isBefore(dataInicial)) &&
                        (dataFinal == null || !time.getData().isAfter(dataFinal)))
                .collect(Collectors.toList());
    }

    public <T> Map<T, Long> filtrarTimesEContarPorComposicao(
            LocalDate dataInicial,
            LocalDate dataFinal,
            List<Time> todosOsTimes,
            Function<ComposicaoTime, T> atributoExtractor
    ) {
        return todosOsTimes.stream()
                .filter(time -> (dataInicial == null || !time.getData().isBefore(dataInicial)) &&
                        (dataFinal == null || !time.getData().isAfter(dataFinal)))
                .flatMap(time -> time.getComposicao().stream())
                .map(atributoExtractor)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}