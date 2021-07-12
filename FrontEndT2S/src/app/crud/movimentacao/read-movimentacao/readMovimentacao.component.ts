import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Movimentacao } from 'src/app/interfaces/movimentacao';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-readMovimentacao',
  templateUrl: './readMovimentacao.component.html',
  styleUrls: ['./readMovimentacao.component.css']
})
export class ReadMovimentacaoComponent implements OnInit {

  retorno!: Movimentacao;
  form!: FormGroup;
  submitted = false;
  idMovimentacao: any;

  constructor(
    private dataService: DataService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) { 
    this.route.params.subscribe( (params) => {
      this.idMovimentacao = params['id']
    });
  }

  ngOnInit(): void {
    this.dataService.requestGetMovimentacao(this.idMovimentacao).subscribe( (data) => {
      this.retorno = data;
    });

    this.form = this.formBuilder.group(
      {
        id: ['', Validators.required],
        cliente: ['', Validators.required],
        num_container: ['', Validators.required],
        categoria: ['', Validators.required],
        status: ['', Validators.required],
        tipoConteiner: ['', Validators.required],
        tipo: ['', Validators.required],
        dt_hr_inicio: ['', Validators.required],
        dt_hr_fim: ['', Validators.required]
      }
    );
  }


  
    
    

    
}
