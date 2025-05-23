package br.com.duxusdesafio.util;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListaNulaValidator {
    public <T> void validar(List<T> lista) {
        if (lista == null) {
            throw new IllegalArgumentException("Lista não pode ser nula.");
        }
    }
}
