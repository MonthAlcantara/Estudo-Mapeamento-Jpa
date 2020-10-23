package io.github.monthalcantara.estudojpa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.monthalcantara.estudojpa.domain.Categoria;
import io.github.monthalcantara.estudojpa.exceptions.ObjectNotFoundException;
import io.github.monthalcantara.estudojpa.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	public CategoriaRepository categoriaRepository;
	
	public Categoria buscar(Integer id) {
	return categoriaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Não foi encontrada categoria com o id: " + id));	
	}

	public Page<Categoria> buscarTodos(Pageable pageable) {
		return categoriaRepository.findAll(pageable);
	}

	public Categoria salvarNovaCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public void deletar(Integer id) {
		buscar(id);
		categoriaRepository.deleteById(id);
		
	}

	public Categoria atualizarCategoria(Integer id, Categoria categoria) {
		Optional<Categoria> categoriaEncontrada = categoriaRepository.findById(id);
		if(categoriaEncontrada.isPresent()){
			categoria.setId(id);
		}
		return categoriaRepository.save(categoria);
	}

}
