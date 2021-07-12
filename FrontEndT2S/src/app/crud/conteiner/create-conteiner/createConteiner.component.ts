import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-createConteiner',
  templateUrl: './createConteiner.component.html',
  styleUrls: ['./createConteiner.component.css']
})
export class CreateConteinerComponent implements OnInit {

  form!: FormGroup;
  submitted = false;

  balaoRetornoCrud: boolean = false;
  mensagem = '';

  constructor(
    private dataService: DataService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {

    this.form = this.formBuilder.group(
      {
        cliente: ['', Validators.required],
        num_container: ['',[
                              Validators.required,
                              Validators.pattern("[A-Z]{4}[0-9]{7}$")
                           ]
                      ],
        categoria: ['', Validators.required],
        status: ['', Validators.required],
        tipo: ['', Validators.required]
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

    this.dataService.requestPostConteiner(this.form.value).subscribe(
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
        window.location.href = "conteiner";
      }
    }, 4000);
  }
}
