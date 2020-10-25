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

import io.github.monthalcantara.estudojpa.domain.Endereco;
import io.github.monthalcantara.estudojpa.services.EnderecoService;

@RestController
@RequestMapping("enderecos")
public class EnderecoController {
	@Autowired
	private EnderecoService enderecoService;

	@GetMapping
	public ResponseEntity buscaTodos(Pageable pageable) {
		return ResponseEntity.ok(enderecoService.buscarTodos(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity buscaPeloId(@PathVariable Integer id) {
		return ResponseEntity.ok(enderecoService.buscar(id));
	}

	@PostMapping
	public ResponseEntity criaNovoProduto(@RequestBody Endereco endereco) {
		return ResponseEntity.ok(enderecoService.salvarNovoEndereco(endereco));
	}

	@PutMapping("/{id}")
	public ResponseEntity atualizaEndereco(@PathVariable Integer id, @RequestBody Endereco endereco) {
		return ResponseEntity.ok(enderecoService.atualizarEndereco(id, endereco));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deletaPeloId(@PathVariable Integer id) {
		enderecoService.deletar(id);
	}

}
