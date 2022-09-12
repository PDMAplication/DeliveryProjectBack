package br.com.projeto.dto;

import br.com.projeto.enuns.PagamentoEnum;
import br.com.projeto.model.Item;
import br.com.projeto.model.Usuario;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private Long id;

    private List<ItemDTO> itensDTO = new ArrayList<>();

    private UsuarioDTO usuarioDTO;

    private PagamentoEnum pagamento;


    public BigDecimal somarPrecoTotal() {
        return itensDTO.stream().map(ItemDTO::getValorItem).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
