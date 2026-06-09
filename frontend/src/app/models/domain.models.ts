export type PrioridadeOcorrencia = 'BAIXA' | 'MEDIA' | 'ALTA' | 'CRITICA';
export type StatusOcorrencia =
  | 'REGISTRADA'
  | 'EM_TRIAGEM'
  | 'DESPACHADA'
  | 'EM_ATENDIMENTO'
  | 'CONCLUIDA'
  | 'CANCELADA';
export type TipoOcorrencia =
  | 'CLINICA'
  | 'TRAUMA'
  | 'CARDIACA'
  | 'RESPIRATORIA'
  | 'ACIDENTE_TRANSITO'
  | 'TRANSFERENCIA';
export type StatusOperacional = 'DISPONIVEL' | 'EM_ATENDIMENTO' | 'MANUTENCAO' | 'INATIVO';
export type StatusAtendimento =
  | 'AGUARDANDO_DESPACHO'
  | 'DESPACHADO'
  | 'EM_ROTA'
  | 'NO_LOCAL'
  | 'FINALIZADO'
  | 'CANCELADO';
export type TipoPontoAtendimento = 'HOSPITAL' | 'UPA' | 'BASE_SAMU' | 'PONTO_APOIO' | 'PONTO_COLETA';
export type TipoVeiculo = 'AMBULANCIA_USB' | 'AMBULANCIA_USA' | 'MOTOLANCIA' | 'VIATURA_APOIO';

export interface PontoAtendimento {
  id: number;
  nome: string;
  tipo: TipoPontoAtendimento;
  setor: string;
  endereco: string;
  cep: string;
  telefone: string;
  latitude?: number;
  longitude?: number;
  ativo: boolean;
}

export interface Veiculo {
  id: number;
  prefixo: string;
  placa: string;
  tipo: TipoVeiculo;
  status: StatusOperacional;
  observacao?: string;
}

export interface Equipe {
  id: number;
  nome: string;
  setor: string;
  telefone: string;
  status: StatusOperacional;
  veiculo?: Veiculo;
}

export interface Ocorrencia {
  id: number;
  protocolo: string;
  solicitanteNome: string;
  solicitanteDocumento: string;
  telefoneSolicitante: string;
  tipo: TipoOcorrencia;
  prioridade: PrioridadeOcorrencia;
  status: StatusOcorrencia;
  endereco: string;
  bairro: string;
  descricao: string;
  registradaEm: string;
  pontoDestino: PontoAtendimento;
}

export interface Atendimento {
  id: number;
  codigo: string;
  ocorrencia: Ocorrencia;
  equipe: Equipe;
  veiculo: Veiculo;
  status: StatusAtendimento;
  despachoEm: string;
  chegadaEm?: string;
  encerradoEm?: string;
  distanciaKm?: number;
  tempoEstimadoMinutos?: number;
  rotaResumo?: string;
  observacoes?: string;
}

export interface DashboardResumo {
  ocorrenciasAbertas: number;
  ocorrenciasCriticas: number;
  equipesDisponiveis: number;
  veiculosDisponiveis: number;
  atendimentosEmAndamento: number;
  ultimasOcorrencias: Ocorrencia[];
  ultimosAtendimentos: Atendimento[];
}

export interface ConsultaAvancadaResponse {
  comando: string;
  tokens: string[];
  arvoreSintatica: string;
  totalEncontrado: number;
  resultados: Ocorrencia[];
}

export const PRIORIDADES: PrioridadeOcorrencia[] = ['BAIXA', 'MEDIA', 'ALTA', 'CRITICA'];
export const STATUS_OCORRENCIA: StatusOcorrencia[] = [
  'REGISTRADA',
  'EM_TRIAGEM',
  'DESPACHADA',
  'EM_ATENDIMENTO',
  'CONCLUIDA',
  'CANCELADA',
];
export const TIPOS_OCORRENCIA: TipoOcorrencia[] = [
  'CLINICA',
  'TRAUMA',
  'CARDIACA',
  'RESPIRATORIA',
  'ACIDENTE_TRANSITO',
  'TRANSFERENCIA',
];
export const STATUS_OPERACIONAL: StatusOperacional[] = [
  'DISPONIVEL',
  'EM_ATENDIMENTO',
  'MANUTENCAO',
  'INATIVO',
];
export const STATUS_ATENDIMENTO: StatusAtendimento[] = [
  'DESPACHADO',
  'EM_ROTA',
  'NO_LOCAL',
  'FINALIZADO',
  'CANCELADO',
];
export const TIPOS_PONTO: TipoPontoAtendimento[] = [
  'HOSPITAL',
  'UPA',
  'BASE_SAMU',
  'PONTO_APOIO',
  'PONTO_COLETA',
];
export const TIPOS_VEICULO: TipoVeiculo[] = [
  'AMBULANCIA_USB',
  'AMBULANCIA_USA',
  'MOTOLANCIA',
  'VIATURA_APOIO',
];

export function label(valor?: string): string {
  return (valor ?? '').replaceAll('_', ' ').toLowerCase().replace(/(^|\s)\S/g, (letra) => letra.toUpperCase());
}
