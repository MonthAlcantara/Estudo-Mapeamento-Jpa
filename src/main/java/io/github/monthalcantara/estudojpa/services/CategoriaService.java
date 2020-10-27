package io.github.monthalcantara.estudojpa.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.monthalcantara.estudojpa.domain.Categoria;
import io.github.monthalcantara.estudojpa.dto.CategoriaDTO;
import io.github.monthalcantara.estudojpa.exceptions.DataIntegrityException;
import io.github.monthalcantara.estudojpa.exceptions.ObjectNotFoundException;
import io.github.monthalcantara.estudojpa.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	public CategoriaRepository categoriaRepository;

	public Categoria buscar(Integer id) {
		return categoriaRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Não foi encontrada categoria com o id: " + id));
	}

	public Page<CategoriaDTO> buscarTodos(Pageable pageable) {
		Page<Categoria> pageCategoria = categoriaRepository.findAll(pageable);
		List<Categoria> lista = pageCategoria.getContent();
		List<CategoriaDTO> listaDTO = lista.stream().map(c -> new CategoriaDTO(c)).collect(Collectors.toList());

		return new PageImpl<>(listaDTO, pageable, pageCategoria.getTotalElements());
	}

	public Categoria salvarNovaCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public void deletar(Integer id) {
		buscar(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possível deletar essa categoria pois há produtos relacionados a ela");
		}

	}

	public Categoria atualizarCategoria(Integer id, Categoria categoria) {
		Optional<Categoria> categoriaEncontrada = categoriaRepository.findById(id);
		if (categoriaEncontrada.isPresent()) {
			categoria.setId(id);
		}
		return categoriaRepository.save(categoria);
	}

}
