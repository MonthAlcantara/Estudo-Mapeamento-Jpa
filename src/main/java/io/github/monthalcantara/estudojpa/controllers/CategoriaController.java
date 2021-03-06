package io.github.monthalcantara.estudojpa.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
import io.github.monthalcantara.estudojpa.dto.CategoriaDTO;
import io.github.monthalcantara.estudojpa.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity buscaTodos(@PageableDefault(size = 24, sort = "id", direction = Direction.ASC) Pageable pageable) {
		return ResponseEntity.ok(categoriaService.buscarTodos(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity buscaPeloId(@PathVariable Integer id) {
		Categoria categoriaEncontrada = categoriaService.buscar(id);
		return ResponseEntity.ok(new CategoriaDTO(categoriaEncontrada));
	}

	@PostMapping
	public ResponseEntity<CategoriaDTO> criaNovaCategoria(@RequestBody @Valid CategoriaDTO categoriaDTO) {
		Categoria categoria = categoriaService.salvarNovaCategoria(new Categoria(categoriaDTO));
		URI uri = geradorDeLocation(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, String.valueOf(uri))
				.body(new CategoriaDTO(categoria));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoriaDTO> atualizaCategoria(@PathVariable Integer id, @Valid @RequestBody CategoriaDTO categoriaDTO) {
		Categoria categoria = categoriaService.atualizarCategoria(id, new Categoria(categoriaDTO));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.LOCATION, String.valueOf(uri))
				.body(new CategoriaDTO(categoria));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deletaPeloId(@PathVariable Integer id) {
		categoriaService.deletar(id);
	}

	private URI geradorDeLocation(Categoria categoria) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
	}
}
