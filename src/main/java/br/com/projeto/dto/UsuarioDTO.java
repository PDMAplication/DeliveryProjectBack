package br.com.projeto.dto;

import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {

        private Long id;

        private String nome;

        private String email;

        private String senha;

}
