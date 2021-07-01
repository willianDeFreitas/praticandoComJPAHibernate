package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class PerformanceConsultas {

	public static void main(String[] args) {
		popularBancoDeDados();
		cadastraPedido();
		EntityManager em = JPAUtil.getEntityManager();
		PedidoDao pedidoDao = new PedidoDao(em);
		Pedido pedido = pedidoDao.buscarPedidoComCliente(1l);
		em.close();
		System.out.println(pedido.getCliente().getNome());
	}
	
	private static void popularBancoDeDados() {
		EntityManager em = JPAUtil.getEntityManager();
		
		Cliente cliente = new Cliente("Willian","123456");
		ClienteDao cliDao = new ClienteDao(em);
		CategoriaDao cDao = new CategoriaDao(em);
		
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");
		
		Produto p1 = new Produto("Xiaomi Redmi", "Note 8 Pro", new BigDecimal("800"), celulares );
		Produto p2 = new Produto("PS5", "Playstation 5", new BigDecimal("5000"), videogames );
		Produto p3 = new Produto("Macbook", "Macboo Pro", new BigDecimal("8000"), informatica );
		
		ProdutoDao pDao = new ProdutoDao(em);
		
		em.getTransaction().begin();
		
		cliDao.cadastrar(cliente);

		cDao.cadastrar(celulares);
		cDao.cadastrar(videogames);
		cDao.cadastrar(informatica);
		
		pDao.cadastrar(p1);
		pDao.cadastrar(p2);
		pDao.cadastrar(p3);
		
		em.getTransaction().commit();
		em.close();
		
	}

	private static void cadastraPedido() {
		EntityManager em = JPAUtil.getEntityManager();
		ClienteDao cliDao = new ClienteDao(em);
		Cliente cli = cliDao.buscarPorId(1l);
		
		ProdutoDao pDao = new ProdutoDao(em);
		Produto prod1 = pDao.buscarPorId(1l);
		Produto prod2 = pDao.buscarPorId(2l);
		Produto prod3 = pDao.buscarPorId(3l);

		em.getTransaction().begin();
		
		Pedido ped1 = new Pedido(cli);
		ped1.adicionarItem(new ItemPedido(10, ped1, prod1));
		ped1.adicionarItem(new ItemPedido(40, ped1, prod2));

		Pedido ped2 = new Pedido(cli);
		ped2.adicionarItem(new ItemPedido(2, ped2, prod3));
		
		PedidoDao pedDao = new PedidoDao(em);
		pedDao.cadastrar(ped1);
		pedDao.cadastrar(ped2);
		
		em.getTransaction().commit();
		em.close();
	}

}
