import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Conteiner } from 'src/app/interfaces/conteiner';
import { Movimentacao } from 'src/app/interfaces/movimentacao';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-deleteMovimentacao',
  templateUrl: './deleteMovimentacao.component.html',
  styleUrls: ['./deleteMovimentacao.component.css']
})
export class DeleteMovimentacaoComponent implements OnInit {

  retorno!: Movimentacao;

  dt_inicio_aux!: String;
  hr_inicio_aux!: String;
  dt_fim_aux!: String;
  hr_fim_aux!: String;
  conteiner_aux!: String;

  conteiners: Conteiner[] = [];
  form!: FormGroup;
  submitted = false;
  idMovimentacao: any;

  balaoRetornoCrud: boolean = false;
  mensagem = '';

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
      
      this.dt_inicio_aux = this.retorno.dt_hr_inicio.split("T")[0];
      this.hr_inicio_aux = this.retorno.dt_hr_inicio.split("T")[1].split(".")[0];
      this.dt_fim_aux = this.retorno.dt_hr_fim?.split("T")[0];
      this.hr_fim_aux = this.retorno.dt_hr_fim?.split("T")[1].split(".")[0];
      
      this.conteiner_aux = this.retorno.conteiner.cliente + " - " + this.retorno.conteiner.num_container;
    });

    this.dataService.getAllConteiners().subscribe( (data) => {
      this.conteiners = data;
    });

    this.form = this.formBuilder.group(
      {
        conteiner: ['', Validators.required],
        tipo: ['', Validators.required],
        dt_inicio: ['', Validators.required],
        hr_inicio: ['', Validators.required],
        dt_fim: ['', Validators.required],
        hr_fim: ['', Validators.required]
      }
    );
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onDelete(): void {
    
    this.dataService.requestDeleteMovimentacao(this.retorno.id).subscribe(
      resultado => {
        this.alerta("Exclusão realizada com sucesso!!!");
      },
      erro => {
        this.alerta(erro.error.message || erro.error.error);
      }
      );

    
    }
  
    alerta(aviso: string){
      this.balaoRetornoCrud = true;
      this.mensagem = aviso;
      setTimeout( () => {
        this.balaoRetornoCrud = false;
        if(
          aviso === "Cadastro realizado com sucesso!!!"
          ||
          aviso === "Edição realizada com sucesso!!!"
          ||
          aviso === "Exclusão realizada com sucesso!!!"
        ){
          window.location.href = "../movimentacao";
        }
      }, 4000);
    }
  }