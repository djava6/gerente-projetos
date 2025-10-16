package br.com.useinet.gerente.service;

import br.com.useinet.gerente.model.Responsavel;
import br.com.useinet.gerente.repository.ResponsavelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponsavelService {

    private final ResponsavelRepository responsavelRepository;

    public List<Responsavel> listar() {
        return responsavelRepository.findAll();
    }

    public Responsavel buscarPorId(Long id) {
        return responsavelRepository.findById(id).orElseThrow(() -> new RuntimeException("Responsável não encontrado"));
    }

    public Responsavel salvar(Responsavel responsavel) {
        responsavelRepository.findByEmail(responsavel.getEmail()).ifPresent(r -> {
            throw new RuntimeException("E-mail já cadastrado");
        });
        return responsavelRepository.save(responsavel);
    }

    public Responsavel atualizar(Long id, Responsavel novo) {
        Responsavel existente = buscarPorId(id);
        novo.setId(existente.getId());
        return responsavelRepository.save(novo);
    }

    public void excluir(Long id) {
        responsavelRepository.deleteById(id);
    }
}
