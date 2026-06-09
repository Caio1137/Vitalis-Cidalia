package br.com.vitalis.cidalia.config;

import br.com.vitalis.cidalia.domain.Atendimento;
import br.com.vitalis.cidalia.domain.Equipe;
import br.com.vitalis.cidalia.domain.Ocorrencia;
import br.com.vitalis.cidalia.domain.PontoAtendimento;
import br.com.vitalis.cidalia.domain.PrioridadeOcorrencia;
import br.com.vitalis.cidalia.domain.StatusAtendimento;
import br.com.vitalis.cidalia.domain.StatusOcorrencia;
import br.com.vitalis.cidalia.domain.StatusOperacional;
import br.com.vitalis.cidalia.domain.TipoOcorrencia;
import br.com.vitalis.cidalia.domain.TipoPontoAtendimento;
import br.com.vitalis.cidalia.domain.TipoVeiculo;
import br.com.vitalis.cidalia.domain.Veiculo;
import br.com.vitalis.cidalia.repository.AtendimentoRepository;
import br.com.vitalis.cidalia.repository.EquipeRepository;
import br.com.vitalis.cidalia.repository.OcorrenciaRepository;
import br.com.vitalis.cidalia.repository.PontoAtendimentoRepository;
import br.com.vitalis.cidalia.repository.VeiculoRepository;
import br.com.vitalis.cidalia.service.CodigoOperacionalFactory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final PontoAtendimentoRepository pontoRepository;
    private final VeiculoRepository veiculoRepository;
    private final EquipeRepository equipeRepository;
    private final OcorrenciaRepository ocorrenciaRepository;
    private final AtendimentoRepository atendimentoRepository;
    private final CodigoOperacionalFactory codigoFactory;

    public DataSeeder(
            PontoAtendimentoRepository pontoRepository,
            VeiculoRepository veiculoRepository,
            EquipeRepository equipeRepository,
            OcorrenciaRepository ocorrenciaRepository,
            AtendimentoRepository atendimentoRepository,
            CodigoOperacionalFactory codigoFactory
    ) {
        this.pontoRepository = pontoRepository;
        this.veiculoRepository = veiculoRepository;
        this.equipeRepository = equipeRepository;
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.atendimentoRepository = atendimentoRepository;
        this.codigoFactory = codigoFactory;
    }

    @Override
    public void run(String... args) {
        if (pontoRepository.count() > 0) {
            return;
        }

        var hospitalNorte = ponto("Hospital Municipal Norte", TipoPontoAtendimento.HOSPITAL, "NORTE", "Av. Saude, 1000", "74000-010");
        var upaOeste = ponto("UPA Cidalia Oeste", TipoPontoAtendimento.UPA, "OESTE", "Rua das Palmeiras, 455", "74000-020");
        var baseSul = ponto("Base SAMU Sul", TipoPontoAtendimento.BASE_SAMU, "SUL", "Alameda Operacional, 77", "74000-030");
        var coletaLeste = ponto("Ponto de Coleta Leste", TipoPontoAtendimento.PONTO_COLETA, "LESTE", "Praca das Aguas, 12", "74000-040");
        pontoRepository.save(hospitalNorte);
        pontoRepository.save(upaOeste);
        pontoRepository.save(baseSul);
        pontoRepository.save(coletaLeste);

        var alfa = veiculo("USB-01", "VTL1A01", TipoVeiculo.AMBULANCIA_USB);
        var bravo = veiculo("USA-02", "VTL1B02", TipoVeiculo.AMBULANCIA_USA);
        var charlie = veiculo("MOT-03", "VTL1C03", TipoVeiculo.MOTOLANCIA);
        var delta = veiculo("APO-04", "VTL1D04", TipoVeiculo.VIATURA_APOIO);
        veiculoRepository.save(alfa);
        veiculoRepository.save(bravo);
        veiculoRepository.save(charlie);
        veiculoRepository.save(delta);

        var equipeAlfa = equipe("Equipe Alfa", "NORTE", alfa);
        var equipeBravo = equipe("Equipe Bravo", "OESTE", bravo);
        var equipeCharlie = equipe("Equipe Charlie", "LESTE", charlie);
        var equipeDelta = equipe("Equipe Delta", "SUL", delta);
        equipeRepository.save(equipeAlfa);
        equipeRepository.save(equipeBravo);
        equipeRepository.save(equipeCharlie);
        equipeRepository.save(equipeDelta);

        var ocorrencia1 = ocorrencia(1, "Marina Torres", "123.456.789-09", TipoOcorrencia.CARDIACA, PrioridadeOcorrencia.CRITICA,
                "Rua 12, Qd. 4", "Setor Norte", "Paciente com dor toracica e falta de ar.", hospitalNorte);
        var ocorrencia2 = ocorrencia(2, "Claudio Mendes", "12.345.678/0001-90", TipoOcorrencia.ACIDENTE_TRANSITO, PrioridadeOcorrencia.ALTA,
                "Av. Circular, km 8", "Jardim Oeste", "Colisao entre motocicleta e veiculo de passeio.", upaOeste);
        var ocorrencia3 = ocorrencia(3, "Rafaela Lima", "987.654.321-00", TipoOcorrencia.RESPIRATORIA, PrioridadeOcorrencia.ALTA,
                "Rua Ipameri, 302", "Vila Leste", "Crise respiratoria em idosa acamada.", coletaLeste);
        var ocorrencia4 = ocorrencia(4, "Unidade Escolar Aurora", "10.222.333/0001-44", TipoOcorrencia.CLINICA, PrioridadeOcorrencia.MEDIA,
                "Av. dos Estudantes, 90", "Centro", "Aluno com desmaio e queda de pressao.", hospitalNorte);
        ocorrenciaRepository.save(ocorrencia1);
        ocorrenciaRepository.save(ocorrencia2);
        ocorrenciaRepository.save(ocorrencia3);
        ocorrenciaRepository.save(ocorrencia4);

        var atendimento = new Atendimento();
        atendimento.setCodigo(codigoFactory.criarCodigoAtendimento(1));
        atendimento.setOcorrencia(ocorrencia1);
        atendimento.setEquipe(equipeAlfa);
        atendimento.setVeiculo(alfa);
        atendimento.setStatus(StatusAtendimento.EM_ROTA);
        atendimento.setDespachoEm(LocalDateTime.now().minusMinutes(18));
        atendimento.setDistanciaKm(new BigDecimal("6.8"));
        atendimento.setTempoEstimadoMinutos(14);
        atendimento.setRotaResumo("Base SAMU Sul -> Av. Saude -> Rua 12 -> Hospital Municipal Norte");
        atendimento.setObservacoes("Prioridade critica. Equipe orientada a comunicar chegada imediatamente.");
        atendimentoRepository.save(atendimento);

        ocorrencia1.setStatus(StatusOcorrencia.EM_ATENDIMENTO);
        equipeAlfa.setStatus(StatusOperacional.EM_ATENDIMENTO);
        alfa.setStatus(StatusOperacional.EM_ATENDIMENTO);
        ocorrenciaRepository.save(ocorrencia1);
        equipeRepository.save(equipeAlfa);
        veiculoRepository.save(alfa);
    }

    private PontoAtendimento ponto(String nome, TipoPontoAtendimento tipo, String setor, String endereco, String cep) {
        var ponto = new PontoAtendimento();
        ponto.setNome(nome);
        ponto.setTipo(tipo);
        ponto.setSetor(setor);
        ponto.setEndereco(endereco);
        ponto.setCep(cep);
        ponto.setTelefone("62999990000");
        ponto.setLatitude(new BigDecimal("-16.6869"));
        ponto.setLongitude(new BigDecimal("-49.2648"));
        return ponto;
    }

    private Veiculo veiculo(String prefixo, String placa, TipoVeiculo tipo) {
        var veiculo = new Veiculo();
        veiculo.setPrefixo(prefixo);
        veiculo.setPlaca(placa);
        veiculo.setTipo(tipo);
        return veiculo;
    }

    private Equipe equipe(String nome, String setor, Veiculo veiculo) {
        var equipe = new Equipe();
        equipe.setNome(nome);
        equipe.setSetor(setor);
        equipe.setTelefone("62988887777");
        equipe.setVeiculo(veiculo);
        return equipe;
    }

    private Ocorrencia ocorrencia(
            long sequencial,
            String solicitante,
            String documento,
            TipoOcorrencia tipo,
            PrioridadeOcorrencia prioridade,
            String endereco,
            String bairro,
            String descricao,
            PontoAtendimento destino
    ) {
        var ocorrencia = new Ocorrencia();
        ocorrencia.setProtocolo(codigoFactory.criarProtocoloOcorrencia(sequencial));
        ocorrencia.setSolicitanteNome(solicitante);
        ocorrencia.setSolicitanteDocumento(documento);
        ocorrencia.setTelefoneSolicitante("62977776666");
        ocorrencia.setTipo(tipo);
        ocorrencia.setPrioridade(prioridade);
        ocorrencia.setEndereco(endereco);
        ocorrencia.setBairro(bairro);
        ocorrencia.setDescricao(descricao);
        ocorrencia.setPontoDestino(destino);
        ocorrencia.setRegistradaEm(LocalDateTime.now().minusMinutes(sequencial * 11));
        return ocorrencia;
    }
}
