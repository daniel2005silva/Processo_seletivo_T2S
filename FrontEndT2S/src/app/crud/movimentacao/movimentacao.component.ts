import { Component, OnInit } from '@angular/core';
import { Movimentacao } from 'src/app/interfaces/movimentacao';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-movimentacao',
  templateUrl: './movimentacao.component.html',
  styleUrls: ['./movimentacao.component.css']
})
export class MovimentacaoComponent implements OnInit {

  movimentacoes: Movimentacao[] = [];

  cliente: string = '';
  conteiner: string = '';
  tipo: string = '';
  dtInicio: string = '';
  dtFim: string = '';

  constructor(
    private dataService: DataService
  ) { }

  ngOnInit(): void {
    this.allMovimentacoes();
  }

  allMovimentacoes(){
    this.dataService.getAllMovimentacoes().subscribe((data) => {
      this.movimentacoes = data;
    })
  }

  filtrar(){
    this.dataService.getAllMovimentacoesFiltrado(
      this.cliente, this.conteiner, this.tipo, this.dtInicio, this.dtFim
    ).subscribe((data) => {
      this.movimentacoes = data
    })
  }
}
