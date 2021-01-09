package io.github.monthalcantara.estudojpa.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import io.github.monthalcantara.estudojpa.validation.annotations.ClienteInsert;

@ClienteInsert
public class ClienteRequestDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "É obrigatório infomar um nome para o cliente")
	@Length(min = 3, max = 80, message = "O nome deve ter entre 3 e 80 caracteres")
	private String nome;

	@Email(message = "Por favor informe um email num formato válido")
	private String email;

	@NotEmpty
	private String cpfOuCnpj;

	@NotNull 
	private Integer tipo;

	@NotEmpty
	private String logradouro;

	@NotEmpty
	private String numero;

	@NotEmpty
	private String complemento;

	@NotEmpty
	private String bairro;

	@NotEmpty
	private String cep;

	@NotNull 
	private Integer cidade;

	@NotEmpty
	private String telefone1;
	
	private String telefone2;
	
	private String telefone3;
	
	
	public ClienteRequestDTO() {
	}

	public ClienteRequestDTO(
			@NotEmpty @Length(min = 3, max = 80) String nome,
			@Email String email,
			@NotEmpty String cpfOuCnpj,
			@NotNull Integer tipo,
			@NotEmpty String logradouro,
			@NotEmpty String numero,
			@NotEmpty String complemento,
			@NotEmpty String bairro,
			@NotEmpty String cep,
			@NotNull Integer cidade,
			@NotEmpty String telefone1,
			String telefone2,
			String telefone3) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.telefone3 = telefone3;
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

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getCidade() {
		return cidade;
	}

	public void setCidade(Integer cidade) {
		this.cidade = cidade;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
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
		ClienteRequestDTO other = (ClienteRequestDTO) obj;
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
