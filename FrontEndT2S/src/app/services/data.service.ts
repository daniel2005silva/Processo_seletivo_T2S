import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Conteiner } from '../interfaces/conteiner';
import { Movimentacao } from '../interfaces/movimentacao';
import { MovByClienteByTipo } from '../interfaces/mov-by-cliente-by-tipo';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private urlAPI = 'http://localhost:8080/';
  private headers: HttpHeaders;
  private params!: HttpParams;

  constructor(private http: HttpClient) {
    this.headers = new HttpHeaders({
      'Content-type': 'application/json',
      Accept: 'application/json, text/plain, */*'
    });
  }

  getAllConteiners(): Observable<Conteiner[]>{
    const options = { headers: this.headers };

    return this.http.get<Conteiner[]>(
      this.urlAPI + 'conteiner', options
    );
  }

  requestPostConteiner(corpo: any){
    const options = { headers: this.headers };

    return this.http.post(
      this.urlAPI + 'conteiner', corpo, options
    );
  }

  requestGetConteiner(id: number): Observable<Conteiner>{
    const options = { headers: this.headers };

    return this.http.get<Conteiner>(
      this.urlAPI + 'conteiner/' + id,
      options
    );
  }

  requestEditConteiner(corpo: Conteiner, id: number){
    const options = { headers: this.headers };

    return this.http
    .put(
       this.urlAPI + 'conteiner/' + id,
       corpo,
       options
    );
  }

  requestDeleteConteiner(id: number){
    const options = { headers: this.headers };

    return this.http
    .delete(
       this.urlAPI + 'conteiner/' + id,
       options
    );
  }

  getAllConteinersFiltrado(
    cliente: string, num_container: string, tipo: string, status: string, categoria: string
    ): Observable<Conteiner[]>{
    const options = { headers: this.headers };
    let params = '?';

    if(cliente){
      params += 'cliente=' + cliente + '&';
    }
    if(num_container){
      params += 'num_container=' + num_container + '&';
    }
    if(tipo){
      params += 'tipo=' + tipo + '&';
    }
    if(status){
      params += 'status=' + status + '&';
    }
    if(categoria){
      params += 'categoria=' + categoria + '&';
    }
    
    return this.http.get<Conteiner[]>(
      this.urlAPI + 'conteiner/filtrar' + params, options
    );
  }






  getAllMovimentacoes(): Observable<Movimentacao[]>{
    const options = { headers: this.headers };

    return this.http.get<Movimentacao[]>(
      this.urlAPI + 'movimentacao', options
    );
  }

  requestPostMovimentacao(corpo: any){
    const options = { headers: this.headers };

    return this.http.post(
      this.urlAPI + 'movimentacao', corpo, options
    );
  }

  requestGetMovimentacao(id: number): Observable<Movimentacao>{
    const options = { headers: this.headers };

    return this.http.get<Movimentacao>(
      this.urlAPI + 'movimentacao/' + id,
      options
    );
  }

  requestEditMovimentacao(corpo: Movimentacao, id: number){
    const options = { headers: this.headers };

    return this.http
    .put(
       this.urlAPI + 'movimentacao/' + id,
       corpo,
       options
    );
  }

  requestDeleteMovimentacao(id: number){
    const options = { headers: this.headers };

    return this.http
    .delete(
       this.urlAPI + 'movimentacao/' + id,
       options
    );
  }

  getMovByClienteByTpMov(): Observable<MovByClienteByTipo[]>{
    const options = { headers: this.headers };

    return this.http.get<MovByClienteByTipo[]>(
      this.urlAPI + 'movimentacao/porClienteEporTipoMovimentacao', options
    );
  }

  getTotalExportImport(categoria: String): Observable<number> {
    const options = { headers: this.headers };

    return this.http.get<number>(
      this.urlAPI + 'conteiner/totalCategoria/' + categoria, options
    );
  }

  getAllMovimentacoesFiltrado(
    cliente: string, conteiner: string, tipo: string, dtIncio: string, dtFim: string
    ): Observable<Movimentacao[]>{
    const options = { headers: this.headers };
    let params = '?';

    if(cliente){
      params += 'cliente=' + cliente + '&';
    }
    if(conteiner){
      params += 'conteiner=' + conteiner + '&';
    }
    if(tipo){
      params += 'tipo=' + tipo + '&';
    }
    if(dtIncio){
      params += 'dtIncio=' + dtIncio + '&';
    }
    if(dtFim){
      params += 'dtFim=' + dtFim + '&';
    }
    console.log(params);
    return this.http.get<Movimentacao[]>(
      this.urlAPI + 'movimentacao/filtrar' + params, options
    );
  }
}