package com.generation.lojagame.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.lojagame.model.Produto;
import com.generation.lojagame.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>>getAll(){
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity <Produto> GetById (@PathVariable long id) {
		return produtoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("nome/{nome}")
	public ResponseEntity <List<Produto>> GetByNome (@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	@GetMapping("preco_maior/{preco_maior}")
	public ResponseEntity <List<Produto>> GetByPreco_maior (@PathVariable BigDecimal preco){
		return ResponseEntity.ok(produtoRepository.findAllByPrecoGreaterThanEqualOrderByPreco(preco));
	}
	@GetMapping("preco_menor/{preco_menor}")
	public ResponseEntity <List<Produto>> GetByPreco_menor (@PathVariable BigDecimal preco){
		return ResponseEntity.ok(produtoRepository.findAllByPrecoLessThanEqualOrderByPreco(preco));
	}
	@GetMapping("quantidade_menor/{quantidade_menor}")
	public ResponseEntity <List<Produto>> GetByQuantidade_menor (@PathVariable int quantidade){
		return ResponseEntity.ok(produtoRepository.findAllByQuantidadeLessThanEqualOrderByQuantidade(quantidade));
	}
	@GetMapping("quantidade_maior/{quantidade_maior}")
	public ResponseEntity <List<Produto>> GetByQuantidade_maior (@PathVariable int quantidade){
		return ResponseEntity.ok(produtoRepository.findAllByQuantidadeGreaterThanEqualOrderByQuantidade(quantidade));
	}
	@PostMapping
	public ResponseEntity <Produto> postProduto (@Valid @RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}
	@PutMapping
	 public ResponseEntity<?> putProduto(@Valid @RequestBody Produto produto){
		if(produto.getId()== null)
			return ResponseEntity.badRequest().build();
		
        return produtoRepository.findById(produto.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto)))
				.orElse (ResponseEntity.notFound().build());
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Produto> deletePostagem(@PathVariable Long id) {
		if(produtoRepository.existsById(id)) {
			produtoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
