package br.com.alura.leilao.leiloes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

public class LeiloesTest {
	
	private LeiloesPage paginaDeLeiloes;
	
	@AfterEach //roda depois de CADA teste
	public void afterEach() {
		paginaDeLeiloes.fechar();
	}
	
	@Test 
	public void deveriaCadastrarLeilao() {
		LoginPage paginaDeLogin = new LoginPage();
		paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
		paginaDeLeiloes = paginaDeLogin.submeteFormualrioDeLogin(); //apos o login, o navegador eh redirecionado automaticamente para pagina de leiloes
		
		CadastroLeilaoPage paginaDeCadastroDeLeiloes = paginaDeLeiloes.carregaFormulario();		
	}
}