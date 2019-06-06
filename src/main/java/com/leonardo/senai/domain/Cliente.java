package com.leonardo.senai.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.leonardo.senai.domain.enuns.TipoCliente;



@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id // Anotação avisando que esse atributo Será uma id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCliente;
	// Atributos serão Campos na tabela
	private String nome;
	private String cpfOuCnpj;
	// Será usado no enum.
	private Integer tipo;

	// O banco de dados não deixa repetir os dados
	@Column(unique = true)
	private String email;

	@ElementCollection // Cria uma tabela telefone
	@CollectionTable(name = "TELEFONE")
	private Set<String> telefones = new HashSet<>();
	
	//Cascade e para se excluir cliente o endereço seja excluido
	@OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)//Varios endereços um cliente
	private List<Endereco> enderecos = new ArrayList<>();

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	// Construtor
	public Cliente() {
	}

	public Cliente(Integer idCliente, String nome, String cpfOuCnpj, String email, 
		/*Atributo busca a Class*/
		TipoCliente tipo) {
		super();
		this.idCliente = idCliente;
		this.nome = nome;
		this.cpfOuCnpj = cpfOuCnpj;
		this.email = email;
		// Aqui não vai telefone
		//Dentro da class busca o cod
		this.tipo = tipo.getCod();

	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public String getNome() {
		return nome;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public String getEmail() {
		return email;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
		// esse to enum vem da classe enum tipo
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();// add .get também

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpfOuCnpj == null) ? 0 : cpfOuCnpj.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((enderecos == null) ? 0 : enderecos.hashCode());
		result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((telefones == null) ? 0 : telefones.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Cliente other = (Cliente) obj;
		if (cpfOuCnpj == null) {
			if (other.cpfOuCnpj != null)
				return false;
		} else if (!cpfOuCnpj.equals(other.cpfOuCnpj))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enderecos == null) {
			if (other.enderecos != null)
				return false;
		} else if (!enderecos.equals(other.enderecos))
			return false;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (telefones == null) {
			if (other.telefones != null)
				return false;
		} else if (!telefones.equals(other.telefones))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

}
