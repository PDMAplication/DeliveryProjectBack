package br.com.projeto.controller;

import br.com.projeto.dto.ProdutoDTO;
import br.com.projeto.model.Produto;
import br.com.projeto.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<ProdutoDTO> getProduto(@PathVariable Long produtoId) {
        ProdutoDTO produtoToGet = produtoService.findProdutoById(produtoId);
        return ResponseEntity.ok(produtoToGet);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> saveProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {
        return new ResponseEntity<>(this.produtoService.saveProduto(produtoDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProdutoDTO> updateProduto(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO produtoToUpdate = produtoService.updateProduto(produtoDTO);
        return ResponseEntity.ok(produtoToUpdate);
    }

    @DeleteMapping("/{produtoId}")
    public ResponseEntity<Produto> deleteById(@PathVariable Long produtoId) {
        this.produtoService.deleteProduto(produtoId);
        return ResponseEntity.noContent().build();
    }
}
