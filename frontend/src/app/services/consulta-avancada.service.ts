import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { ConsultaAvancadaResponse } from '../models/domain.models';

@Injectable({ providedIn: 'root' })
export class ConsultaAvancadaService {
  private readonly http = inject(HttpClient);

  executar(comando: string): Observable<ConsultaAvancadaResponse> {
    return this.http.post<ConsultaAvancadaResponse>(`${environment.apiUrl}/relatorios/consulta-avancada`, {
      comando,
    });
  }
}
