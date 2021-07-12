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

  
}
