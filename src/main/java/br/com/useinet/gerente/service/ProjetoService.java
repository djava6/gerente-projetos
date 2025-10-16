package br.com.useinet.gerente.service;

import br.com.useinet.gerente.model.Projeto;
import br.com.useinet.gerente.model.Responsavel;
import br.com.useinet.gerente.repository.ProjetoRepository;
import br.com.useinet.gerente.repository.ResponsavelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final ResponsavelRepository responsavelRepository;

    public List<Projeto> listar() {
        return projetoRepository.findAll();
    }

    public Projeto buscarPorId(Long id) {
        return projetoRepository.findById(id).orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
    }

    public Projeto salvar(Projeto projeto) {
        if (projeto.getResponsaveis() != null && !projeto.getResponsaveis().isEmpty()) {
            List<Long> ids = projeto.getResponsaveis().stream().map(Responsavel::getId).toList();

            projeto.setResponsaveis(responsavelRepository.findAllById(ids));
        }
        return projetoRepository.save(projeto);
    }

    public Projeto atualizar(Long id, Projeto projetoAtualizado) {
        Projeto existente = buscarPorId(id);
        projetoAtualizado.setId(existente.getId());
        return projetoRepository.save(projetoAtualizado);
    }

    public void excluir(Long id) {
        projetoRepository.deleteById(id);
    }
}
