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

public class CadastroDePedido {

	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao pDao = new ProdutoDao(em);
		Produto prod = pDao.buscarPorId(1l);
		
		ClienteDao cliDao = new ClienteDao(em);
		Cliente cli = cliDao.buscarPorId(1l);

		em.getTransaction().begin();
		
		Pedido p = new Pedido(cli);
		p.adicionarItem(new ItemPedido(10, p, prod));
		PedidoDao pedDao = new PedidoDao(em);
		pedDao.cadastrar(p);
		
		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedDao.valorTotalVendido();
		System.out.println("Valor total: " + totalVendido);
	}
	
	private static void popularBancoDeDados() {
		EntityManager em = JPAUtil.getEntityManager();
		
		Cliente cliente = new Cliente("Willian","123456");
		ClienteDao cliDao = new ClienteDao(em);
		
		Categoria cat = new Categoria("Celulares");
		CategoriaDao cDao = new CategoriaDao(em);
		
		Produto p = new Produto("Xiaomi Redmi", "Note 8 Pro", new BigDecimal("800"), cat );
		ProdutoDao pDao = new ProdutoDao(em);
		
		em.getTransaction().begin();
		
		cDao.cadastrar(cat);
		pDao.cadastrar(p);
		cliDao.cadastrar(cliente);
		
		em.getTransaction().commit();
		em.close();
	}

}
