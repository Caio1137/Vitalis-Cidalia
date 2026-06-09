import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { DashboardResumo } from '../models/domain.models';

@Injectable({ providedIn: 'root' })
export class DashboardService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = environment.apiUrl;

  carregar(): Observable<DashboardResumo> {
    return this.http.get<DashboardResumo>(`${this.apiUrl}/dashboard`);
  }
}
