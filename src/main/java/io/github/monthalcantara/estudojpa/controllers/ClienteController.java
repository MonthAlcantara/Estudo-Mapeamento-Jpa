package io.github.monthalcantara.estudojpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

import io.github.monthalcantara.estudojpa.domain.Cliente;
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
		return ResponseEntity.ok(clienteService.buscar(id));
	}

	@PostMapping
	public ResponseEntity criaNovoProduto(@RequestBody Cliente cliente) {
		return ResponseEntity.ok(clienteService.salvarNovoCliente(cliente));
	}

	@PutMapping("/{id}")
	public ResponseEntity atualizaProduto(@PathVariable Integer id, @RequestBody Cliente cliente) {
		return ResponseEntity.ok(clienteService.atualizarCliente(id, cliente));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deletaPeloId(@PathVariable Integer id) {
		clienteService.deletar(id);
	}

}
