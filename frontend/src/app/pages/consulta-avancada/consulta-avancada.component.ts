import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ConsultaAvancadaResponse, label } from '../../models/domain.models';
import { ConsultaAvancadaService } from '../../services/consulta-avancada.service';

@Component({
  selector: 'app-consulta-avancada',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './consulta-avancada.component.html',
})
export class ConsultaAvancadaComponent {
  private readonly fb = inject(FormBuilder);
  private readonly service = inject(ConsultaAvancadaService);

  form = this.fb.group({
    comando: ['parametro.tipo = "CARDIACA" AND parametro.setor = "NORTE"', Validators.required],
  });
  resultado?: ConsultaAvancadaResponse;
  erro = '';
  label = label;

  executar(): void {
    if (this.form.invalid) return this.form.markAllAsTouched();
    this.erro = '';
    this.service.executar(this.form.value.comando ?? '').subscribe({
      next: (resultado) => (this.resultado = resultado),
      error: (error) => (this.erro = error?.error?.detalhes?.join(' ') ?? 'Consulta invalida.'),
    });
  }
}
