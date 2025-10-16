package br.com.useinet.gerente.repository;

import br.com.useinet.gerente.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    List<Projeto> findByStatus(Projeto.Status status);
}
