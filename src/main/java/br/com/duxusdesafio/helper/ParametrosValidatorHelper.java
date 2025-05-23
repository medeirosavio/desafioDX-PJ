package br.com.duxusdesafio.helper;

import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.util.DataValidator;
import br.com.duxusdesafio.util.ListaNulaValidator;
import br.com.duxusdesafio.util.ListaVaziaValidator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ParametrosValidatorHelper {

    private DataValidator dataValidator;
    private ListaNulaValidator listaNulaValidator;
    private ListaVaziaValidator listaVaziaValidator;

    public void validarParametros(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        dataValidator.validar(dataInicial);
        dataValidator.validar(dataFinal);
        listaNulaValidator.validar(todosOsTimes);
        listaVaziaValidator.validar(todosOsTimes);
    }

    public void validarParametros(LocalDate data,List<Time> todosOsTimes) {
        dataValidator.validar(data);
        listaNulaValidator.validar(todosOsTimes);
        listaVaziaValidator.validar(todosOsTimes);
    }
}
