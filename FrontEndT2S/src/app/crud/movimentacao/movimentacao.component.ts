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
}
