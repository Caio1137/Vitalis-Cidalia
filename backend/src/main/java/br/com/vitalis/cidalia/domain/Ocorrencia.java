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
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
@Table(name = "ocorrencias")
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = RegexPatterns.PROTOCOLO_OCORRENCIA, message = "Protocolo deve seguir CID-AAAA-000000")
    private String protocolo;

    @NotBlank
    private String solicitanteNome;

    @Pattern(regexp = RegexPatterns.CPF_CNPJ, message = "Documento deve ser CPF ou CNPJ valido em formato")
    private String solicitanteDocumento;

    @Pattern(regexp = RegexPatterns.TELEFONE_BR, message = "Telefone deve seguir o padrao brasileiro")
    private String telefoneSolicitante;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoOcorrencia tipo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PrioridadeOcorrencia prioridade;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusOcorrencia status = StatusOcorrencia.REGISTRADA;

    @NotBlank
    private String endereco;

    @NotBlank
    private String bairro;

    @NotBlank
    @Column(length = 1200)
    private String descricao;

    private LocalDateTime registradaEm;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ponto_destino_id")
    private PontoAtendimento pontoDestino;

    @OneToOne(mappedBy = "ocorrencia")
    private Atendimento atendimento;

    @PrePersist
    public void prePersist() {
        if (registradaEm == null) {
            registradaEm = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getSolicitanteNome() {
        return solicitanteNome;
    }

    public void setSolicitanteNome(String solicitanteNome) {
        this.solicitanteNome = solicitanteNome;
    }

    public String getSolicitanteDocumento() {
        return solicitanteDocumento;
    }

    public void setSolicitanteDocumento(String solicitanteDocumento) {
        this.solicitanteDocumento = solicitanteDocumento;
    }

    public String getTelefoneSolicitante() {
        return telefoneSolicitante;
    }

    public void setTelefoneSolicitante(String telefoneSolicitante) {
        this.telefoneSolicitante = telefoneSolicitante;
    }

    public TipoOcorrencia getTipo() {
        return tipo;
    }

    public void setTipo(TipoOcorrencia tipo) {
        this.tipo = tipo;
    }

    public PrioridadeOcorrencia getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioridadeOcorrencia prioridade) {
        this.prioridade = prioridade;
    }

    public StatusOcorrencia getStatus() {
        return status;
    }

    public void setStatus(StatusOcorrencia status) {
        this.status = status;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getRegistradaEm() {
        return registradaEm;
    }

    public void setRegistradaEm(LocalDateTime registradaEm) {
        this.registradaEm = registradaEm;
    }

    public PontoAtendimento getPontoDestino() {
        return pontoDestino;
    }

    public void setPontoDestino(PontoAtendimento pontoDestino) {
        this.pontoDestino = pontoDestino;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }
}
