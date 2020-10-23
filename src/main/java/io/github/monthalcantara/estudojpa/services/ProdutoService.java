package io.github.monthalcantara.estudojpa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.monthalcantara.estudojpa.domain.Produto;
import io.github.monthalcantara.estudojpa.exceptions.ObjectNotFoundException;
import io.github.monthalcantara.estudojpa.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Page<Produto> buscarTodos(Pageable pageable) {
		return produtoRepository.findAll(pageable);
	}

	public Produto buscar(Integer id) {
		return produtoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Não foi encontrado produto com o id: " + id));	
	}

	public Produto salvarNovaCategoria(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Produto atualizarCategoria(Integer id, Produto produto) {
		Optional<Produto> produtoEncontrado = produtoRepository.findById(id);
		if (produtoEncontrado.isPresent()) {
			produto.setId(id);
		}
		return produtoRepository.save(produto);
	}

	public void deletar(Integer id) {
		buscar(id);
		produtoRepository.deleteById(id);

	}

}
