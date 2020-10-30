package io.github.monthalcantara.estudojpa.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import io.github.monthalcantara.estudojpa.domain.Cliente;
import io.github.monthalcantara.estudojpa.domain.Endereco;
import io.github.monthalcantara.estudojpa.domain.enums.TipoCliente;

public class ClienteResponseDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "É obrigatório infomar um nome para o cliente")
	@Length(min = 3, max = 80, message = "O nome deve ter entre 3 e 80 caracteres")
	private String nome;

	@Email(message = "Por favor informe um email num formato válido")
	private String email;

	private String cpfOuCnpj;

	private Integer tipo;

	private Set<String> telefones = new HashSet<>();
	
	private List<Endereco> enderecos = new ArrayList<>();

	public ClienteResponseDTO() {
	}

	public ClienteResponseDTO(Integer id, String nome, String email, String cpfOuCnpj, Integer tipo, Set<String> telefones, List<Endereco> enderecos) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo;
		this.telefones = telefones;
		this.enderecos = enderecos;
	}
	
	public ClienteResponseDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.cpfOuCnpj = cliente.getCpfOuCnpj();
		this.tipo = cliente.getTipo().getCodigo();
		this.telefones = cliente.getTelefones();
		this.enderecos = cliente.getEnderecos();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCodigo();
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}
	

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpfOuCnpj == null) ? 0 : cpfOuCnpj.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		ClienteResponseDTO other = (ClienteResponseDTO) obj;
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
		return true;
	}

}
