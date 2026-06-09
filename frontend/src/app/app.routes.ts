import { Routes } from '@angular/router';
import { AtendimentosComponent } from './pages/atendimentos/atendimentos.component';
import { ConsultaAvancadaComponent } from './pages/consulta-avancada/consulta-avancada.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { OcorrenciasComponent } from './pages/ocorrencias/ocorrencias.component';
import { RecursosComponent } from './pages/recursos/recursos.component';

export const routes: Routes = [
  { path: '', component: DashboardComponent, title: 'Vitalis Cidalia' },
  { path: 'ocorrencias', component: OcorrenciasComponent, title: 'Ocorrencias' },
  { path: 'recursos', component: RecursosComponent, title: 'Recursos' },
  { path: 'atendimentos', component: AtendimentosComponent, title: 'Atendimentos' },
  { path: 'consulta-avancada', component: ConsultaAvancadaComponent, title: 'Consulta avancada' },
  { path: '**', redirectTo: '' },
];
