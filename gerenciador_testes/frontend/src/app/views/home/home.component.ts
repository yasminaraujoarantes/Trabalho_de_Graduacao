import { CasoDeTeste } from './../../interfaces/CasoDeTeste';
import { Component, OnInit } from '@angular/core';
import { TestadorService } from 'src/app/services/TestadorService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  casosDeTesteCasoDeTeste: Array<CasoDeTeste>;

  constructor(private service: TestadorService, private router: Router) { }

  ngOnInit() {
    this.service.buscarTodosCasosDeTeste().subscribe(response => {
      this.casosDeTesteCasoDeTeste = response;
    }, error => {
      console.log(error);
    });
  }

  // public novoCasoDeTeste() {
  //   this.router.navigate(['/novo']);
  // }

  public deletarCasoDeTeste(id: number) {
    this.service.deletarCasoDeTestePorId(id).subscribe(response => {
      this.router.navigate(['/']);
      console.log(response);
    }, error => {
      console.log(error);
    });
  }

public clonarCasoDeTeste(id: number) {
    this.service.clonarCasoDeTeste(id).subscribe(response => {
      window.location.reload();
    }, error => {
      console.log(error);
    });
  }
}
