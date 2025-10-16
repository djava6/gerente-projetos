package br.com.useinet.gerente;

import br.com.useinet.gerente.model.Responsavel;
import br.com.useinet.gerente.repository.ResponsavelRepository;
import br.com.useinet.gerente.service.ResponsavelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ResponsavelServiceTest {

    @Mock
    private ResponsavelRepository responsavelRepository;

    @InjectMocks
    private ResponsavelService responsavelService;

    private Responsavel responsavel;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        responsavel = Responsavel.builder().id(1L).nome("Carlos Silva").email("carlos@empresa.com").cargo("Gerente").build();
    }

    @Test
    void deveListarResponsaveis() {
        when(responsavelRepository.findAll()).thenReturn(List.of(responsavel));
        List<Responsavel> result = responsavelService.listar();
        assertEquals(1, result.size());
        assertEquals("Carlos Silva", result.get(0).getNome());
    }

    @Test
    void deveBuscarPorId() {
        when(responsavelRepository.findById(1L)).thenReturn(Optional.of(responsavel));
        Responsavel result = responsavelService.buscarPorId(1L);
        assertEquals("Carlos Silva", result.getNome());
    }

    @Test
    void deveLancarErroAoBuscarIdInexistente() {
        when(responsavelRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> responsavelService.buscarPorId(99L));
    }

    @Test
    void deveSalvarResponsavel() {
        when(responsavelRepository.findByEmail(responsavel.getEmail())).thenReturn(Optional.empty());
        when(responsavelRepository.save(responsavel)).thenReturn(responsavel);

        Responsavel result = responsavelService.salvar(responsavel);
        assertNotNull(result);
        verify(responsavelRepository, times(1)).save(responsavel);
    }

    @Test
    void naoDeveSalvarEmailDuplicado() {
        when(responsavelRepository.findByEmail(responsavel.getEmail())).thenReturn(Optional.of(responsavel));
        assertThrows(RuntimeException.class, () -> responsavelService.salvar(responsavel));
    }

    @Test
    void deveAtualizarResponsavel() {
        when(responsavelRepository.findById(1L)).thenReturn(Optional.of(responsavel));
        when(responsavelRepository.save(any())).thenReturn(responsavel);

        Responsavel atualizado = responsavelService.atualizar(1L, responsavel);
        assertEquals("Carlos Silva", atualizado.getNome());
    }

    @Test
    void deveExcluirResponsavel() {
        responsavelService.excluir(1L);
        verify(responsavelRepository, times(1)).deleteById(1L);
    }
}
