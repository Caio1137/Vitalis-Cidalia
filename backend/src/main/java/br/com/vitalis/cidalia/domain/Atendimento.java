package br.com.vitalis.cidalia.domain;

import br.com.vitalis.cidalia.validation.RegexPatterns;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "atendimentos")
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = RegexPatterns.CODIGO_ATENDIMENTO, message = "Codigo deve seguir ATD-000000")
    private String codigo;

    @NotNull
    @OneToOne(optional = false)
    @JoinColumn(name = "ocorrencia_id", unique = true)
    private Ocorrencia ocorrencia;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusAtendimento status = StatusAtendimento.AGUARDANDO_DESPACHO;

    private LocalDateTime despachoEm;
    private LocalDateTime chegadaEm;
    private LocalDateTime encerradoEm;

    @Positive
    private BigDecimal distanciaKm;

    @Positive
    private Integer tempoEstimadoMinutos;

    @Column(length = 1200)
    private String rotaResumo;

    @Column(length = 1200)
    private String observacoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public StatusAtendimento getStatus() {
        return status;
    }

    public void setStatus(StatusAtendimento status) {
        this.status = status;
    }

    public LocalDateTime getDespachoEm() {
        return despachoEm;
    }

    public void setDespachoEm(LocalDateTime despachoEm) {
        this.despachoEm = despachoEm;
    }

    public LocalDateTime getChegadaEm() {
        return chegadaEm;
    }

    public void setChegadaEm(LocalDateTime chegadaEm) {
        this.chegadaEm = chegadaEm;
    }

    public LocalDateTime getEncerradoEm() {
        return encerradoEm;
    }

    public void setEncerradoEm(LocalDateTime encerradoEm) {
        this.encerradoEm = encerradoEm;
    }

    public BigDecimal getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(BigDecimal distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public Integer getTempoEstimadoMinutos() {
        return tempoEstimadoMinutos;
    }

    public void setTempoEstimadoMinutos(Integer tempoEstimadoMinutos) {
        this.tempoEstimadoMinutos = tempoEstimadoMinutos;
    }

    public String getRotaResumo() {
        return rotaResumo;
    }

    public void setRotaResumo(String rotaResumo) {
        this.rotaResumo = rotaResumo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
