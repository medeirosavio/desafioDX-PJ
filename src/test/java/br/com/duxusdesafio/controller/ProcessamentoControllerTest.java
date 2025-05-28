package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.dto.IntegranteDTO;
import br.com.duxusdesafio.dto.TimeDTO;
import br.com.duxusdesafio.service.ProcessamentoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class ProcessamentoControllerTest {

    @InjectMocks
    private ProcessamentoController controller;

    @Mock
    private ProcessamentoService service;

    private final LocalDate data = LocalDate.of(2023, 1, 1);

    @Test
    void testBuscarTimeDaData() {
        TimeDTO timeDTO = new TimeDTO();
        Mockito.when(service.buscarTimeDaData(data)).thenReturn(timeDTO);

        ResponseEntity<TimeDTO> response = controller.getTimeDaData(data);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(timeDTO, response.getBody());
    }

    @Test
    void testBuscarIntegranteMaisUsado() {
        IntegranteDTO integranteDTO = new IntegranteDTO();
        Mockito.when(service.buscarIntegranteMaisUsado(null, null)).thenReturn(integranteDTO);

        ResponseEntity<IntegranteDTO> response = controller.getIntegranteMaisUsado(null, null);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(integranteDTO, response.getBody());
    }

    @Test
    void testBuscarFuncaoMaisComum() {
        Map<String, String> result = Map.of("funcao", "Líder");
        Mockito.when(service.buscarFuncaoMaisComum(null, null)).thenReturn(result);

        ResponseEntity<Map<String, String>> response = controller.getFuncaoMaisComum(null, null);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(result, response.getBody());
    }

    @Test
    void testBuscarFranquiaMaisFamosa() {
        Map<String, String> result = Map.of("franquia", "Avengers");
        Mockito.when(service.buscarFranquiaMaisFamosa(null, null)).thenReturn(result);

        ResponseEntity<Map<String, String>> response = controller.getFranquiaMaisFamosa(null, null);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(result, response.getBody());
    }

    @Test
    void testContarPorFranquia() {
        Map<String, Long> result = Map.of("Avengers", 3L);
        Mockito.when(service.contarPorFranquia(null, null)).thenReturn(result);

        ResponseEntity<Map<String, Long>> response = controller.getContagemPorFranquia(null, null);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(result, response.getBody());
    }

    @Test
    void testContarPorFuncao() {
        Map<String, Long> result = Map.of("Líder", 5L);
        Mockito.when(service.contarPorFuncao(null, null)).thenReturn(result);

        ResponseEntity<Map<String, Long>> response = controller.getContagemPorFuncao(null, null);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(result, response.getBody());
    }
}

