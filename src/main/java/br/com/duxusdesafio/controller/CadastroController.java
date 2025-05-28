package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.dto.IntegranteDTO;
import br.com.duxusdesafio.dto.TimeDTO;
import br.com.duxusdesafio.service.CadastroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cadastro")
@RequiredArgsConstructor
public class CadastroController {

    private final CadastroService cadastroService;

    @PostMapping("/integrantes")
    public ResponseEntity<IntegranteDTO> cadastrarIntegrante(@RequestBody IntegranteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastroService.salvarIntegrante(dto));
    }

    @PostMapping("/times")
    public ResponseEntity<TimeDTO> cadastrarTime(@RequestBody TimeDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastroService.salvarTime(dto));
    }
}


