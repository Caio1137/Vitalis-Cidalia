package br.com.vitalis.cidalia.domain;

import br.com.vitalis.cidalia.validation.RegexPatterns;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String prefixo;

    @NotBlank
    @Pattern(regexp = RegexPatterns.PLACA_MERCOSUL, message = "Placa deve seguir o formato AAA1A11")
    private String placa;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusOperacional status = StatusOperacional.DISPONIVEL;

    private String observacao;

    @OneToOne(mappedBy = "veiculo")
    private Equipe equipe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrefixo() {
        return prefixo;
    }

    public void setPrefixo(String prefixo) {
        this.prefixo = prefixo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public TipoVeiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVeiculo tipo) {
        this.tipo = tipo;
    }

    public StatusOperacional getStatus() {
        return status;
    }

    public void setStatus(StatusOperacional status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Equipe getEquipe() {
        return equipe;
    }
}
