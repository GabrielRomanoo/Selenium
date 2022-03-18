package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {
	
	private LoginPage paginaDeLogin;
	
	@BeforeEach //roda antes de CADA teste
	public void beforeEach() {
		paginaDeLogin = new LoginPage();
	}
	
	@AfterEach //roda depois de CADA teste
	public void afterEach() {
		paginaDeLogin.fechar();
	}
	
	@Test
	public void deveriaEfetuarLoginComDadosValidos() {
		paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
		paginaDeLogin.submeteFormualrioDeLogin();
		
		//verifica se a url atual nao eh mais a url de login
		Assert.assertFalse(paginaDeLogin.isPaginaDeLogin());
		
		//verifica se o nome do usuario logado na pagina é fulano
		Assert.assertEquals("fulano", paginaDeLogin.getUsuarioLogado());
	}

	@Test
	public void naoDeveriaLogarComDadosInvalidos() { 
		paginaDeLogin.preencheFormularioDeLogin("invalido", "pass");
		paginaDeLogin.submeteFormualrioDeLogin();
		
		//verifica se a url atual eh a tela de login com erro
		Assert.assertTrue(paginaDeLogin.isPaginaDeLoginComDadosInvalidos());
		
		//verifica se a mensagem de usuario invalido aparece na tela
		Assert.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
		
		//verifica se o nome do usuario logado esta vazio
		Assert.assertNull(paginaDeLogin.getUsuarioLogado());
	}
	
	@Test
	public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
		//tenta acessar uma pagina de um leilao
		paginaDeLogin.navegaParaPaginaDeLances();
		
		//verifica se voltou pra pagina de login
		Assert.assertTrue(paginaDeLogin.isPaginaDeLogin());
		
		//verifica se nao tem o titulo de dados do leilao na pagina atual
		Assert.assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));
	}
	
}
