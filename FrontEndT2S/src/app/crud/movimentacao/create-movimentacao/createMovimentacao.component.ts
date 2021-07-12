import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Conteiner } from 'src/app/interfaces/conteiner';
import { Movimentacao } from 'src/app/interfaces/movimentacao';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-createMovimentacao',
  templateUrl: './createMovimentacao.component.html',
  styleUrls: ['./createMovimentacao.component.css']
})
export class CreateMovimentacaoComponent implements OnInit {

  conteiners: Conteiner[] = [];
  form!: FormGroup;
  submitted = false;

  balaoRetornoCrud: boolean = false;
  mensagem = '';

  constructor(
    private dataService: DataService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {

    this.dataService.getAllConteiners().subscribe( (data) => {
      this.conteiners = data;
    });

    this.form = this.formBuilder.group(
      {
        idConteiner: ['', Validators.required],
        tipo: ['', Validators.required],
        dt_inicio: ['', Validators.required],
        hr_inicio: ['', Validators.required],
        dt_fim: ['', Validators.required],
        hr_fim: ['', Validators.required]
      }
    )
  }

  get f(): { [key: string]: AbstractControl }{
    return this.form.controls;
  }

  onSubmit(): void {
    this.submitted = true;
    
    if(this.form.invalid){
      return;
    }

    this.form.value.dt_hr_inicio = this.form.value.dt_inicio + "T" + this.form.value.hr_inicio;
    this.form.value.dt_hr_fim = this.form.value.dt_fim + "T" + this.form.value.hr_fim;
    this.form.value.conteiner = { id : this.form.value.idConteiner };
    
    this.dataService.requestPostMovimentacao(this.form.value).subscribe(
      resultado => {
        this.alerta("Cadastro realizado com sucesso!!!");
      },
      erro => {
        this.alerta(erro.error.message);
      }
    );
  }

  onReset(): void {
    this.submitted = false;
    this.form.reset();
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
        window.location.href = "movimentacao";
      }
    }, 4000);
  }
}
