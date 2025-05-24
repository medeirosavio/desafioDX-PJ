package br.com.duxusdesafio.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataValidator {

    public void validar(LocalDate data) {
        if (data != null) {
            if (data.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("A data não pode ser no futuro.");
            }
        }
    }
}
