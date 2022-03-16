package br.com.alura.leilao;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
	
	private String urlLogin = "http://localhost:8080/login";
	private WebDriver browser;
	
	@BeforeAll //roda antes de TODOS os testes
	public void beforeAll() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
	}
	
	@BeforeEach //roda antes de CADA teste
	public void beforeEach() {
		this.browser = new ChromeDriver();
		
		//abre a tela de login
		this.browser.navigate().to(urlLogin);
	}
	
	@AfterEach //roda depois de CADA teste
	public void afterEach() {
		//fecha o navegador
		this.browser.quit();
	}
	
	@Test
	public void deveriaEfetuarLoginComDadosValidos() {
		//digita o usuario e senha
		browser.findElement(By.id("username")).sendKeys("fulano");
		browser.findElement(By.id("password")).sendKeys("pass");
		
		//da sumbit na tag form (efetua a requisicao para o backend)
		browser.findElement(By.id("login-form")).submit();
		
		//verifica se a url atual nao eh mais a url de login
		Assert.assertFalse(browser.getCurrentUrl().equals(urlLogin));
		
		//verifica se o nome do usuario logado na pagina é fulano
		Assert.assertTrue(browser.findElement(By.id("usuario-logado")).getText().equals("fulano"));
	}

	@Test
	public void naoDeveriaLogarComDadosInvalidos() { 
		//digita o usuario e senha
		browser.findElement(By.id("username")).sendKeys("invalido");
		browser.findElement(By.id("password")).sendKeys("pass");
		
		//da sumbit na tag form (efetua a requisicao para o backend)
		browser.findElement(By.id("login-form")).submit();
		
		//verifica se a url atual eh a tela de login
		Assert.assertFalse(browser.getCurrentUrl().equals(urlLogin.concat("?error")));
		
		//verifica se a mensagem de usuario invalido aparece na tela
		Assert.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
		
		//verifica se o nome do usuario logado esta vazio
		Assert.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")).getText().equals(""));
	}
}
