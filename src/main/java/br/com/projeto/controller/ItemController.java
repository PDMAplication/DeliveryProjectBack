package br.com.projeto.controller;

import br.com.projeto.dto.ItemDTO;
import br.com.projeto.model.Item;
import br.com.projeto.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/itens")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDTO> getItem(@PathVariable Long itemId) {
        ItemDTO itemToGet = itemService.findItemById(itemId);
        return ResponseEntity.ok(itemToGet);
    }

    @PostMapping
    public ResponseEntity<ItemDTO> saveProduto(@RequestBody @Valid ItemDTO itemDTO) {
        return new ResponseEntity<>(this.itemService.saveItem(itemDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ItemDTO> updateItem(@RequestBody ItemDTO itemDTO) {
        ItemDTO itemToUpdate = itemService.updateItem(itemDTO);
        return ResponseEntity.ok(itemToUpdate);
    }

    @PutMapping("/aumentar/{itemId}")
    public ResponseEntity<ItemDTO> aumentarQuantidadeProduto(@PathVariable Long itemId) {
        ItemDTO itemToUpdate = itemService.aumentarQuantidadeProduto(itemId);
        return ResponseEntity.ok(itemToUpdate);
    }

    @PutMapping("/diminuir/{itemId}")
    public ResponseEntity<ItemDTO> diminuirQuantidadeProduto(@PathVariable Long itemId) {
        ItemDTO itemToUpdate = itemService.diminuirQuantidadeProduto(itemId);
        return ResponseEntity.ok(itemToUpdate);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Item> deleteById(@PathVariable Long itemId) {
        this.itemService.deleteItem(itemId);
        return ResponseEntity.noContent().build();
    }
}
