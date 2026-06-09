import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Ocorrencia } from '../models/domain.models';

@Injectable({ providedIn: 'root' })
export class OcorrenciaService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = `${environment.apiUrl}/ocorrencias`;

  listar(filtros: Record<string, string | null | undefined> = {}): Observable<Ocorrencia[]> {
    let params = new HttpParams();
    Object.entries(filtros).forEach(([key, value]) => {
      if (value) {
        params = params.set(key, value);
      }
    });
    return this.http.get<Ocorrencia[]>(this.apiUrl, { params });
  }

  criar(payload: unknown): Observable<Ocorrencia> {
    return this.http.post<Ocorrencia>(this.apiUrl, payload);
  }

  atualizarStatus(id: number, status: string): Observable<Ocorrencia> {
    return this.http.patch<Ocorrencia>(`${this.apiUrl}/${id}/status`, { status });
  }
}
