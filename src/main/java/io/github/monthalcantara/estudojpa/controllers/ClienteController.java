package io.github.monthalcantara.estudojpa.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.monthalcantara.estudojpa.domain.Categoria;
import io.github.monthalcantara.estudojpa.domain.Cliente;
import io.github.monthalcantara.estudojpa.dto.ClienteRequestDTO;
import io.github.monthalcantara.estudojpa.dto.ClienteResponseDTO;
import io.github.monthalcantara.estudojpa.services.ClienteService;

@RestController
@RequestMapping("clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity buscaTodos(Pageable pageable) {
		return ResponseEntity.ok(clienteService.buscarTodos(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity buscaPeloId(@PathVariable Integer id) {
		Cliente clienteEncontrado = clienteService.buscar(id);
		return ResponseEntity.ok(new ClienteResponseDTO(clienteEncontrado));
	}

	@PostMapping
	public ResponseEntity criaNovoProduto(@RequestBody @Valid ClienteRequestDTO cliente) {
		Cliente cli = clienteService.salvarNovoCliente(cliente);
		URI uri = geradorDeLocation(cli);
		return ResponseEntity.status(HttpStatus.CREATED)
				.header(HttpHeaders.LOCATION, String.valueOf(uri))
				.body(new ClienteResponseDTO(cli));
	}

	@PutMapping("/{id}")
	public ResponseEntity atualizaProduto(@PathVariable Integer id, @RequestBody @Valid ClienteRequestDTO cliente) {
		Cliente cli = clienteService.atualizarCliente(id, cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(cli.getId()).toUri();
		return ResponseEntity.status(HttpStatus.OK)
				.header(HttpHeaders.LOCATION, String.valueOf(uri))
				.body(new ClienteResponseDTO(cli));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deletaPeloId(@PathVariable Integer id) {
		clienteService.deletar(id);
	}
	private URI geradorDeLocation(Cliente cliente) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
	}
}
