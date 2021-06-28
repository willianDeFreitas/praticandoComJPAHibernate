package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProdutoEConsulta {
	
	public static void main(String[] args) {
		cadastrarProduto();
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao pDao = new ProdutoDao(em);
		Produto p = pDao.buscarPorId(1l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = pDao.buscarTodos();
		todos.forEach(p2 -> System.out.println(p2.getNome()));
		
		List<Produto> porNome = pDao.buscarPorNome("Xiaomi Redmi");
		porNome.forEach(p2 -> System.out.println(p2.getNome()));
		
		List<Produto> porCategoria = pDao.buscarPorNomeCategoria("Celulares");
		porCategoria.forEach(p2 -> System.out.println(p2.getNome()));
		
		BigDecimal precoDoProduto = pDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
		System.out.println("Preço do produto é: " + precoDoProduto);
		
	}

	private static void cadastrarProduto() {
		EntityManager em = JPAUtil.getEntityManager();
		
		Categoria cat = new Categoria("Celulares");
		CategoriaDao cDao = new CategoriaDao(em);
		
		Produto p = new Produto("Xiaomi Redmi", "Note 8 Pro", new BigDecimal("800"), cat );
		ProdutoDao pDao = new ProdutoDao(em);
		
		em.getTransaction().begin();
		cDao.cadastrar(cat);
		pDao.cadastrar(p);
		em.getTransaction().commit();
		em.close();
	}

}
