package br.com.duxusdesafio.service;

import br.com.duxusdesafio.helper.ParametrosValidatorHelper;
import br.com.duxusdesafio.helper.TimesFilterHelper;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApiService {

    private final FrequenciaUtil frequenciaUtil;
    private final IntegranteUtil integranteUtil;
    private final ParametrosValidatorHelper parametrosValidatorHelper;
    private final TimesFilterHelper timesFilterHelper;


    public Time timeDaData(LocalDate data, List<Time> todosOsTimes){
        parametrosValidatorHelper.validarParametros(data, todosOsTimes);
        return todosOsTimes.stream()
                .filter(time -> data.equals(time.getData()))
                .findFirst()
                .orElse(null);
    }

    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        parametrosValidatorHelper.validarParametros(dataInicial, dataFinal, todosOsTimes);
        Map<Integrante, Long> frequencias = timesFilterHelper.filtrarTimesEContarPorComposicao(
                dataInicial,
                dataFinal,
                todosOsTimes,
                ComposicaoTime::getIntegrante
        );
        return frequenciaUtil.encontrarMaisFrequente(frequencias);
    }

    public List<String> integrantesDoTimeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        parametrosValidatorHelper.validarParametros(dataInicial, dataFinal, todosOsTimes);

        Map<Time, Long> frequencias = timesFilterHelper.filtrarTimesEContarPorComposicao(
                dataInicial,
                dataFinal,
                todosOsTimes,
                ComposicaoTime::getTime
        );

        Time timeMaisComum = frequenciaUtil.encontrarMaisFrequente(frequencias);

        if (timeMaisComum == null) {
            throw new IllegalArgumentException("Nenhum time mais comum foi encontrado no período informado.");
        }

        return timeMaisComum.getComposicao().stream()
                .map(composicao -> composicao.getIntegrante().getNome())
                .collect(Collectors.toList());
    }

    public String funcaoMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        parametrosValidatorHelper.validarParametros(dataInicial, dataFinal, todosOsTimes);
        List<Time> timesFiltrados = timesFilterHelper.filtrarTimesPorPeriodo(dataInicial, dataFinal, todosOsTimes);
        List<String> funcoes = integranteUtil.extrairAtributosDosIntegrantes(timesFiltrados, Integrante::getFuncao);
        Map<String, Long> frequenciaFuncoes = frequenciaUtil.contarFrequencia(funcoes, Function.identity());
        return frequenciaUtil.encontrarMaisFrequente(frequenciaFuncoes);
    }

    public String franquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        parametrosValidatorHelper.validarParametros(dataInicial, dataFinal, todosOsTimes);
        List<Time> timesFiltrados = timesFilterHelper.filtrarTimesPorPeriodo(dataInicial, dataFinal, todosOsTimes);
        List<String> franquias = integranteUtil.extrairAtributosDosIntegrantes(timesFiltrados, Integrante::getFranquia);
        Map<String, Long> frequenciaFranq = frequenciaUtil.contarFrequencia(franquias, Function.identity());
        return frequenciaUtil.encontrarMaisFrequente(frequenciaFranq);
    }

    public Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        parametrosValidatorHelper.validarParametros(dataInicial, dataFinal, todosOsTimes);
        List<Time> timesFiltrados = timesFilterHelper.filtrarTimesPorPeriodo(dataInicial, dataFinal, todosOsTimes);
        List<String> franquias = integranteUtil.extrairAtributosDosIntegrantes(timesFiltrados, Integrante::getFranquia);
        return frequenciaUtil.contarFrequencia(franquias, Function.identity());
    }

    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        parametrosValidatorHelper.validarParametros(dataInicial, dataFinal, todosOsTimes);
        List<Time> timesFiltrados = timesFilterHelper.filtrarTimesPorPeriodo(dataInicial, dataFinal, todosOsTimes);
        List<String> funcoes = integranteUtil.extrairAtributosDosIntegrantes(timesFiltrados, Integrante::getFuncao);
        return frequenciaUtil.contarFrequencia(funcoes, Function.identity());
    }

}
