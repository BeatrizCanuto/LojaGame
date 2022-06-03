package com.generation.lojagame.model;

import java.math.BigDecimal;
import java.time.Year;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tb_produto")

public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo não aceita apenas caracteres em branco")
	@Size(min = 5,max = 250,message = "O atributo aceita no mínimo 5 e no máximo 250 caracteres")
	private String nome;
	
	@NotBlank(message = "O atributo não aceita apenas caracteres em branco")
	@Size(min = 5,max = 250,message = "O atributo aceita no mínimo 5 e no máximo 250 caracteres")
	private String descricao;
	
	@NotNull(message = "É obrigatório o uso de 4 caracteres no atributo")
	private Year ano; 
	
	@Positive
	@NotNull
	@Digits(integer = 3, fraction = 2, message = "No máximo centenas e 2 casas após o ponto" )
	private BigDecimal preco;
	
	@Positive
	@NotNull
	private int quantidade;
	
	@NotBlank(message = "O atributo aceita não ter foto")
	@Size(max = 50,message = "O atributo aceita no máximo 50 caracteres de link")
	private String foto;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Year getAno() {
		return ano;
	}

	public void setAno(Year ano) {
		this.ano = ano;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	
}
