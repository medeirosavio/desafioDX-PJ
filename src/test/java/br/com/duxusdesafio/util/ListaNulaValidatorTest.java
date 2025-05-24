package br.com.duxusdesafio.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListaNulaValidatorTest {

    private ListaNulaValidator listaNulaValidator;

    @BeforeEach
    void setUp() {
        listaNulaValidator = new ListaNulaValidator();
    }

    @Test
    void validar_deveLancarExcecao_quandoListaForNula() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> listaNulaValidator.validar(null)
        );
        assertEquals("Lista não pode ser nula.", exception.getMessage());
    }

    @Test
    void validar_naoDeveLancarExcecao_quandoListaNaoForNula() {
        assertDoesNotThrow(() -> listaNulaValidator.validar(List.of("item1", "item2")));
    }
}
