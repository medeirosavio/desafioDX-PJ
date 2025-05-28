package br.com.duxusdesafio.service;

import br.com.duxusdesafio.dto.ComposicaoTimeDTO;
import br.com.duxusdesafio.dto.IntegranteDTO;
import br.com.duxusdesafio.dto.TimeDTO;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CadastroServiceTest {

    private IntegranteRepository integranteRepository;
    private TimeRepository timeRepository;
    private CadastroService cadastroService;

    @BeforeEach
    void setUp() {
        integranteRepository = mock(IntegranteRepository.class);
        timeRepository = mock(TimeRepository.class);
        cadastroService = new CadastroService(integranteRepository, timeRepository);
    }

    @Test
    void deveSalvarIntegranteComSucesso() {
        IntegranteDTO dto = new IntegranteDTO(null, "João", "Desenvolvedor", "Franquia A");

        Integrante salvo = new Integrante();
        salvo.setId(1L);
        salvo.setNome("João");
        salvo.setFuncao("Desenvolvedor");
        salvo.setFranquia("Franquia A");

        when(integranteRepository.save(any(Integrante.class))).thenReturn(salvo);

        IntegranteDTO result = cadastroService.salvarIntegrante(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("João", result.getNome());
        assertEquals("Desenvolvedor", result.getFuncao());
        assertEquals("Franquia A", result.getFranquia());
    }

    @Test
    void deveSalvarTimeComSucesso() {
        Long integranteId = 1L;

        ComposicaoTimeDTO composicaoDTO = new ComposicaoTimeDTO(integranteId);
        TimeDTO dto = new TimeDTO(null, LocalDate.of(2024, 1, 1), List.of(composicaoDTO));

        Integrante integrante = new Integrante();
        integrante.setId(integranteId);
        integrante.setNome("Maria");

        when(integranteRepository.findById(integranteId)).thenReturn(Optional.of(integrante));

        ArgumentCaptor<Time> timeCaptor = ArgumentCaptor.forClass(Time.class);
        when(timeRepository.save(any(Time.class))).thenAnswer(invocation -> {
            Time t = invocation.getArgument(0);
            t.setId(99L); // simulando ID atribuído
            return t;
        });

        TimeDTO result = cadastroService.salvarTime(dto);

        assertNotNull(result);
        assertEquals(99L, result.getId());
        assertEquals(LocalDate.of(2024, 1, 1), result.getData());
        assertEquals(1, result.getComposicao().size());
        assertEquals(integranteId, result.getComposicao().get(0).getIntegranteId());
    }

    @Test
    void deveLancarExcecaoSeIntegranteNaoEncontrado() {
        Long integranteId = 999L;
        ComposicaoTimeDTO composicaoDTO = new ComposicaoTimeDTO(integranteId);
        TimeDTO dto = new TimeDTO(null, LocalDate.of(2024, 1, 1), List.of(composicaoDTO));

        when(integranteRepository.findById(integranteId)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            cadastroService.salvarTime(dto);
        });

        assertEquals("Integrante não encontrado: 999", ex.getMessage());
    }
}

