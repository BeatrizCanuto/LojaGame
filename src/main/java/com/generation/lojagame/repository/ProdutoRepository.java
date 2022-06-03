package com.generation.lojagame.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.lojagame.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>   {
	
	
	public List<Produto> findAllByNomeContainingIgnoreCase (@Param("nome") String nome);
	
	public List<Produto> findAllByPrecoLessThanEqualOrderByPreco (BigDecimal preco);
	
	public List<Produto> findAllByPrecoGreaterThanEqualOrderByPreco (BigDecimal preco);
	
	public List<Produto> findAllByQuantidadeLessThanEqualOrderByQuantidade (int quantidade);
	
	public List<Produto> findAllByQuantidadeGreaterThanEqualOrderByQuantidade (int quantidade);
	
	
	
	
}
