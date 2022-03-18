package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

	public boolean isLeilaoCadastrado(String nome, String valorInicial, String dataAbertura) {
		WebElement linhaTabela = browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
		WebElement colunaNome = linhaTabela.findElement(By.cssSelector("td:nth-child(1)"));
		WebElement colunaDataAbertura = linhaTabela.findElement(By.cssSelector("td:nth-child(2)"));
		WebElement colunaValorInicial = linhaTabela.findElement(By.cssSelector("td:nth-child(3)"));
		
		return colunaNome.getText().equals(nome)
				&& colunaDataAbertura.getText().equals(dataAbertura)
				&& colunaValorInicial.getText().equals(valorInicial); 
	}
}
