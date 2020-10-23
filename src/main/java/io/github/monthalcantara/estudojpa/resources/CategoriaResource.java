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

import io.github.monthalcantara.estudojpa.domain.Categoria;
import io.github.monthalcantara.estudojpa.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity buscaTodos(Pageable pageable) {
		return ResponseEntity.ok(categoriaService.buscarTodos(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity buscaPeloId(@PathVariable Integer id) {
		return ResponseEntity.ok(categoriaService.buscar(id));
	}
	
	@PostMapping
	public ResponseEntity criaNovaCategoria(@RequestBody Categoria categoria ) {
		return ResponseEntity.ok(categoriaService.salvarNovaCategoria(categoria));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity atualizaCategoria(@PathVariable Integer id, @RequestBody Categoria categoria ) {
		return ResponseEntity.ok(categoriaService.atualizarCategoria(id, categoria));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deletaPeloId(@PathVariable Integer id) {
		categoriaService.deletar(id);
	}

}
