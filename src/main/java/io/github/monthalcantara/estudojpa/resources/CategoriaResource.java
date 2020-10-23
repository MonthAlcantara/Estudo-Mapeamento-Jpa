package io.github.monthalcantara.estudojpa.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.monthalcantara.estudojpa.domain.Categoria;
import io.github.monthalcantara.estudojpa.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/{id}")
	public ResponseEntity buscaPeloId(@PathVariable Integer id) {
		return ResponseEntity.ok(categoriaService.buscar(id));
	}
}
