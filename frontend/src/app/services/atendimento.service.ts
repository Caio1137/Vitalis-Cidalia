import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Atendimento } from '../models/domain.models';

@Injectable({ providedIn: 'root' })
export class AtendimentoService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = `${environment.apiUrl}/atendimentos`;

  listar(): Observable<Atendimento[]> {
    return this.http.get<Atendimento[]>(this.apiUrl);
  }

  criar(payload: unknown): Observable<Atendimento> {
    return this.http.post<Atendimento>(this.apiUrl, payload);
  }

  atualizar(id: number, payload: unknown): Observable<Atendimento> {
    return this.http.patch<Atendimento>(`${this.apiUrl}/${id}`, payload);
  }
}
