package br.com.alura.leilao.leiloes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

public class LeiloesTest {
	
	private LeiloesPage paginaDeLeiloes;
	
	@AfterEach //roda depois de CADA teste
	public void afterEach() {
		this.paginaDeLeiloes.fechar();
	}
	
	@Test 
	public void deveriaCadastrarLeilao() {
		LoginPage paginaDeLogin = new LoginPage();
		paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
		this.paginaDeLeiloes = paginaDeLogin.submeteFormualrioDeLogin(); //apos o login, o navegador eh redirecionado automaticamente para pagina de leiloes
		
		CadastroLeilaoPage paginaDeCadastroDeLeiloes = paginaDeLeiloes.carregaFormulario();
		
		String hoje = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String nome = "Lance do dia " + hoje;
		BigDecimal valorInicial = new BigDecimal("500.00");
		
		this.paginaDeLeiloes = paginaDeCadastroDeLeiloes.cadastrarLeilao(nome, valorInicial.toString(), hoje);
		Assert.assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nome, valorInicial.toString(), hoje));
	}
}