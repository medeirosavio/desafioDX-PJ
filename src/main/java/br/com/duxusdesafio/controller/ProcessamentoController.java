package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.dto.IntegranteDTO;
import br.com.duxusdesafio.dto.TimeDTO;
import br.com.duxusdesafio.service.ProcessamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/estatisticas")
@RequiredArgsConstructor
public class ProcessamentoController {

    private final ProcessamentoService processamentoService;

    @GetMapping("/time-da-data")
    public ResponseEntity<TimeDTO> getTimeDaData(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return ResponseEntity.ok(processamentoService.buscarTimeDaData(data));
    }

    @GetMapping("/integrante-mais-usado")
    public ResponseEntity<IntegranteDTO> getIntegranteMaisUsado(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal
    ) {
        return ResponseEntity.ok(processamentoService.buscarIntegranteMaisUsado(dataInicial, dataFinal));
    }

    @GetMapping("/funcao-mais-comum")
    public ResponseEntity<Map<String, String>> getFuncaoMaisComum(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal
    ) {
        return ResponseEntity.ok(processamentoService.buscarFuncaoMaisComum(dataInicial, dataFinal));
    }

    @GetMapping("/franquia-mais-famosa")
    public ResponseEntity<Map<String, String>> getFranquiaMaisFamosa(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal
    ) {
        return ResponseEntity.ok(processamentoService.buscarFranquiaMaisFamosa(dataInicial, dataFinal));
    }

    @GetMapping("/contagem-por-franquia")
    public ResponseEntity<Map<String, Long>> getContagemPorFranquia(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal
    ) {
        return ResponseEntity.ok(processamentoService.contarPorFranquia(dataInicial, dataFinal));
    }

    @GetMapping("/contagem-por-funcao")
    public ResponseEntity<Map<String, Long>> getContagemPorFuncao(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal
    ) {
        return ResponseEntity.ok(processamentoService.contarPorFuncao(dataInicial, dataFinal));
    }
}

