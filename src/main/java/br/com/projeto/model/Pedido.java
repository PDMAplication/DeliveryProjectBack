package br.com.projeto.model;

import br.com.projeto.enuns.PagamentoEnum;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Builder
@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itens = new ArrayList<>();

    @ManyToOne
    private Usuario usuario;

    private PagamentoEnum pagamento;

    public void adicionarItem(Item item) {
        this.itens.add(item);
    }

    public void removerItem(Long id) {
        Optional<Item> itemParaRemover = itens.stream().filter(map -> map.getId().equals(id)).findFirst();
        itemParaRemover.ifPresent(item -> this.itens.remove(item));
    }

    public BigDecimal somarPrecoTotal() {
        return itens.stream().map(Item::getValorItem).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
