package br.com.projeto.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Builder
@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório!")
    @Length(min = 3, max = 100, message = "O nome deverá ter no mínimo {min} e no máximo {max} caracteres.")
    private String nome;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(length = 1000)
    private String descricao;

    @Column(nullable = false)
    private String foto;

}
