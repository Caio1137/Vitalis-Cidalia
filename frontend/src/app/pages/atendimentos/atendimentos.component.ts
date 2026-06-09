import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import {
  Atendimento,
  Equipe,
  Ocorrencia,
  STATUS_ATENDIMENTO,
  Veiculo,
  label,
} from '../../models/domain.models';
import { AtendimentoService } from '../../services/atendimento.service';
import { OcorrenciaService } from '../../services/ocorrencia.service';
import { RecursoService } from '../../services/recurso.service';

@Component({
  selector: 'app-atendimentos',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './atendimentos.component.html',
})
export class AtendimentosComponent implements OnInit {
  private readonly fb = inject(FormBuilder);
  private readonly atendimentoService = inject(AtendimentoService);
  private readonly ocorrenciaService = inject(OcorrenciaService);
  private readonly recursoService = inject(RecursoService);

  atendimentos: Atendimento[] = [];
  ocorrencias: Ocorrencia[] = [];
  equipes: Equipe[] = [];
  veiculos: Veiculo[] = [];
  status = STATUS_ATENDIMENTO;
  label = label;
  erro = '';

  form = this.fb.group({
    ocorrenciaId: ['', Validators.required],
    equipeId: ['', Validators.required],
    veiculoId: ['', Validators.required],
    distanciaKm: [6.5, Validators.required],
    tempoEstimadoMinutos: [14, Validators.required],
    rotaResumo: ['Base operacional -> ponto da ocorrencia -> destino assistencial'],
    observacoes: [''],
  });

  ngOnInit(): void {
    this.carregar();
  }

  carregar(): void {
    this.atendimentoService.listar().subscribe((atendimentos) => (this.atendimentos = atendimentos));
    this.ocorrenciaService.listar().subscribe((ocorrencias) => (this.ocorrencias = ocorrencias));
    this.recursoService.listarEquipes().subscribe((equipes) => (this.equipes = equipes));
    this.recursoService.listarVeiculos().subscribe((veiculos) => (this.veiculos = veiculos));
  }

  salvar(): void {
    if (this.form.invalid) return this.form.markAllAsTouched();
    const payload = {
      ...this.form.value,
      ocorrenciaId: Number(this.form.value.ocorrenciaId),
      equipeId: Number(this.form.value.equipeId),
      veiculoId: Number(this.form.value.veiculoId),
    };
    this.atendimentoService.criar(payload).subscribe({
      next: () => {
        this.form.patchValue({ ocorrenciaId: '', equipeId: '', veiculoId: '', observacoes: '' });
        this.carregar();
      },
      error: (error) => (this.erro = this.mensagemErro(error)),
    });
  }

  atualizar(atendimento: Atendimento, status: string): void {
    this.atendimentoService.atualizar(atendimento.id, { status, observacoes: atendimento.observacoes }).subscribe({
      next: () => this.carregar(),
      error: (error) => (this.erro = this.mensagemErro(error)),
    });
  }

  private mensagemErro(error: any): string {
    return error?.error?.detalhes?.join(' ') ?? 'Operacao nao concluida.';
  }
}
