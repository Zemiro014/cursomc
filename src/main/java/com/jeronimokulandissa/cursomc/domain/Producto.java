package com.jeronimokulandissa.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

/* Declarando que a class "Producto" é uma entidade de domínio*/
@Entity 
public class Producto implements Serializable
{
	/*Serializable é uma interface que difine se os objectos de uma class poderão ser convertidos para uma sequência de Bytes
	 * Isso é importante pois permite que os objectos sejam gravados em arquivos, trafegar em redes e assim por diante. É uma exigência em Java
	 * Toda class que implementa a interface Serializable precisa de um número de versão padrão*/
	
	private static final long serialVersionUID = 1L; // número de versão padrão
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private double preco;
	
	/* Fazendo associação entre a class "Producto" com a class "Categoria"
	 * 
	 *  Quando a associação entre dois domínios (tabelas) for de muito
	 *   para muitos, é preciso declarar o "ManyToMany" e "JoinTable" 
	 *   na associação
	 *  */
	@JsonBackReference // Isso diz que: do outro lado da associação já foram obtido os objectos, logo pare a busca
	@ManyToMany
	@JoinTable(name="PRODUCTO_CATEGORIA", // nome da tabela resultante da associação muito para muitos
			joinColumns = @JoinColumn(name="producto_id"), // chave-estrangeira vindo da class "Producto"
			inverseJoinColumns =@JoinColumn(name="categoria_id")// chave-estrangeira vindo da class "Categoria"
	)
	private List<Categoria> categorias = new ArrayList<>();
	
	public Producto() {	}

	/* Uma vez que a coleção "categoria" já foi iniciada, ela não precisa 
	 * ser um parâmetro do construtor
	 * */
	public Producto(Integer id, String nome, double preco) 
	{
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	/*
	 * HashCodeEqual: Serve para comparar os objectos por valor e não pelo 
	 * ponteiro de memória. É a implementação padrão de comparação de 
	 * objectos. Normalmente a comparação é feita usando o Id
	 * */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
	
}