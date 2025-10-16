package br.com.useinet.gerente.repository;

import br.com.useinet.gerente.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

}
