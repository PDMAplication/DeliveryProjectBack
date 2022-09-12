package br.com.projeto.mapper;

import br.com.projeto.dto.PedidoDTO;
import br.com.projeto.model.Pedido;

public class PedidoMapper {
    public static PedidoDTO pedidoToDTO(Pedido pedido) {
        return PedidoDTO.builder()
                .id(pedido.getId())
                .build();
    }


}
