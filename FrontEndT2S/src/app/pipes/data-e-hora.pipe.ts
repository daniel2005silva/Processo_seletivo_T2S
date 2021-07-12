import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dataEHora'
})
export class DataEHoraPipe implements PipeTransform {

  arrayAux!: string[];
  data!: string;
  hora!: string;

  transform(valor: string): string {
    this.arrayAux = valor.split("");
    
    this.data = this.arrayAux[8] + this.arrayAux[9] + '/' + this.arrayAux[5] + this.arrayAux[6]
                + '/' + this.arrayAux[0] + this.arrayAux[1] + this.arrayAux[2] + this.arrayAux[3];
    
     this.hora = this.arrayAux[11] + this.arrayAux[12] + this.arrayAux[13] 
                + this.arrayAux[14] + this.arrayAux[15] + this.arrayAux[16]
                + this.arrayAux[17] + this.arrayAux[18];
    
    return this.data + " - " + this.hora;
  }

}
