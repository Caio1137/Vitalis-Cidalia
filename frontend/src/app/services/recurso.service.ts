import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Equipe, PontoAtendimento, StatusOperacional, Veiculo } from '../models/domain.models';

@Injectable({ providedIn: 'root' })
export class RecursoService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = environment.apiUrl;

  listarPontos(): Observable<PontoAtendimento[]> {
    return this.http.get<PontoAtendimento[]>(`${this.apiUrl}/pontos-atendimento`);
  }

  criarPonto(payload: unknown): Observable<PontoAtendimento> {
    return this.http.post<PontoAtendimento>(`${this.apiUrl}/pontos-atendimento`, payload);
  }

  inativarPonto(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/pontos-atendimento/${id}`);
  }

  listarVeiculos(): Observable<Veiculo[]> {
    return this.http.get<Veiculo[]>(`${this.apiUrl}/veiculos`);
  }

  criarVeiculo(payload: unknown): Observable<Veiculo> {
    return this.http.post<Veiculo>(`${this.apiUrl}/veiculos`, payload);
  }

  atualizarStatusVeiculo(id: number, status: StatusOperacional): Observable<Veiculo> {
    return this.http.patch<Veiculo>(`${this.apiUrl}/veiculos/${id}/status`, { status });
  }

  listarEquipes(): Observable<Equipe[]> {
    return this.http.get<Equipe[]>(`${this.apiUrl}/equipes`);
  }

  criarEquipe(payload: unknown): Observable<Equipe> {
    return this.http.post<Equipe>(`${this.apiUrl}/equipes`, payload);
  }

  atualizarStatusEquipe(id: number, status: StatusOperacional): Observable<Equipe> {
    return this.http.patch<Equipe>(`${this.apiUrl}/equipes/${id}/status`, { status });
  }
}
