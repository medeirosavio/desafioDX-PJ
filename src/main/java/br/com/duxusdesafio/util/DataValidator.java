package br.com.duxusdesafio.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataValidator {
    public void validar(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException("Data não pode ser nula.");
        }
    }
}
