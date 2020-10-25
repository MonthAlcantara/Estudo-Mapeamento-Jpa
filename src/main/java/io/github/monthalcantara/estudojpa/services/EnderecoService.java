package io.github.monthalcantara.estudojpa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.monthalcantara.estudojpa.domain.Endereco;
import io.github.monthalcantara.estudojpa.exceptions.ObjectNotFoundException;
import io.github.monthalcantara.estudojpa.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	public EnderecoRepository enderecoRepository;

	public Endereco buscar(Integer id) {
		return enderecoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Não foi encontrado endereco com o id: " + id));
	}

	public Page<Endereco> buscarTodos(Pageable pageable) {
		return enderecoRepository.findAll(pageable);
	}

	public Endereco salvarNovoEndereco(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	public void deletar(Integer id) {
		buscar(id);
		enderecoRepository.deleteById(id);

	}

	public Endereco atualizarEndereco(Integer id, Endereco endereco) {
		Optional<Endereco> categoriaEncontrada = enderecoRepository.findById(id);
		if (categoriaEncontrada.isPresent()) {
			endereco.setId(id);
		}
		return enderecoRepository.save(endereco);
	}

}
