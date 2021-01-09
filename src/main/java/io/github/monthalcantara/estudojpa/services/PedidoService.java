package io.github.monthalcantara.estudojpa.services;

import io.github.monthalcantara.estudojpa.domain.Pedido;
import io.github.monthalcantara.estudojpa.exceptions.ObjectNotFoundException;
import io.github.monthalcantara.estudojpa.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    public PedidoRepository pedidoRepository;

    public Pedido buscar(Integer id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Não foi encontrado pedido com o id: " + id));
    }

    public Page<Pedido> buscarTodos(Pageable pageable) {
        return pedidoRepository.findAll(pageable);
    }

    public Pedido salvarNovoPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void deletar(Integer id) {
        buscar(id);
        pedidoRepository.deleteById(id);


    }

    public Pedido atualizarPedido(Integer id, Pedido pedido) {
        Optional<Pedido> pedidoEncontrado = pedidoRepository.findById(id);
        if (pedidoEncontrado.isPresent()) {
            pedido.setId(id);
        }
        return pedidoRepository.save(pedido);
    }
}
