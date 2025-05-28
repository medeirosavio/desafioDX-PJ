package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @PostMapping("/time")
    public ResponseEntity<Time> getTimeDaData(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestBody List<Time> todosOsTimes
    ) {
        return ResponseEntity.ok(apiService.timeDaData(data, todosOsTimes));
    }

    @PostMapping("/integrante-mais-usado")
    public ResponseEntity<Integrante> getIntegranteMaisUsado(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
            @RequestBody List<Time> todosOsTimes
    ) {
        return ResponseEntity.ok(apiService.integranteMaisUsado(dataInicial, dataFinal, todosOsTimes));
    }

    @PostMapping("/integrantes-do-time-mais-comum")
    public ResponseEntity<List<String>> getIntegrantesDoTimeMaisComum(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
            @RequestBody List<Time> todosOsTimes
    ) {
        return ResponseEntity.ok(apiService.integrantesDoTimeMaisComum(dataInicial, dataFinal, todosOsTimes));
    }

    @PostMapping("/funcao-mais-comum")
    public ResponseEntity<String> getFuncaoMaisComum(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
            @RequestBody List<Time> todosOsTimes
    ) {
        return ResponseEntity.ok(apiService.funcaoMaisComum(dataInicial, dataFinal, todosOsTimes));
    }

    @PostMapping("/franquia-mais-famosa")
    public ResponseEntity<String> getFranquiaMaisFamosa(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
            @RequestBody List<Time> todosOsTimes
    ) {
        return ResponseEntity.ok(apiService.franquiaMaisFamosa(dataInicial, dataFinal, todosOsTimes));
    }

    @PostMapping("/contagem-por-franquia")
    public ResponseEntity<Map<String, Long>> getContagemPorFranquia(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
            @RequestBody List<Time> todosOsTimes
    ) {
        return ResponseEntity.ok(apiService.contagemPorFranquia(dataInicial, dataFinal, todosOsTimes));
    }

    @PostMapping("/contagem-por-funcao")
    public ResponseEntity<Map<String, Long>> getContagemPorFuncao(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
            @RequestBody List<Time> todosOsTimes
    ) {
        return ResponseEntity.ok(apiService.contagemPorFuncao(dataInicial, dataFinal, todosOsTimes));
    }


}

