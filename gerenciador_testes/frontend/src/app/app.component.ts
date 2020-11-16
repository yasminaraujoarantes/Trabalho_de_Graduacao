import { CasoDeTeste } from './interfaces/CasoDeTeste';
import { Component } from '@angular/core';
import { ItemTeste } from './interfaces/ItemTeste';
import { TestadorService } from './services/TestadorService';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  mostrarSpinner = false;

  count = 1;

  acoes: any[];
  acaoSelecionada: any;

  tiposBy: any[];
  tipoBySelecionado: any;

  casoDeTesteSelecionado: CasoDeTeste = new CasoDeTeste();
  testeSelecionado: ItemTeste = new ItemTeste();

  mensagemRetorno: any;

  constructor(private service: TestadorService) {

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

  selecionarAcao(acaoSelecionada) {
    this.testeSelecionado = new ItemTeste();
    this.tipoBySelecionado = null;
    this.testeSelecionado.action = acaoSelecionada.value;
  }

  selecionarTiposBy(tipoBySelecionado) {
    this.testeSelecionado.byType = tipoBySelecionado.value;
  }

  adicionarTeste() {

    if (!this.casoDeTesteSelecionado.testes) {
      this.casoDeTesteSelecionado.testes = new Array<ItemTeste>();
    }

    this.testeSelecionado.id = this.count++;
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

}
