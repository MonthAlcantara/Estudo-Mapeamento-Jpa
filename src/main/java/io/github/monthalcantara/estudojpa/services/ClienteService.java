package io.github.monthalcantara.estudojpa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.monthalcantara.estudojpa.domain.Cliente;
import io.github.monthalcantara.estudojpa.exceptions.ObjectNotFoundException;
import io.github.monthalcantara.estudojpa.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	public ClienteRepository clienteRepository;

	public Cliente buscar(Integer id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Não foi encontrado cliente com o id: " + id));
	}

	public Page<Cliente> buscarTodos(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}

	public Cliente salvarNovoCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public void deletar(Integer id) {
		buscar(id);
		clienteRepository.deleteById(id);

	}

	public Cliente atualizarCliente(Integer id, Cliente cliente) {
		Optional<Cliente> categoriaEncontrada = clienteRepository.findById(id);
		if (categoriaEncontrada.isPresent()) {
			cliente.setId(id);
		}
		return clienteRepository.save(cliente);
	}

}
