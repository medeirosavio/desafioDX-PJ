package br.com.duxusdesafio.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListaVaziaValidatorTest {

    private ListaVaziaValidator listaVaziaValidator;

    @BeforeEach
    void setUp() {
        listaVaziaValidator = new ListaVaziaValidator();
    }

    @Test
    void validar_deveLancarExcecao_quandoListaEstiverVazia() {
        List<String> listaVazia = List.of();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> listaVaziaValidator.validar(listaVazia)
        );

        assertEquals("Lista não pode estar vazia.", exception.getMessage());
    }

    @Test
    void validar_naoDeveLancarExcecao_quandoListaNaoEstiverVazia() {
        List<String> lista = List.of("elemento");

        assertDoesNotThrow(() -> listaVaziaValidator.validar(lista));
    }
}

