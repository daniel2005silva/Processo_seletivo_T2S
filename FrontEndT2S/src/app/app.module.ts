import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { ConteinerComponent } from './crud/conteiner/conteiner.component';
import { CreateConteinerComponent } from './crud/conteiner/create-conteiner/createConteiner.component';
import { DeleteConteinerComponent } from './crud/conteiner/delete-conteiner/deleteConteiner.component';
import { EditConteinerComponent } from './crud/conteiner/edit-conteiner/editConteiner.component';
import { ReadConteinerComponent } from './crud/conteiner/read-conteiner/readConteiner.component';

import { MovimentacaoComponent } from './crud/movimentacao/movimentacao.component';
import { CreateMovimentacaoComponent } from './crud/movimentacao/create-movimentacao/createMovimentacao.component';
import { DeleteMovimentacaoComponent } from './crud/movimentacao/delete-movimentacao/deleteMovimentacao.component';
import { EditMovimentacaoComponent } from './crud/movimentacao/edit-movimentacao/editMovimentacao.component';
import { ReadMovimentacaoComponent } from './crud/movimentacao/read-movimentacao/readMovimentacao.component';
import { RelatoriosComponent } from './relatorios/relatorios.component';
import { DataEHoraPipe } from './pipes/data-e-hora.pipe';

@NgModule({
  declarations: [
    AppComponent,
    ConteinerComponent,
    CreateConteinerComponent,
    DeleteConteinerComponent,
    EditConteinerComponent,
    ReadConteinerComponent,
    MovimentacaoComponent,
    CreateMovimentacaoComponent,
    DeleteMovimentacaoComponent,
    EditMovimentacaoComponent,
    ReadMovimentacaoComponent,
    RelatoriosComponent,
    DataEHoraPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [
    DataEHoraPipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
