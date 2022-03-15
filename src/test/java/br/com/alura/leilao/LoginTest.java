package br.com.alura.leilao;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
	
	private String urlLogin = "http://localhost:8080/login";
	
	@Test
	public void deveriaEfetuarLoginComDadosValidos() {
		//abre a tela de login
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver browser = new ChromeDriver();
		browser.navigate().to(urlLogin);
		
		//digita o usuario e senha
		browser.findElement(By.id("username")).sendKeys("fulano");
		browser.findElement(By.id("password")).sendKeys("pass");
		
		//da sumbit na tag form (efetua a requisicao para o backend)
		browser.findElement(By.id("login-form")).submit();
		
		//verifica se a url atual nao eh mais a url de login
		Assert.assertFalse(browser.getCurrentUrl().equals(urlLogin));
		
		//verifica se o nome do usuario logado na pagina é fulano
		Assert.assertTrue(browser.findElement(By.id("usuario-logado")).getText().equals("fulano"));
		
		//fecha o navegador
		browser.quit();
	}

}
