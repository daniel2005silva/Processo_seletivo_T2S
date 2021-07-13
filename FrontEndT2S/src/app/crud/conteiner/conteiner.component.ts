import { Component, OnInit } from '@angular/core';
import { Conteiner } from 'src/app/interfaces/conteiner';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-conteiner',
  templateUrl: './conteiner.component.html',
  styleUrls: ['./conteiner.component.css']
})
export class ConteinerComponent implements OnInit {

  conteiners: Conteiner[] = [];

  cliente: string = '';
  num_container: string = '';
  tipo: string = '';
  status: string = '';
  categoria: string = '';

  constructor(
    private dataService: DataService
  ) { }

  ngOnInit(): void {
    this.allConteiners();
  }

  allConteiners(){
    this.dataService.getAllConteiners().subscribe((data) => {
      this.conteiners = data
    })
  }

  filtrar(){
    this.dataService.getAllConteinersFiltrado(
      this.cliente, this.num_container, this.tipo, this.status, this.categoria
    ).subscribe((data) => {
      this.conteiners = data
    })
  }
  
}
