import { Component, OnInit } from '@angular/core';
import { MovByClienteByTipo } from '../interfaces/mov-by-cliente-by-tipo';
import { DataService } from '../services/data.service';

@Component({
  selector: 'app-relatorios',
  templateUrl: './relatorios.component.html',
  styleUrls: ['./relatorios.component.css']
})
export class RelatoriosComponent implements OnInit {

  mov_porClient_porTpMov_s: MovByClienteByTipo[] = [];

  totalImportacao: number = 0;
  totalExportacao: number = 0;

  constructor(
    private dataService: DataService
  ) { }

  ngOnInit(): void {
    this.allMovimentacoes();

    this.dataService.getTotalExportImport('EXPORTAÇÃO').subscribe((data) => {
      this.totalExportacao = data
    });

    this.dataService.getTotalExportImport('IMPORTAÇÃO').subscribe((data) => {
      this.totalImportacao = data
    })
  }

  allMovimentacoes(){
    this.dataService.getMovByClienteByTpMov().subscribe((data) => {
      this.mov_porClient_porTpMov_s = data
    })
  }

  
}
