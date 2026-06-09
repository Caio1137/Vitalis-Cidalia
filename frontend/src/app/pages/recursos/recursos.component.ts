import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import {
  Equipe,
  PontoAtendimento,
  STATUS_OPERACIONAL,
  TIPOS_PONTO,
  TIPOS_VEICULO,
  Veiculo,
  label,
} from '../../models/domain.models';
import { RecursoService } from '../../services/recurso.service';

@Component({
  selector: 'app-recursos',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './recursos.component.html',
})
export class RecursosComponent implements OnInit {
  private readonly fb = inject(FormBuilder);
  private readonly service = inject(RecursoService);

  pontos: PontoAtendimento[] = [];
  veiculos: Veiculo[] = [];
  equipes: Equipe[] = [];
  tiposPonto = TIPOS_PONTO;
  tiposVeiculo = TIPOS_VEICULO;
  status = STATUS_OPERACIONAL;
  label = label;
  erro = '';

  pontoForm = this.fb.group({
    nome: ['', Validators.required],
    tipo: ['HOSPITAL', Validators.required],
    setor: ['', Validators.required],
    endereco: ['', Validators.required],
    cep: ['74000-000', Validators.required],
    telefone: ['62999990000', Validators.required],
  });

  veiculoForm = this.fb.group({
    prefixo: ['', Validators.required],
    placa: ['ABC1D23', Validators.required],
    tipo: ['AMBULANCIA_USB', Validators.required],
    status: ['DISPONIVEL', Validators.required],
    observacao: [''],
  });

  equipeForm = this.fb.group({
    nome: ['', Validators.required],
    setor: ['', Validators.required],
    telefone: ['62988887777', Validators.required],
    status: ['DISPONIVEL', Validators.required],
    veiculoId: [''],
  });

  ngOnInit(): void {
    this.carregar();
  }

  carregar(): void {
    this.service.listarPontos().subscribe((pontos) => (this.pontos = pontos));
    this.service.listarVeiculos().subscribe((veiculos) => (this.veiculos = veiculos));
    this.service.listarEquipes().subscribe((equipes) => (this.equipes = equipes));
  }

  salvarPonto(): void {
    if (this.pontoForm.invalid) return this.pontoForm.markAllAsTouched();
    this.service.criarPonto(this.pontoForm.value).subscribe({
      next: () => {
        this.pontoForm.patchValue({ nome: '', setor: '', endereco: '' });
        this.carregar();
      },
      error: (error) => (this.erro = this.mensagemErro(error)),
    });
  }

  salvarVeiculo(): void {
    if (this.veiculoForm.invalid) return this.veiculoForm.markAllAsTouched();
    this.service.criarVeiculo(this.veiculoForm.value).subscribe({
      next: () => {
        this.veiculoForm.patchValue({ prefixo: '', placa: '', observacao: '' });
        this.carregar();
      },
      error: (error) => (this.erro = this.mensagemErro(error)),
    });
  }

  salvarEquipe(): void {
    if (this.equipeForm.invalid) return this.equipeForm.markAllAsTouched();
    const payload = {
      ...this.equipeForm.value,
      veiculoId: this.equipeForm.value.veiculoId ? Number(this.equipeForm.value.veiculoId) : null,
    };
    this.service.criarEquipe(payload).subscribe({
      next: () => {
        this.equipeForm.patchValue({ nome: '', setor: '', veiculoId: '' });
        this.carregar();
      },
      error: (error) => (this.erro = this.mensagemErro(error)),
    });
  }

  private mensagemErro(error: any): string {
    return error?.error?.detalhes?.join(' ') ?? 'Operacao nao concluida.';
  }
}
