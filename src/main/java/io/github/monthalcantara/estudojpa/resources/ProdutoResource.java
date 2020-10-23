package io.github.monthalcantara.estudojpa.resources;

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

import io.github.monthalcantara.estudojpa.domain.Produto;
import io.github.monthalcantara.estudojpa.services.ProdutoService;

@RestController
@RequestMapping("produtos")
public class ProdutoResource {
	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public ResponseEntity buscaTodos(Pageable pageable) {
		return ResponseEntity.ok(produtoService.buscarTodos(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity buscaPeloId(@PathVariable Integer id) {
		return ResponseEntity.ok(produtoService.buscar(id));
	}

	@PostMapping
	public ResponseEntity criaNovoProduto(@RequestBody Produto produto) {
		return ResponseEntity.ok(produtoService.salvarNovaCategoria(produto));
	}

	@PutMapping("/{id}")
	public ResponseEntity atualizaProduto(@PathVariable Integer id, @RequestBody Produto produto) {
		return ResponseEntity.ok(produtoService.atualizarCategoria(id, produto));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deletaPeloId(@PathVariable Integer id) {
		produtoService.deletar(id);
	}

}
