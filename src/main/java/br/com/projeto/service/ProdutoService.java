package br.com.projeto.service;

import br.com.projeto.dto.ProdutoDTO;
import br.com.projeto.exception.NotFoundException;
import br.com.projeto.mapper.ProdutoMapper;
import br.com.projeto.model.Produto;
import br.com.projeto.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.requireNonNullElse;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoDTO findProdutoById(Long id) {
        Optional<ProdutoDTO> produtoToFind = produtoRepository.findById(id).map(ProdutoMapper::produtoToDTO);
        if (produtoToFind.isEmpty()) {
            throw new NotFoundException("Produto não encontrado.");
        }
        return produtoToFind.get();
    }

    public ProdutoDTO saveProduto(ProdutoDTO produtoDTO) {
        Produto produto = ProdutoMapper.dtoToProduto(produtoDTO);
        produto.setId(null);
        return ProdutoMapper.produtoToDTO(produtoRepository.save(produto));
    }

    public ProdutoDTO updateProduto(ProdutoDTO produtoDTO) {
        Optional<Produto> produtoToFind = produtoRepository.findById(produtoDTO.getId());
        if (produtoToFind.isEmpty()) {
            throw new NotFoundException(
                    "Não foi possível atualizar produto com o ID: " + produtoDTO.getId() + ", pois ele não existe.");
        }
        Produto produtoToUpdate = produtoToFind.get();
        produtoToUpdate.setNome(requireNonNullElse(produtoDTO.getNome(), produtoToUpdate.getNome()));
        produtoToUpdate.setValor(requireNonNullElse(produtoDTO.getValor(), produtoToUpdate.getValor()));
        produtoToUpdate.setDescricao(requireNonNullElse(produtoDTO.getDescricao(), produtoToUpdate.getDescricao()));
        return ProdutoMapper.produtoToDTO(produtoRepository.save(produtoToUpdate));
    }

    public void deleteProduto(Long produtoId) {
        Optional<Produto> produto = this.produtoRepository.findById(produtoId);
        if (produto.isEmpty()) {
            throw new NotFoundException("Produto não encontrado.");
        }
        produtoRepository.delete(produto.get());
    }

}
