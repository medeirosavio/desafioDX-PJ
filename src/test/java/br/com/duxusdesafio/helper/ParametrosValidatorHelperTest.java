package br.com.duxusdesafio.helper;

import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.util.DataValidator;
import br.com.duxusdesafio.util.ListaNulaValidator;
import br.com.duxusdesafio.util.ListaVaziaValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.verify;

public class ParametrosValidatorHelperTest {

    @InjectMocks
    private ParametrosValidatorHelper helper;

    @Mock
    private DataValidator dataValidator;

    @Mock
    private ListaNulaValidator listaNulaValidator;

    @Mock
    private ListaVaziaValidator listaVaziaValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validarParametros_comDataInicialDataFinal_listaDeTimes_chamaTodosValidadores() {
        LocalDate dataInicial = LocalDate.of(2025, 5, 1);
        LocalDate dataFinal = LocalDate.of(2025, 5, 31);
        List<Time> todosOsTimes = List.of(new Time());

        helper.validarParametros(dataInicial, dataFinal, todosOsTimes);

        // Verifica se os validadores foram chamados com os parâmetros corretos
        verify(dataValidator).validar(dataInicial);
        verify(dataValidator).validar(dataFinal);
        verify(listaNulaValidator).validar(todosOsTimes);
        verify(listaVaziaValidator).validar(todosOsTimes);
    }

    @Test
    void validarParametros_comData_listaDeTimes_chamaValidadoresCorretamente() {
        LocalDate data = LocalDate.of(2025, 5, 1);
        List<Time> todosOsTimes = List.of(new Time());

        helper.validarParametros(data, todosOsTimes);

        verify(dataValidator).validar(data);
        verify(listaNulaValidator).validar(todosOsTimes);
        verify(listaVaziaValidator).validar(todosOsTimes);
    }
}


