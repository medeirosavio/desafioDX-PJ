package br.com.duxusdesafio.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DataValidatorTest {

    private DataValidator dataValidator;

    @BeforeEach
    void setUp() {
        dataValidator = new DataValidator();
    }

    @Test
    void validar_dataNula_naoDeveLancarExcecao() {
        assertDoesNotThrow(() -> dataValidator.validar(null));
    }

    @Test
    void validar_dataNoPassado_naoDeveLancarExcecao() {
        LocalDate dataPassada = LocalDate.now().minusDays(1);
        assertDoesNotThrow(() -> dataValidator.validar(dataPassada));
    }

    @Test
    void validar_dataAtual_naoDeveLancarExcecao() {
        LocalDate dataAtual = LocalDate.now();
        assertDoesNotThrow(() -> dataValidator.validar(dataAtual));
    }

    @Test
    void validar_dataNoFuturo_deveLancarIllegalArgumentException() {
        LocalDate dataFutura = LocalDate.now().plusDays(1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            dataValidator.validar(dataFutura);
        });
        assertEquals("A data não pode ser no futuro.", exception.getMessage());
    }
}
