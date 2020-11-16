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

    public executarTestes(casoDeTeste: CasoDeTeste) {
        return this.http.post(`${this.apiUrl}/executar_testes`, casoDeTeste).pipe();
    }
}