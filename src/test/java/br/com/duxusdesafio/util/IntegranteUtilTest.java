package br.com.duxusdesafio.util;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegranteUtilTest {

    private IntegranteUtil integranteUtil;

    @BeforeEach
    void setUp() {
        integranteUtil = new IntegranteUtil();
    }


    @Test
    void testExtrairAtributosDosIntegrantes() {
        // Arrange
        Integrante integrante1 = new Integrante(1L, "Franquia A", "João", "Dev");
        Integrante integrante2 = new Integrante(2L, "Franquia B", "Maria", "QA");

        ComposicaoTime comp1 = new ComposicaoTime();
        comp1.setIntegrante(integrante1);

        ComposicaoTime comp2 = new ComposicaoTime();
        comp2.setIntegrante(integrante2);

        Time time = new Time();
        time.setComposicao(List.of(comp1, comp2));

        List<Time> times = List.of(time);

        // Act
        List<String> nomes = integranteUtil.extrairAtributosDosIntegrantes(times, Integrante::getNome);

        // Assert
        assertEquals(2, nomes.size());
        assertEquals("João", nomes.get(0));
        assertEquals("Maria", nomes.get(1));
    }

    @Test
    void extrairAtributosDosIntegrantes_comListaVazia_deveRetornarListaVazia() {
        List<String> resultado = integranteUtil.extrairAtributosDosIntegrantes(
                List.of(),
                Integrante::getNome
        );
        assertTrue(resultado.isEmpty());
    }
}


