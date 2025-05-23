package br.com.duxusdesafio.util;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListaVaziaValidator {
    public <T> void validar(List<T> lista) {
        if (lista.isEmpty()) {
            throw new IllegalArgumentException("Lista não pode estar vazia.");
        }
    }
}
