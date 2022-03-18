package br.com.alura.leilao.leiloes;

import org.openqa.selenium.WebDriver;

public class LeiloesPage {

	private String urlCadastroLeiloes = "http://localhost:8080/leiloes/new" ;
	private WebDriver browser;
	
	public LeiloesPage(WebDriver browser) {
		this.browser = browser;
	}

	public void fechar() {
		//fecha o navegador
		this.browser.quit();
	}

	public CadastroLeilaoPage carregaFormulario() {
		browser.navigate().to(urlCadastroLeiloes);
		return new CadastroLeilaoPage(browser);
	}
}
