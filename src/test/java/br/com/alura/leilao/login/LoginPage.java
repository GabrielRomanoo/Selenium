package br.com.alura.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.alura.leilao.leiloes.LeiloesPage;

public class LoginPage {
	
	private String urlLogin = "http://localhost:8080/login";
	private WebDriver browser;
	
	public LoginPage() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		this.browser = new ChromeDriver();
		
		//abre a tela de login
		this.browser.navigate().to(urlLogin);
	}

	public void fechar() {
		//fecha o navegador
		this.browser.quit();
	}

	public void preencheFormularioDeLogin(String string, String string2) {
		//digita o usuario e senha
		browser.findElement(By.id("username")).sendKeys(string);
		browser.findElement(By.id("password")).sendKeys(string2);
	}

	public LeiloesPage submeteFormualrioDeLogin() {
		//da sumbit na tag form (efetua a requisicao para o backend
		browser.findElement(By.id("login-form")).submit();
		return new LeiloesPage(browser);
	}

	public String getUrlLogin() {
		return urlLogin;
	}

	public void setUrlLogin(String urlLogin) {
		this.urlLogin = urlLogin;
	}

	public WebDriver getBrowser() {
		return browser;
	}

	public void setBrowser(WebDriver browser) {
		this.browser = browser;
	}

	public boolean isPaginaDeLogin() {
		return browser.getCurrentUrl().equals(urlLogin);
	}

	public String getUsuarioLogado() {
		try {
			return browser.findElement(By.id("usuario-logado")).getText();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public void navegaParaPaginaDeLances() {
		this.browser.navigate().to("http://localhost:8080/leiloes/2");
	}

	public boolean contemTexto(String string) {
		return browser.getPageSource().contains(string);
	}

	public boolean isPaginaDeLoginComDadosInvalidos() {
		return browser.getCurrentUrl().equals(urlLogin + "?error");
	}
	
}
