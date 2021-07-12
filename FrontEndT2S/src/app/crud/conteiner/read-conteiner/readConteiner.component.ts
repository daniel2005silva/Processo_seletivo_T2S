import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Conteiner } from 'src/app/interfaces/conteiner';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-readConteiner',
  templateUrl: './readConteiner.component.html',
  styleUrls: ['./readConteiner.component.css']
})
export class ReadConteinerComponent implements OnInit {
  
  retorno!: Conteiner;
  form!: FormGroup;
  submitted = false;
  idConteiner: any;

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


  
    
    

    
  }

  


