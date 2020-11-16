import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CasoDeTeste } from 'src/app/interfaces/CasoDeTeste';
import { ItemTeste } from 'src/app/interfaces/ItemTeste';
import { TestadorService } from 'src/app/services/TestadorService';

@Component({
  selector: 'app-detalhe',
  templateUrl: './detalhe.component.html',
  styleUrls: ['./detalhe.component.css']
})
export class DetalheComponent implements OnInit {

  podeExecutar = false;

  mostrarSpinner = false;

  acoes: any[];
  acaoSelecionada: any;

  tiposBy: any[];
  tipoBySelecionado: any;

  casoDeTesteSelecionado: CasoDeTeste = new CasoDeTeste();
  testeSelecionado: ItemTeste = new ItemTeste();

  mensagemRetorno: any;

  constructor(private service: TestadorService, private router: Router, public activatedRoute: ActivatedRoute) {

    this.acoes = [
      { name: 'Abrir página', value: 'abrir_pagina' },
      { name: 'Preencher campo', value: 'preencher_input' },
      { name: 'Clicar em botão', value: 'clicar_botao' },
      { name: 'Comparar textos', value: 'comparar_textos' },
      { name: 'Aceitar alerta', value: 'aceitar_alerta' },
      { name: 'Negar alerta', value: 'negar_alerta' },
      { name: 'Comparar texto do alerta', value: 'comparar_texto_alerta' },
    ];

    this.tiposBy = [
      { name: 'By id', value: 'id' },
      { name: 'By xpath', value: 'xpath' },
      { name: 'By name', value: 'name' },
      { name: 'By className', value: 'className' },
    ];
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      const id = params.id;
      if (id) {
        this.podeExecutar = true;
        this.service.buscarCasoDeTeste(id).subscribe(response => {
          this.casoDeTesteSelecionado = response;
        });
      }

    });
  }

  selecionarAcao(acaoSelecionada) {
    this.testeSelecionado = new ItemTeste();
    this.tipoBySelecionado = null;
    this.testeSelecionado.action = acaoSelecionada.value;
  }

  selecionarTiposBy(tipoBySelecionado) {
    this.testeSelecionado.byType = tipoBySelecionado.value;
  }

  adicionarTeste() {
    this.podeExecutar = false;
    if (!this.casoDeTesteSelecionado.testes) {
      this.casoDeTesteSelecionado.testes = new Array<ItemTeste>();
    }

    this.casoDeTesteSelecionado.testes.push(this.testeSelecionado);

    this.acaoSelecionada = null;
    this.testeSelecionado = new ItemTeste();
  }

  executarTestes() {
    this.mostrarSpinner = true;
    this.service.executarTestes(this.casoDeTesteSelecionado).subscribe(response => {
      this.mostrarSpinner = false;
      this.mensagemRetorno = response;
      console.log(response);
    }, error => {
      console.log(error);
    });
    console.log(this.casoDeTesteSelecionado);
  }

  excluirTeste(teste) {
    this.podeExecutar = false;
    this.casoDeTesteSelecionado.testes = this.casoDeTesteSelecionado.testes.filter(obj => obj !== teste);
  }

  prepararEdicaoTeste(teste) {
    this.testeSelecionado = teste;

    this.acoes.forEach(element => {
      if (element.value === teste.action) {
        this.acaoSelecionada = element;
      }
    });

    this.tiposBy.forEach(element => {
      if (element.value === teste.byType) {
        this.tipoBySelecionado = element;
      }
    });
  }

  salvarTeste() {
    this.casoDeTesteSelecionado.testes.forEach(element => {
      if (element.id === this.testeSelecionado.id) {
        element = this.testeSelecionado;
      }
    });
    this.acaoSelecionada = null;
    this.testeSelecionado = new ItemTeste();
  }

  salvarCasoDeTeste() {
    if (this.casoDeTesteSelecionado.id) {
      this.service.editarCasoDeTestes(this.casoDeTesteSelecionado).subscribe(response => {
        window.location.reload();
      });
    } else {
      this.service.inserirCasoDeTestes(this.casoDeTesteSelecionado).subscribe(response => {
        this.router.navigate(['/visualizar', response]);
      });
    }
  }

}
