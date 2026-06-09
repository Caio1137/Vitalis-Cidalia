import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import {
  Ocorrencia,
  PontoAtendimento,
  PRIORIDADES,
  STATUS_OCORRENCIA,
  TIPOS_OCORRENCIA,
  label,
} from '../../models/domain.models';
import { OcorrenciaService } from '../../services/ocorrencia.service';
import { RecursoService } from '../../services/recurso.service';

@Component({
  selector: 'app-ocorrencias',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './ocorrencias.component.html',
})
export class OcorrenciasComponent implements OnInit {
  private readonly fb = inject(FormBuilder);
  private readonly ocorrenciaService = inject(OcorrenciaService);
  private readonly recursoService = inject(RecursoService);

  ocorrencias: Ocorrencia[] = [];
  pontos: PontoAtendimento[] = [];
  prioridades = PRIORIDADES;
  tipos = TIPOS_OCORRENCIA;
  status = STATUS_OCORRENCIA;
  label = label;
  erro = '';
  sucesso = '';

  filtros = this.fb.group({
    status: [''],
    prioridade: [''],
    tipo: [''],
    setor: [''],
  });

  form = this.fb.group({
    solicitanteNome: ['', [Validators.required, Validators.minLength(3)]],
    solicitanteDocumento: ['123.456.789-09', Validators.required],
    telefoneSolicitante: ['62977776666', Validators.required],
    tipo: ['CLINICA', Validators.required],
    prioridade: ['MEDIA', Validators.required],
    endereco: ['', Validators.required],
    bairro: ['', Validators.required],
    descricao: ['', [Validators.required, Validators.minLength(10)]],
    pontoDestinoId: ['', Validators.required],
  });

  ngOnInit(): void {
    this.carregarPontos();
    this.carregar();
  }

  carregar(): void {
    this.erro = '';
    this.ocorrenciaService.listar(this.filtros.value).subscribe({
      next: (dados) => (this.ocorrencias = dados),
      error: (error) => (this.erro = this.mensagemErro(error)),
    });
  }

  salvar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    const payload = { ...this.form.value, pontoDestinoId: Number(this.form.value.pontoDestinoId) };
    this.ocorrenciaService.criar(payload).subscribe({
      next: () => {
        this.sucesso = 'Ocorrencia registrada.';
        this.form.patchValue({ endereco: '', bairro: '', descricao: '' });
        this.carregar();
      },
      error: (error) => (this.erro = this.mensagemErro(error)),
    });
  }

  atualizarStatus(ocorrencia: Ocorrencia, status: string): void {
    this.ocorrenciaService.atualizarStatus(ocorrencia.id, status).subscribe({
      next: () => this.carregar(),
      error: (error) => (this.erro = this.mensagemErro(error)),
    });
  }

  private carregarPontos(): void {
    this.recursoService.listarPontos().subscribe({
      next: (pontos) => (this.pontos = pontos),
      error: () => (this.erro = 'Nao foi possivel carregar pontos de atendimento.'),
    });
  }

  private mensagemErro(error: any): string {
    return error?.error?.detalhes?.join(' ') ?? 'Operacao nao concluida.';
  }
}
