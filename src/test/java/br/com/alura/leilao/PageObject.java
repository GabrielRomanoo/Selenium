package br.com.alura.leilao;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageObject {

	protected WebDriver browser;
	
	public PageObject(WebDriver browser) {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		
		if (browser == null) {
			this.browser = new ChromeDriver();
		} else {
			this.browser = browser;
		}
		
		//PARA SITUACOES ASSINCRONAS (ONDE O JAVASCRIPT/A TELA NAO CARREGOU A TEMPO)
		this.browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //pede para o selenium esperar 5 segundos caso nao encontre algum elemento para lancar o erro
		this.browser.manage().timeouts().pageLoadTimeout(10,  TimeUnit.SECONDS); //pede para esperar 10 segundos para a pagina carregar, caso nao carrega, o selenium lanca um erro
	}
	
	public void fechar() {
		//fecha o navegador
		this.browser.quit();
	}
}
