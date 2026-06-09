import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Equipe, PontoAtendimento, Veiculo } from '../models/domain.models';

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

  listarVeiculos(): Observable<Veiculo[]> {
    return this.http.get<Veiculo[]>(`${this.apiUrl}/veiculos`);
  }

  criarVeiculo(payload: unknown): Observable<Veiculo> {
    return this.http.post<Veiculo>(`${this.apiUrl}/veiculos`, payload);
  }

  listarEquipes(): Observable<Equipe[]> {
    return this.http.get<Equipe[]>(`${this.apiUrl}/equipes`);
  }

  criarEquipe(payload: unknown): Observable<Equipe> {
    return this.http.post<Equipe>(`${this.apiUrl}/equipes`, payload);
  }
}
