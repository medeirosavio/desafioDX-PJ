package br.com.duxusdesafio.service;

import br.com.duxusdesafio.helper.ParametrosValidatorHelper;
import br.com.duxusdesafio.helper.TimesFilterHelper;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.util.FrequenciaUtil;
import br.com.duxusdesafio.util.IntegranteUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApiServiceTest {

    @Mock
    private FrequenciaUtil frequenciaUtil;

    @Mock
    private IntegranteUtil integranteUtil;

    @Mock
    private ParametrosValidatorHelper parametrosValidatorHelper;

    @Mock
    private TimesFilterHelper timesFilterHelper;

    @InjectMocks
    private ApiService apiService;

    private List<Time> times;
    private LocalDate data1;
    private LocalDate data2;

    @BeforeEach
    void setUp() {
        data1 = LocalDate.of(2024, 1, 1);
        data2 = LocalDate.of(2024, 12, 31);

        Integrante integrante = new Integrante();
        integrante.setNome("João");
        integrante.setFuncao("Desenvolvedor");
        integrante.setFranquia("Franquia A");

        ComposicaoTime composicao = new ComposicaoTime();
        composicao.setIntegrante(integrante);

        Time time = new Time();
        time.setData(data1);
        time.setComposicao(List.of(composicao));

        times = List.of(time);
    }

    @Test
    void testTimeDaData() {
        Time result = apiService.timeDaData(data1, times);
        assertNotNull(result);
        assertEquals(data1, result.getData());
    }

    @Test
    void testIntegranteMaisUsado() {
        // Arrange
        Integrante esperado = times.get(0).getComposicao().get(0).getIntegrante();

        // Simula o retorno do método filtrarTimesEContarPorComposicao com qualquer entrada
        when(timesFilterHelper.filtrarTimesEContarPorComposicao(
                Mockito.any(LocalDate.class),
                Mockito.any(LocalDate.class),
                anyList(),
                Mockito.<Function<ComposicaoTime, ?>>any()
        )).thenAnswer(invocation -> {
            Map<Integrante, Long> resultadoSimulado = new HashMap<>();
            resultadoSimulado.put(esperado, 3L);
            return resultadoSimulado;
        });

        // Simula o retorno do método encontrarMaisFrequente
        when(frequenciaUtil.encontrarMaisFrequente(Mockito.anyMap()))
                .thenReturn(esperado);

        // Act
        Integrante resultado = apiService.integranteMaisUsado(data1, data2, times);

        // Assert
        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
    }


    @Test
    void testIntegrantesDoTimeMaisComum() {
        // Arrange
        Time esperado = times.get(0);

        // Simula o retorno do método filtrarTimesEContarPorComposicao
        when(timesFilterHelper.filtrarTimesEContarPorComposicao(
                Mockito.any(LocalDate.class),
                Mockito.any(LocalDate.class),
                anyList(),
                Mockito.<Function<ComposicaoTime, ?>>any()
        )).thenAnswer(invocation -> {
            Map<Time, Long> resultadoSimulado = new HashMap<>();
            resultadoSimulado.put(esperado, 2L);
            return resultadoSimulado;
        });

        // Simula o retorno do método encontrarMaisFrequente
        when(frequenciaUtil.encontrarMaisFrequente(Mockito.anyMap()))
                .thenReturn(esperado);

        // Act
        List<String> nomes = apiService.integrantesDoTimeMaisComum(data1, data2, times);

        // Assert
        assertNotNull(nomes);
        assertEquals(List.of("João"), nomes);
    }


    @Test
    void testFuncaoMaisComum() {
        // Arrange
        String funcaoEsperada = "Desenvolvedor";

        // Simula o retorno do método filtrarTimesEContarPorComposicao
        lenient().when(timesFilterHelper.filtrarTimesEContarPorComposicao(
                Mockito.any(LocalDate.class),
                Mockito.any(LocalDate.class),
                anyList(),
                Mockito.<Function<ComposicaoTime, ?>>any()
        )).thenAnswer(invocation -> {
            Map<String, Long> resultadoSimulado = new HashMap<>();
            resultadoSimulado.put(funcaoEsperada, 3L);
            return resultadoSimulado;
        });

        // Simula o retorno do método encontrarMaisFrequente
        when(frequenciaUtil.encontrarMaisFrequente(Mockito.anyMap()))
                .thenReturn(funcaoEsperada);

        // Act
        String resultado = apiService.funcaoMaisComum(data1, data2, times);

        // Assert
        assertNotNull(resultado);
        assertEquals("Desenvolvedor", resultado);
    }




    @Test
    void testFranquiaMaisFamosa() {
        // Dados de entrada
        List<Time> times = List.of(mock(Time.class), mock(Time.class));
        LocalDate data1 = LocalDate.of(2020, 1, 1);
        LocalDate data2 = LocalDate.of(2020, 12, 31);
        List<String> franquias = List.of("Franquia A", "Franquia A");

        // Mock dos retornos
        when(timesFilterHelper.filtrarTimesPorPeriodo(data1, data2, times))
                .thenReturn(times);

        // Usa ArgumentMatchers para lidar com o uso de lambdas
        when(integranteUtil.extrairAtributosDosIntegrantes(
                anyList(), ArgumentMatchers.<Function<Integrante, String>>any()))
                .thenReturn(franquias);

        when(frequenciaUtil.contarFrequencia(franquias, Function.identity()))
                .thenReturn(Map.of("Franquia A", 2L));

        when(frequenciaUtil.encontrarMaisFrequente(Map.of("Franquia A", 2L)))
                .thenReturn("Franquia A");

        // Execução do método a ser testado
        String resultado = apiService.franquiaMaisFamosa(data1, data2, times);

        // Verificação
        assertEquals("Franquia A", resultado);
    }


    @Test
    void testContagemPorFranquia() {
        List<String> franquias = List.of("Franquia A", "Franquia B", "Franquia A");

        when(timesFilterHelper.filtrarTimesPorPeriodo(data1, data2, times)).thenReturn(times);

        when(integranteUtil.<String>extrairAtributosDosIntegrantes(
                eq(times),
                ArgumentMatchers.<Function<Integrante, String>>any()
        )).thenReturn(franquias);

        when(frequenciaUtil.contarFrequencia(franquias, Function.identity()))
                .thenReturn(Map.of("Franquia A", 2L, "Franquia B", 1L));

        Map<String, Long> resultado = apiService.contagemPorFranquia(data1, data2, times);

        assertEquals(2L, resultado.get("Franquia A"));
        assertEquals(1L, resultado.get("Franquia B"));
    }

    @Test
    void testContagemPorFuncao() {
        List<String> funcoes = List.of("QA", "QA", "Dev");

        when(timesFilterHelper.filtrarTimesPorPeriodo(data1, data2, times)).thenReturn(times);
        when(integranteUtil.extrairAtributosDosIntegrantes(eq(times), Mockito.<Function<Integrante, String>>any()))
                .thenReturn(funcoes);
        when(frequenciaUtil.contarFrequencia(eq(funcoes), Mockito.<Function<String, String>>any()))
                .thenReturn(Map.of("QA", 2L, "Dev", 1L));

        Map<String, Long> resultado = apiService.contagemPorFuncao(data1, data2, times);

        assertEquals(2L, resultado.get("QA"));
        assertEquals(1L, resultado.get("Dev"));
    }

}
