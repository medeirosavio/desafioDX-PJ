package br.com.duxusdesafio.util;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class IntegranteUtil {

    public <R> List<R> extrairAtributosDosIntegrantes(List<Time> times, Function<Integrante, R> atributoExtractor) {
        return times.stream()
                .flatMap(time -> time.getComposicao().stream())
                .map(ComposicaoTime::getIntegrante)
                .filter(Objects::nonNull)
                .map(atributoExtractor)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}