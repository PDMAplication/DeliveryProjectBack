package br.com.projeto.mapper;

import br.com.projeto.dto.ItemDTO;
import br.com.projeto.model.Item;

public class ItemMapper {

    public static ItemDTO itemToDTO(Item item) {
        return ItemDTO.builder()
                .id(item.getId())
                .usuarioDTO(UsuarioMapper.usuarioToDTO(item.getUsuario()))
                .produtoDTO(ProdutoMapper.produtoToDTO(item.getProduto()))
                .quantidadeProduto(item.getQuantidadeProduto())
                .valorItem(item.getValorItem())
                .build();
    }

    public static Item dtoToItem(ItemDTO itemDTO) {
        return Item.builder()
                .id(itemDTO.getId())
                .usuario(UsuarioMapper.dtoToUsuario(itemDTO.getUsuarioDTO()))
                .produto(ProdutoMapper.dtoToProduto(itemDTO.getProdutoDTO()))
                .quantidadeProduto(itemDTO.getQuantidadeProduto())
                .valorItem(itemDTO.getValorItem())
                .build();
    }
}
