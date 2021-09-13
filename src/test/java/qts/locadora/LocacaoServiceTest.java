package qts.locadora;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import qts.locador.exception.JogoSemEstoqueException;
import qts.locador.exception.PrecoInvalidoException;
import qts.locadora.service.LocacaoService;
import qts.locadora.util.DataUtil;

public class LocacaoServiceTest {	
	
	@Test
	public void teste() throws Exception{
		
		Cliente cliente = new Cliente("Antonio");
		Jogo jogo = new Jogo("Harry Potter", 15.00, 1);
		
		LocacaoService locacaoService = new LocacaoService();
		Locacao locacao;
		
		try {
			
			locacao = locacaoService.alugarJogo(cliente, jogo);
			
			Assert.assertTrue(locacao.getJogo().getNome().equals(jogo.getNome()));
			Assert.assertTrue(locacao.getCliente().getNome().equals(cliente.getNome()));
			Assert.assertTrue("Erro no valor do jogo", locacao.getValor()==jogo.getValor());
			Assert.assertTrue(new DataUtil().verificarDatasIguais(locacao.getRetirada(), new Date()));
		} 
		catch(Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test (expected = JogoSemEstoqueException.class)
	public void testSemEstoque() throws Exception {
	
	Cliente cliente = new Cliente("Antonio");
	Jogo jogo = new Jogo("Harry Potter", 15.00, 0);
	
	LocacaoService locacaoService = new LocacaoService();
	Locacao locacao;
	locacao = locacaoService.alugarJogo(cliente, jogo);
	}
	
	@Test (expected = PrecoInvalidoException.class)
	public void testPrecoValido() throws Exception{
		
	Cliente cliente = new Cliente("Antonio");
	Jogo jogo = new Jogo("Harry Potter", -4.00, 1);
	
	LocacaoService locacaoService = new LocacaoService();
	Locacao locacao;
	locacao = locacaoService.alugarJogo(cliente, jogo);
	}

}
