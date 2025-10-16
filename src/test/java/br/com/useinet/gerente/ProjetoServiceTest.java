package br.com.useinet.gerente;

import br.com.useinet.gerente.model.Projeto;
import br.com.useinet.gerente.repository.ProjetoRepository;
import br.com.useinet.gerente.service.ProjetoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjetoServiceTest {

    @Mock
    private ProjetoRepository projetoRepository;

    @InjectMocks
    private ProjetoService projetoService;

    private Projeto projeto;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        projeto = Projeto.builder().id(1L).nome("Projeto Teste").inicioPrevisto(LocalDate.now().minusDays(5)).terminoPrevisto(LocalDate.now().plusDays(5)).build();
    }

    @Test
    void deveListarProjetos() {
        when(projetoRepository.findAll()).thenReturn(List.of(projeto));
        List<Projeto> result = projetoService.listar();
        assertEquals(1, result.size());
        assertEquals("Projeto Teste", result.get(0).getNome());
    }

    @Test
    void deveBuscarPorId() {
        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));
        Projeto result = projetoService.buscarPorId(1L);
        assertEquals("Projeto Teste", result.getNome());
    }

    @Test
    void deveLancarExcecaoAoBuscarIdInexistente() {
        when(projetoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> projetoService.buscarPorId(99L));
    }

    @Test
    void deveSalvarProjeto() {
        when(projetoRepository.save(projeto)).thenReturn(projeto);
        Projeto result = projetoService.salvar(projeto);
        assertNotNull(result);
        verify(projetoRepository, times(1)).save(projeto);
    }

    @Test
    void deveAtualizarProjeto() {
        when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));
        when(projetoRepository.save(any())).thenReturn(projeto);

        Projeto atualizado = projetoService.atualizar(1L, projeto);
        assertEquals(projeto.getNome(), atualizado.getNome());
    }

    @Test
    void deveExcluirProjeto() {
        projetoService.excluir(1L);
        verify(projetoRepository, times(1)).deleteById(1L);
    }
}
