package br.com.duxusdesafio.service;

import br.com.duxusdesafio.dto.IntegranteDTO;
import br.com.duxusdesafio.dto.TimeDTO;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProcessamentoServiceTest {

    @InjectMocks
    private ProcessamentoService service;

    @Mock
    private TimeRepository timeRepository;

    @Mock
    private IntegranteRepository integranteRepository;

    private final LocalDate data = LocalDate.of(2023, 1, 1);

    @Test
    void testBuscarTimeDaData_Sucesso() {
        Time time = new Time();
        Mockito.when(timeRepository.findByData(data)).thenReturn(Optional.of(time));

        // Você precisa de um construtor TimeDTO(Time) para esse teste funcionar.
        TimeDTO dto = service.buscarTimeDaData(data);

        Assertions.assertNotNull(dto);
        Mockito.verify(timeRepository).findByData(data);
    }

    @Test
    void testBuscarTimeDaData_NaoEncontrado() {
        Mockito.when(timeRepository.findByData(data)).thenReturn(Optional.empty());

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            service.buscarTimeDaData(data);
        });

        Assertions.assertTrue(exception.getMessage().contains("Time não encontrado"));
    }

    @Test
    void testBuscarIntegranteMaisUsado_SemFiltro() {
        Integrante i1 = new Integrante(); // configure os campos se necessário
        Integrante i2 = new Integrante();

        List<Integrante> integrantes = List.of(i1, i1, i2); // i1 mais usado
        Mockito.when(integranteRepository.findAll()).thenReturn(integrantes);

        IntegranteDTO dto = service.buscarIntegranteMaisUsado(null, null);

        Assertions.assertNotNull(dto);
        Mockito.verify(integranteRepository).findAll();
    }

    @Test
    void testBuscarIntegranteMaisUsado_ComFiltro() {
        LocalDate dataInicial = LocalDate.of(2025, 5, 1);
        LocalDate dataFinal = LocalDate.of(2025, 5, 31);

        // Criando integrantes com IDs para garantir diferenciação (e.g. equals/hashCode)
        Integrante i1 = new Integrante();
        ReflectionTestUtils.setField(i1, "id", 1L);
        Integrante i2 = new Integrante();
        ReflectionTestUtils.setField(i2, "id", 2L);

        // List com i2 repetido para ser o mais usado
        List<Integrante> integrantes = List.of(i1, i2, i2);

        Mockito.when(integranteRepository.findAllByTimeDataBetween(dataInicial, dataFinal))
                .thenReturn(integrantes);

        IntegranteDTO dto = service.buscarIntegranteMaisUsado(dataInicial, dataFinal);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(2L, dto.getId()); // espera que o mais usado seja i2 (id=2)

        Mockito.verify(integranteRepository).findAllByTimeDataBetween(dataInicial, dataFinal);
    }


    @Test
    void testBuscarIntegranteMaisUsado_NenhumIntegrante() {
        Mockito.when(integranteRepository.findAll()).thenReturn(List.of());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            service.buscarIntegranteMaisUsado(null, null);
        });

        Assertions.assertTrue(exception.getMessage().contains("Nenhum integrante encontrado"));
    }

    @Test
    void testBuscarFuncaoMaisComum() {
        Integrante i1 = new Integrante();
        i1.setFuncao("Líder");
        Integrante i2 = new Integrante();
        i2.setFuncao("Líder");
        Integrante i3 = new Integrante();
        i3.setFuncao("Membro");

        List<Integrante> integrantes = List.of(i1, i2, i3);
        Mockito.when(integranteRepository.findAll()).thenReturn(integrantes);

        Map<String, String> result = service.buscarFuncaoMaisComum(null, null);

        Assertions.assertEquals("Líder", result.get("funcao"));
    }

    @Test
    void testBuscarFranquiaMaisFamosa() {
        Integrante i1 = new Integrante();
        i1.setFranquia("Avengers");
        Integrante i2 = new Integrante();
        i2.setFranquia("Avengers");
        Integrante i3 = new Integrante();
        i3.setFranquia("Justice League");

        List<Integrante> integrantes = List.of(i1, i2, i3);
        Mockito.when(integranteRepository.findAll()).thenReturn(integrantes);

        Map<String, String> result = service.buscarFranquiaMaisFamosa(null, null);

        Assertions.assertEquals("Avengers", result.get("franquia"));
    }

    @Test
    void testContarPorFranquia() {
        Integrante i1 = new Integrante();
        i1.setFranquia("Avengers");
        Integrante i2 = new Integrante();
        i2.setFranquia("Avengers");
        Integrante i3 = new Integrante();
        i3.setFranquia("Justice League");

        List<Integrante> integrantes = List.of(i1, i2, i3);
        Mockito.when(integranteRepository.findAll()).thenReturn(integrantes);

        Map<String, Long> counts = service.contarPorFranquia(null, null);

        Assertions.assertEquals(2L, counts.get("Avengers"));
        Assertions.assertEquals(1L, counts.get("Justice League"));
    }

    @Test
    void testContarPorFuncao() {
        Integrante i1 = new Integrante();
        i1.setFuncao("Líder");
        Integrante i2 = new Integrante();
        i2.setFuncao("Líder");
        Integrante i3 = new Integrante();
        i3.setFuncao("Membro");

        List<Integrante> integrantes = List.of(i1, i2, i3);
        Mockito.when(integranteRepository.findAll()).thenReturn(integrantes);

        Map<String, Long> counts = service.contarPorFuncao(null, null);

        Assertions.assertEquals(2L, counts.get("Líder"));
        Assertions.assertEquals(1L, counts.get("Membro"));
    }
}