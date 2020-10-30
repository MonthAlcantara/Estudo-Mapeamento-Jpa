package io.github.monthalcantara.estudojpa.validation.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import io.github.monthalcantara.estudojpa.advice.ApiError;
import io.github.monthalcantara.estudojpa.domain.enums.TipoCliente;
import io.github.monthalcantara.estudojpa.dto.ClienteRequestDTO;
import io.github.monthalcantara.estudojpa.validation.annotations.ClienteInsert;
import io.github.monthalcantara.estudojpa.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteRequestDTO> {

	@Override
	public boolean isValid(ClienteRequestDTO value, ConstraintValidatorContext context) {
		List<ApiError> listaDeErros = new ArrayList<>();

		if (value.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.isValidCPF(value.getCpfOuCnpj())) {
			listaDeErros.add(new ApiError(500, "Campo CPF inválido"));
		}
		if (value.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.isValidCNPJ(value.getCpfOuCnpj())) {
			listaDeErros.add(new ApiError(500, "Campo CNPJ inválido"));
		}

		for (ApiError erro : listaDeErros) {
			for (String msg : erro.getErrors()) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
			}
		}
		return listaDeErros.isEmpty();
	}

}
