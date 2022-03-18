package br.com.alura.leilao.leiloes;

import org.openqa.selenium.WebDriver;

public class CadastroLeilaoPage {

	private WebDriver browser;
	
	public CadastroLeilaoPage(WebDriver browser) {
		this.browser = browser;
	}

	public void fechar() {
		//fecha o navegador
		this.browser.quit();
	}
}
