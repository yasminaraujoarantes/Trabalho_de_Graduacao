import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { throwError, Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { CasoDeTeste } from '../interfaces/CasoDeTeste';

@Injectable({
    providedIn: 'root'
})

export class TestadorService {

    private apiUrl = 'http://localhost:8080/api/';

    constructor(private http: HttpClient) { }

    public buscarTodosCasosDeTeste(): Observable<any> {
        return this.http.get(`${this.apiUrl}/buscarTodosCasosDeTeste`).pipe(
            catchError(e => {
                return throwError(e);
            })
        );
    }

    public buscarCasoDeTeste(id): Observable<CasoDeTeste> {
        return this.http.get<CasoDeTeste>(`${this.apiUrl}/visualizar/${id}`).pipe(
            catchError(e => {
                return throwError(e);
            })
        );
    }

    public deletarCasoDeTestePorId(id: number): Observable<any> {
        return this.http.delete<CasoDeTeste>(`${this.apiUrl}/deletarCasoDeTestePorId/${id}`).pipe(
            catchError(e => {
                return throwError(e);
            })
        );
    }

    public inserirCasoDeTestes(casoDeTeste: CasoDeTeste) {
        return this.http.post(`${this.apiUrl}/inserir`, casoDeTeste).pipe(
            catchError(e => {
                return throwError(e);
            })
        );
    }

    public editarCasoDeTestes(casoDeTeste: CasoDeTeste) {
        return this.http.put<CasoDeTeste>(`${this.apiUrl}/editar/${casoDeTeste.id}`, casoDeTeste).pipe(
          catchError(e => {
            return throwError(e);
          })
        );
      }

    public executarTestes(casoDeTeste: CasoDeTeste) {
        return this.http.post(`${this.apiUrl}/executar_testes`, casoDeTeste).pipe();
    }
}