import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Conteiner } from 'src/app/interfaces/conteiner';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-deleteConteiner',
  templateUrl: './deleteConteiner.component.html',
  styleUrls: ['./deleteConteiner.component.css']
})
export class DeleteConteinerComponent implements OnInit {

  retorno!: Conteiner;
  form!: FormGroup;
  submitted = false;
  idConteiner: any;

  balaoRetornoCrud: boolean = false;
  mensagem = '';

  constructor(
    private dataService: DataService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) { 
    this.route.params.subscribe( (params) => {
      this.idConteiner = params['id']
    });
  }

  ngOnInit(): void {
    this.dataService.requestGetConteiner(this.idConteiner).subscribe( (data) => {
      this.retorno = data;
    });

    this.form = this.formBuilder.group(
      {
        id: ['', Validators.required],
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
    );
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onDelete(): void {
    
    this.dataService.requestDeleteConteiner(this.retorno.id).subscribe(
      resultado => {
        this.alerta("Exclusão realizada com sucesso!!!");
      },
      erro => {
        this.alerta(erro.error.message || erro.error.error);
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

  