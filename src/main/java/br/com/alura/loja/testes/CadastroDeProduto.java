package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	
	public static void main(String[] args) {
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
