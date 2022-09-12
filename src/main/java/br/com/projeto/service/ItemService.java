package br.com.projeto.service;

import br.com.projeto.dto.ItemDTO;
import br.com.projeto.exception.NotFoundException;
import br.com.projeto.mapper.ItemMapper;
import br.com.projeto.mapper.ProdutoMapper;
import br.com.projeto.mapper.UsuarioMapper;
import br.com.projeto.model.Item;
import br.com.projeto.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.requireNonNullElse;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemDTO findItemById(Long id) {
        Optional<ItemDTO> itemToFind = itemRepository.findById(id).map(ItemMapper::itemToDTO);
        if (itemToFind.isEmpty()) {
            throw new NotFoundException("Item não encontrado.");
        }
        return itemToFind.get();
    }

    public ItemDTO saveItem(ItemDTO itemDTO) {
        Item item = ItemMapper.dtoToItem(itemDTO);
        item.setId(null);
        return ItemMapper.itemToDTO(itemRepository.save(item));
    }

    public ItemDTO updateItem(ItemDTO itemDTO) {
        Optional<Item> itemToFind = itemRepository.findById(itemDTO.getId());
        if (itemToFind.isEmpty()) {
            throw new NotFoundException(
                    "Não foi possível atualizar o item com o ID: " + itemDTO.getId() + ", pois ele não existe.");
        }
        Item itemToUpdate = itemToFind.get();
        itemToUpdate.setUsuario(requireNonNullElse(UsuarioMapper.dtoToUsuario(itemDTO.getUsuarioDTO()), itemToUpdate.getUsuario()));
        itemToUpdate.setProduto(requireNonNullElse(ProdutoMapper.dtoToProduto(itemDTO.getProdutoDTO()), itemToUpdate.getProduto()));
        itemToUpdate.setQuantidadeProduto(requireNonNullElse(itemDTO.getQuantidadeProduto(), itemToUpdate.getQuantidadeProduto()));
        itemToUpdate.setValorItem(requireNonNullElse(itemDTO.getValorItem(), itemToUpdate.getValorItem()));
        return ItemMapper.itemToDTO(itemRepository.save(itemToUpdate));
    }

    public ItemDTO aumentarQuantidadeProduto(Long itemId) {
        Optional<Item> itemToFind = itemRepository.findById(itemId);
        if (itemToFind.isEmpty()) {
            throw new NotFoundException(
                    "Não foi possível atualizar o item com o ID: " + itemId + ", pois ele não existe.");
        }
        Item itemToUpdate = itemToFind.get();
        itemToUpdate.aumentarQuantidade();
        return ItemMapper.itemToDTO(itemRepository.save(itemToUpdate));
    }

    public ItemDTO diminuirQuantidadeProduto(Long itemId) {
        Optional<Item> itemToFind = itemRepository.findById(itemId);
        if (itemToFind.isEmpty()) {
            throw new NotFoundException(
                    "Não foi possível atualizar o item com o ID: " + itemId + ", pois ele não existe.");
        }
        Item itemToUpdate = itemToFind.get();
        itemToUpdate.diminuirQuantidade();
        return ItemMapper.itemToDTO(itemRepository.save(itemToUpdate));
    }

    public void deleteItem(Long itemId) {
        Optional<Item> item = this.itemRepository.findById(itemId);
        if (item.isEmpty()) {
            throw new NotFoundException("Item não encontrado.");
        }
        itemRepository.delete(item.get());
    }
}
