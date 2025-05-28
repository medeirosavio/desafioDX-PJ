package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.dto.ComposicaoTimeDTO;
import br.com.duxusdesafio.dto.IntegranteDTO;
import br.com.duxusdesafio.dto.TimeDTO;
import br.com.duxusdesafio.service.CadastroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CadastroController.class)
class CadastroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CadastroService cadastroService;

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void deveCadastrarIntegranteComSucesso() throws Exception {
        // Arrange
        IntegranteDTO integranteDTO = new IntegranteDTO(null, "Bangalore", "Atirador", "Apex Legends");
        IntegranteDTO savedDTO = new IntegranteDTO(1L, "Bangalore", "Atirador", "Apex Legends");

        Mockito.when(cadastroService.salvarIntegrante(Mockito.any(IntegranteDTO.class)))
                .thenReturn(savedDTO);

        // Act & Assert
        mockMvc.perform(post("/api/cadastro/integrantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(integranteDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Bangalore"))
                .andExpect(jsonPath("$.funcao").value("Atirador"))
                .andExpect(jsonPath("$.franquia").value("Apex Legends"));
    }

    @Test
    void deveCadastrarTimeComSucesso() throws Exception {
        // Arrange
        TimeDTO requestDTO = new TimeDTO();
        requestDTO.setData(LocalDate.of(2024, 1, 1));
        requestDTO.setComposicao(List.of(
                new ComposicaoTimeDTO(1L),
                new ComposicaoTimeDTO(2L)
        ));

        TimeDTO savedDTO = new TimeDTO(10L, requestDTO.getData(), requestDTO.getComposicao());

        Mockito.when(cadastroService.salvarTime(Mockito.any(TimeDTO.class)))
                .thenReturn(savedDTO);

        // Act & Assert
        mockMvc.perform(post("/api/cadastro/times")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.data").value("2024-01-01"))
                .andExpect(jsonPath("$.composicao.length()").value(2));
    }
}

