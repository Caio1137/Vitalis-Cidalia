package br.com.vitalis.cidalia.domain;

import br.com.vitalis.cidalia.validation.RegexPatterns;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "equipes")
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String setor;

    @Pattern(regexp = RegexPatterns.TELEFONE_BR, message = "Telefone deve seguir o padrao brasileiro")
    private String telefone;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusOperacional status = StatusOperacional.DISPONIVEL;

    @OneToOne
    @JoinColumn(name = "veiculo_id", unique = true)
    private Veiculo veiculo;

    @OneToMany(mappedBy = "equipe")
    private List<Atendimento> atendimentos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public StatusOperacional getStatus() {
        return status;
    }

    public void setStatus(StatusOperacional status) {
        this.status = status;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }
}
