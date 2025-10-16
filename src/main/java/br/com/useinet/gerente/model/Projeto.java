package br.com.useinet.gerente.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany
    @JoinTable(name = "projeto_responsavel", joinColumns = @JoinColumn(name = "projeto_id"), inverseJoinColumns = @JoinColumn(name = "responsavel_id"))
    private List<Responsavel> responsaveis;

    private LocalDate inicioPrevisto;
    private LocalDate terminoPrevisto;
    private LocalDate inicioRealizado;
    private LocalDate terminoRealizado;

    private long diasAtraso;
    private double percentualTempoRestante;

    @PrePersist
    @PreUpdate
    public void atualizarStatusECalculos() {
        this.status = calcularStatus();
        this.diasAtraso = calcularDiasAtraso();
        this.percentualTempoRestante = calcularPercentualTempoRestante();
    }

    private Status calcularStatus() {
        LocalDate hoje = LocalDate.now();
        if (inicioRealizado == null && terminoRealizado == null) return Status.A_INICIAR;
        if (inicioRealizado != null && terminoRealizado == null) {
            if (terminoPrevisto != null && terminoPrevisto.isBefore(hoje)) return Status.ATRASADO;
            return Status.EM_ANDAMENTO;
        }
        return Status.CONCLUIDO;
    }

    private long calcularDiasAtraso() {
        LocalDate hoje = LocalDate.now();
        if (terminoRealizado != null) return 0;
        if (terminoPrevisto != null && terminoPrevisto.isBefore(hoje))
            return ChronoUnit.DAYS.between(terminoPrevisto, hoje);
        return 0;
    }

    private double calcularPercentualTempoRestante() {
        if (inicioPrevisto == null || terminoPrevisto == null) return 0.0;
        long total = ChronoUnit.DAYS.between(inicioPrevisto, terminoPrevisto);
        if (total <= 0) return 0.0;
        long usado = ChronoUnit.DAYS.between(inicioPrevisto, LocalDate.now());
        double restante = Math.max(0, total - usado);
        return (restante / (double) total) * 100;
    }

    public enum Status {
        A_INICIAR, EM_ANDAMENTO, ATRASADO, CONCLUIDO
    }
}
