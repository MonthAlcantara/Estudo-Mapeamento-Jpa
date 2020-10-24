package io.github.monthalcantara.estudojpa.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"), PESSOAJURIDICA(2, "Pessoa Jurídica");

	private Integer codigo;
	private String descricao;

	private TipoCliente(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoCliente toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		
		for (TipoCliente x : TipoCliente.values()){
			if(codigo.equals(x)) {
				return x;
			}
		}
		throw new IllegalArgumentException("Codigo inválido: " + codigo);
	}
}
