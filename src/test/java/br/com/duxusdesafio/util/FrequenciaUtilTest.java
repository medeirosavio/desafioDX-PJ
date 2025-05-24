package br.com.duxusdesafio.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class FrequenciaUtilTest {

    private FrequenciaUtil frequenciaUtil;

    @BeforeEach
    void setUp() {
        frequenciaUtil = new FrequenciaUtil();
    }

    @Test
    void contarFrequencia_deveContarFrequenciaCorretamente() {
        List<String> items = Arrays.asList("QA", "QA", "Dev", null, "Dev", "QA");
        Map<String, Long> frequencia = frequenciaUtil.contarFrequencia(items, Function.identity());

        assertEquals(2L, frequencia.get("Dev"));
        assertEquals(3L, frequencia.get("QA"));
        assertFalse(frequencia.containsKey(null), "Não deve conter chave nula");
    }

    @Test
    void contarFrequencia_comAgrupadorCustomizado_deveFuncionar() {
        List<String> nomes = Arrays.asList("Ana", "André", "Bruno", "Beatriz", "Carlos");
        // Agrupa pela primeira letra do nome
        Map<Character, Long> frequencia = frequenciaUtil.contarFrequencia(nomes, nome -> nome.charAt(0));

        assertEquals(2L, frequencia.get('A'));
        assertEquals(2L, frequencia.get('B'));
        assertEquals(1L, frequencia.get('C'));
    }

    @Test
    void encontrarMaisFrequente_deveRetornarChaveComMaiorFrequencia() {
        Map<String, Long> frequencias = Map.of("QA", 5L, "Dev", 3L, "Ops", 2L);
        String maisFrequente = frequenciaUtil.encontrarMaisFrequente(frequencias);

        assertEquals("QA", maisFrequente);
    }

    @Test
    void encontrarMaisFrequente_comMapaVazio_deveRetornarNull() {
        Map<String, Long> frequenciasVazio = Collections.emptyMap();
        assertNull(frequenciaUtil.encontrarMaisFrequente(frequenciasVazio));
    }

    @Test
    void encontrarMaisFrequente_comEmpate_deveRetornarUmDosMaisFrequentes() {
        Map<String, Long> frequencias = Map.of("QA", 3L, "Dev", 3L);
        String maisFrequente = frequenciaUtil.encontrarMaisFrequente(frequencias);

        assertTrue(maisFrequente.equals("QA") || maisFrequente.equals("Dev"));
    }
}

