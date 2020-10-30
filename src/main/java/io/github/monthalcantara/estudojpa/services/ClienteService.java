package io.github.monthalcantara.estudojpa.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.monthalcantara.estudojpa.domain.Cidade;
import io.github.monthalcantara.estudojpa.domain.Cliente;
import io.github.monthalcantara.estudojpa.domain.Endereco;
import io.github.monthalcantara.estudojpa.dto.ClienteRequestDTO;
import io.github.monthalcantara.estudojpa.dto.ClienteResponseDTO;
import io.github.monthalcantara.estudojpa.exceptions.DataIntegrityException;
import io.github.monthalcantara.estudojpa.exceptions.ObjectNotFoundException;
import io.github.monthalcantara.estudojpa.repositories.CidadeRepository;
import io.github.monthalcantara.estudojpa.repositories.ClienteRepository;
import io.github.monthalcantara.estudojpa.repositories.EnderecoRepository;

@Service
public class ClienteService {

	@Autowired
	public ClienteRepository clienteRepository;

	@Autowired
	public CidadeRepository cidadeRepository;

	@Autowired
	public EnderecoRepository enderecoRepository;

	public Cliente buscar(Integer id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Não foi encontrado cliente com o id: " + id));
	}

	public Page<ClienteResponseDTO> buscarTodos(Pageable pageable) {
		Page<Cliente> paginaDeClientes = clienteRepository.findAll(pageable);
		List<ClienteResponseDTO> listaDeClientes = paginaDeClientes.getContent().stream()
				.map(c -> new ClienteResponseDTO(c)).collect(Collectors.toList());
		return new PageImpl<>(listaDeClientes, pageable, paginaDeClientes.getTotalElements());
	}

	@Transactional
	public Cliente salvarNovoCliente(ClienteRequestDTO clienteRequest) {
		Cliente cliente = converteParaCliente(clienteRequest);
		enderecoRepository.saveAll(cliente.getEnderecos());
		cliente = clienteRepository.save(cliente);
		return cliente;
	}

	public void deletar(Integer id) {
		buscar(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível deletar esse cliente pois há pedidos relacionados a ele");
		}

	}

	public Cliente atualizarCliente(Integer id, ClienteRequestDTO clienteRequestDTO) {
		Cliente cliente = converteParaCliente(clienteRequestDTO);
		Optional<Cliente> categoriaEncontrada = clienteRepository.findById(id);
		if (categoriaEncontrada.isPresent()) {
			cliente.setId(id);
		}
		return clienteRepository.save(cliente);
	}

	public Cliente converteParaCliente(ClienteRequestDTO clienteRequest) {
		Cliente cliente = new Cliente();
		cliente.setCpfOuCnpj(clienteRequest.getCpfOuCnpj());
		cliente.setEmail(clienteRequest.getEmail());
		cliente.setNome(clienteRequest.getNome());
		cliente.setTipo(clienteRequest.getTipo());
		Optional<Cidade> cidade = cidadeRepository.findById(clienteRequest.getCidade());
		if (cidade.isPresent()) {
			Endereco endereco = new Endereco(clienteRequest.getLogradouro(), clienteRequest.getNumero(),
					clienteRequest.getComplemento(), clienteRequest.getBairro(), clienteRequest.getCep(), cliente,
					cidade.get());

			cliente.getEnderecos().add(endereco);
		} else {
			throw new ObjectNotFoundException("Não existe essa cidade nos nossos registros");
		}

		Set<String> telefones = new HashSet<>();

		telefones.add(clienteRequest.getTelefone1());
		if (clienteRequest.getTelefone2() != null) {
			telefones.add(clienteRequest.getTelefone2());
		}
		if (clienteRequest.getTelefone3() != null) {
			telefones.add(clienteRequest.getTelefone3());
		}
		cliente.setTelefones(telefones);

		System.out.println(cliente);
		return cliente;
	}

	private ClienteResponseDTO converteParaClienteResponse(Cliente cliente) {
		ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();
		clienteResponseDTO.setCpfOuCnpj(cliente.getCpfOuCnpj());
		clienteResponseDTO.setEmail(cliente.getEmail());
		clienteResponseDTO.setNome(cliente.getNome());
		clienteResponseDTO.setTipo(cliente.getTipo());
		clienteResponseDTO.setEnderecos(cliente.getEnderecos());
		return clienteResponseDTO;
	}
}
