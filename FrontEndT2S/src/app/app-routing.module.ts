import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ConteinerComponent } from './crud/conteiner/conteiner.component';
import { CreateConteinerComponent } from './crud/conteiner/create-conteiner/createConteiner.component';
import { DeleteConteinerComponent } from './crud/conteiner/delete-conteiner/deleteConteiner.component';
import { EditConteinerComponent } from './crud/conteiner/edit-conteiner/editConteiner.component';
import { ReadConteinerComponent } from './crud/conteiner/read-conteiner/readConteiner.component';
import { CreateMovimentacaoComponent } from './crud/movimentacao/create-movimentacao/createMovimentacao.component';
import { DeleteMovimentacaoComponent } from './crud/movimentacao/delete-movimentacao/deleteMovimentacao.component';
import { EditMovimentacaoComponent } from './crud/movimentacao/edit-movimentacao/editMovimentacao.component';
import { MovimentacaoComponent } from './crud/movimentacao/movimentacao.component';
import { ReadMovimentacaoComponent } from './crud/movimentacao/read-movimentacao/readMovimentacao.component';
import { RelatoriosComponent } from './relatorios/relatorios.component';

const routes: Routes = [
  { path: '', redirectTo: 'conteiner', pathMatch: 'full' },
  { path: 'conteiner', component: ConteinerComponent},
  { path: 'conteiner/create', component: CreateConteinerComponent},
  { path: 'conteiner/edit/:id', component: EditConteinerComponent},
  { path: 'conteiner/read/:id', component: ReadConteinerComponent},
  { path: 'conteiner/delete/:id', component: DeleteConteinerComponent},
  { path: 'movimentacao', component: MovimentacaoComponent},
  { path: 'movimentacao/create', component: CreateMovimentacaoComponent},
  { path: 'movimentacao/edit/:id', component: EditMovimentacaoComponent},
  { path: 'movimentacao/read/:id', component: ReadMovimentacaoComponent},
  { path: 'movimentacao/delete/:id', component: DeleteMovimentacaoComponent},
  { path: 'relatorios', component: RelatoriosComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
