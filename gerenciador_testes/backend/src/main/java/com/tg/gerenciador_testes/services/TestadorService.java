package com.tg.gerenciador_testes.services;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.tg.gerenciador_testes.dto.CasoDeTesteDTO;
import com.tg.gerenciador_testes.dto.MensagemDTO;
import com.tg.gerenciador_testes.dto.TesteDTO;
import com.tg.gerenciador_testes.model.CasoDeTeste;
import com.tg.gerenciador_testes.model.Teste;
import com.tg.gerenciador_testes.repositories.CasoDeTesteRepository;
import com.tg.gerenciador_testes.repositories.TesteRepository;

@Service
public class TestadorService {
	
	private WebDriver driver;
	
	@Autowired
	private CasoDeTesteRepository repository;
	
	@Autowired
	private TesteRepository testeRepository;
	
	public void teste() {
		setUpChromeDriver();
		driver.get("https://google.com.br");
	}
	
	public void deletarCasoDeTestePorId(Long idCasoDeTeste) {
		repository.deleteById(idCasoDeTeste);
	}
	
	public List<CasoDeTesteDTO> buscarTodosCasosDeTeste() {
		return repository.buscarTodosCasosDeTeste();
	}
	
	public CasoDeTesteDTO detalharCasoDeTeste(Long idCasoDeTeste) throws Exception {
		CasoDeTeste casoDeTeste = buscarCasoDeTestePorId(idCasoDeTeste);
		
		CasoDeTesteDTO casoDeTesteDTO = new CasoDeTesteDTO();
		casoDeTesteDTO.setId(casoDeTeste.getId());
		casoDeTesteDTO.setNome(casoDeTeste.getNome());
		casoDeTesteDTO.setObjetivo(casoDeTeste.getObjetivo());
		
		List<TesteDTO> testes = new ArrayList<>();
		
		for (Teste teste : casoDeTeste.getTestes()) {
			TesteDTO testeDTO = new TesteDTO();
			testeDTO.setId(teste.getId());
			testeDTO.setAction(teste.getAction());
			testeDTO.setByType(teste.getByType());
			testeDTO.setElement(teste.getElement());
			testeDTO.setUrl(teste.getUrl());
			testeDTO.setTextInput(teste.getTextInput());
			testeDTO.setSaidaEsperada(teste.getSaidaEsperada());
			
			testes.add(testeDTO);
		}
		
		casoDeTesteDTO.setTestes(testes);
		
		return casoDeTesteDTO;
	}
	
	public CasoDeTeste buscarCasoDeTestePorId(Long idCasoDeTeste) throws Exception {
		Optional<CasoDeTeste> obj = repository.findById(idCasoDeTeste);
		return obj.orElseThrow(() -> new Exception("Não encontrado!"));
	}
	
	public Teste buscarTestePorId(Long idTeste) throws Exception {
		Optional<Teste> obj = testeRepository.findById(idTeste);
		return obj.orElseThrow(() -> new Exception("Não encontrado!"));
	}
	
	public CasoDeTeste inserir(CasoDeTesteDTO casoDeTesteDTO) {
		CasoDeTeste casoDeTesteParaInserir = new CasoDeTeste();
		
		casoDeTesteParaInserir.setNome(casoDeTesteDTO.getNome());
		casoDeTesteParaInserir.setObjetivo(casoDeTesteDTO.getObjetivo());
		casoDeTesteParaInserir.setTestes(new ArrayList<>());
		
		for (TesteDTO testeDTO : casoDeTesteDTO.getTestes()) {
			Teste testeParaInserir = new Teste();
			testeParaInserir.setAction(testeDTO.getAction());
			testeParaInserir.setByType(testeDTO.getByType());
			testeParaInserir.setElement(testeDTO.getElement());
			testeParaInserir.setUrl(testeDTO.getUrl());
			testeParaInserir.setTextInput(testeDTO.getTextInput());
			testeParaInserir.setSaidaEsperada(testeDTO.getSaidaEsperada());
			testeParaInserir.setCasoDeteste(casoDeTesteParaInserir);
			
			casoDeTesteParaInserir.getTestes().add(testeParaInserir);
		}
		
		return repository.save(casoDeTesteParaInserir);
	}
	
	public CasoDeTeste editar(CasoDeTesteDTO casoDeTesteDTO) throws Exception {
		CasoDeTeste casoDeTeste = buscarCasoDeTestePorId(casoDeTesteDTO.getId());
		
		casoDeTeste.setNome(casoDeTesteDTO.getNome());
		casoDeTeste.setObjetivo(casoDeTesteDTO.getObjetivo());
		List<Teste> testes = new ArrayList<>();
		
		for (TesteDTO testeDTO : casoDeTesteDTO.getTestes()) {
			
			Teste testeParaInserir = new Teste();
			if (testeDTO.getId() != null) {
				testeParaInserir = buscarTestePorId(testeDTO.getId());
			}
			
			testeParaInserir.setAction(testeDTO.getAction());
			testeParaInserir.setByType(testeDTO.getByType());
			testeParaInserir.setElement(testeDTO.getElement());
			testeParaInserir.setUrl(testeDTO.getUrl());
			testeParaInserir.setTextInput(testeDTO.getTextInput());
			testeParaInserir.setSaidaEsperada(testeDTO.getSaidaEsperada());
			testeParaInserir.setCasoDeteste(casoDeTeste);
			
			testes.add(testeParaInserir);
		}
		
		casoDeTeste.setTestes(testes);
		
		return repository.save(casoDeTeste);
	}

	public CasoDeTeste clonar(Long idCasoDeTeste) throws Exception {
       	 	CasoDeTeste casoDeTesteOriginal = buscarCasoDeTestePorId(idCasoDeTeste);
        	CasoDeTeste novoCasoDeTeste = new CasoDeTeste(null, casoDeTesteOriginal.getNome()+"(1)", casoDeTesteOriginal.getObjetivo(), null);

       		 List<Teste> testesClonados = new ArrayList<>();
        	for (Teste testeOriginal : casoDeTesteOriginal.getTestes()) {
         	    Teste testeNovo = new Teste(
                    null, testeOriginal.getAction(), testeOriginal.getByType(), 
                    testeOriginal.getElement(), testeOriginal.getUrl(), testeOriginal.getTextInput(), 
                    testeOriginal.getSaidaEsperada(), novoCasoDeTeste);
                    testesClonados.add(testeNovo);
        }

               novoCasoDeTeste.setTestes(testesClonados);
               repository.save(novoCasoDeTeste);
               return novoCasoDeTeste;
       }
	
	/**Método usado para configurar o driver do Google Chrome**/
	private void setUpChromeDriver() {
		ClassPathResource classPathResource = new ClassPathResource("chromedriver.exe");
		InputStream inputStream = null;
		try {
			inputStream = classPathResource.getInputStream();
			File geckodriverFile = File.createTempFile("chromedriver", ".exe"); ;
			FileOutputStream out = new FileOutputStream( geckodriverFile );
			IOUtils.copy(inputStream, out);
			System.err.println( geckodriverFile.getCanonicalPath());
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\yasmi\\Documents\\Faculdade\\curso de teste\\chromedriver.exe" );
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
	}
	
	/**Método que passa por todos os testes do caso de teste recebido pela api e executa**/
	public MensagemDTO executarTestes(CasoDeTesteDTO casoDeTeste) throws IOException {
		setUpChromeDriver();

		Long idTesteAtual = null;
		
		try {
			for (TesteDTO teste : casoDeTeste.getTestes()) {
				idTesteAtual = teste.getId();
				switch (teste.getAction()) {
				case "abrir_pagina":
					abrirPagina(teste);
					break;
				case "preencher_input":
					preencherInput(teste);
					break;
				case "clicar_botao":
					clicarBotao(teste);
					break;
				case "comparar_textos":
					compararTextos(teste);
					break;
				case "aceitar_alerta":
					aceitarAlerta();
					break;
				case "negar_alerta":
					negarAlerta();
					break;
				case "comparar_texto_alerta":
					compararTextoAlerta(teste);
					break;
				default:
					break;
				}

			}
			MensagemDTO mensagem = new MensagemDTO();
			mensagem.setTipo("Sucesso");
			mensagem.setTitulo("Testes executados com sucesso");
			return mensagem;
			
		} catch (Exception | AssertionError e) {
			return tratarErro(idTesteAtual, e.getMessage());
		}

	}
	
	/** Método usado para montar a mensagem de falha dos testes**/
	public MensagemDTO tratarErro(Long idTesteAtual, String erroMsg) throws IOException{
		
		/*Salva imagem da tela onde ocorreu o erro*/
//		TakesScreenshot ss = (TakesScreenshot) driver;
//		File arquivo = ss.getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(arquivo, new File("target" + File.separator + "screenshot" +
//				File.separator + "testeeErroo" + ".jpg"));
		
//		return "Falha no teste de ID"+idTesteAtual+"| Motivo da falha: "+erroMsg;
		
		MensagemDTO mensagem = new MensagemDTO();
		mensagem.setTitulo("Falha no teste de ID"+idTesteAtual);
		mensagem.setDescricao(erroMsg);
		mensagem.setTipo("Erro");
		return mensagem;
	}
	
	/** Retorna o by de acordo com o que foi definido para o teste recebido como parâmetro**/
	private By retornarElemento(TesteDTO teste) {
		
		By element = null;
		
		if ("id".equals(teste.getByType())) {
			element = By.id(teste.getElement());

		}else if ("xpath".equals(teste.getByType())) {
			element = By.xpath(teste.getElement());
			
		}else if ("className".equals(teste.getByType())) {
			element = By.className(teste.getElement());
			
		}else if ("name".equals(teste.getByType())) {
			element = By.name(teste.getElement());
		}

		return element;
	}
		
	/** Métodos referentes as ações do Selenium**/
	
	private void abrirPagina(TesteDTO teste) {
		driver.get(teste.getUrl());
	}
	
	private void clicarBotao(TesteDTO teste) {
		By element = retornarElemento(teste);
		driver.findElement(element).click();
	}
	
	public void preencherInput(TesteDTO teste){
		By element = retornarElemento(teste);
		
		driver.findElement(element).clear();
		driver.findElement(element).sendKeys(teste.getTextInput());
	}
	
	public void compararTextos(TesteDTO teste) {
		By element = retornarElemento(teste);
		String textoElemento = driver.findElement(element).getText();
		assertEquals(teste.getSaidaEsperada(), textoElemento);
	}
	
	public void aceitarAlerta(){
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public void negarAlerta(){
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}
	
	public void compararTextoAlerta(TesteDTO teste){
		Alert alert = driver.switchTo().alert();
		String textoAlerta = alert.getText();
		assertEquals(teste.getSaidaEsperada(), textoAlerta);
	}

}
