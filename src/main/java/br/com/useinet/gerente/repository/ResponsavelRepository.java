package br.com.useinet.gerente.repository;

import br.com.useinet.gerente.model.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
    Optional<Responsavel> findByEmail(String email);
}
