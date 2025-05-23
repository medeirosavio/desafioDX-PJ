package br.com.duxusdesafio.util;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class FrequenciaUtil {

    public  <T, K> Map<K, Long> contarFrequencia(Collection<T> items, Function<T, K> agrupador) {
        return items.stream()
                .map(agrupador)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public  <K> K encontrarMaisFrequente(Map<K, Long> frequencias) {
        return frequencias.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
