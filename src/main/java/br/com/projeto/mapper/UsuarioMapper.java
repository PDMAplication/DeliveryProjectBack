package br.com.projeto.mapper;

import br.com.projeto.dto.UsuarioDTO;
import br.com.projeto.model.Usuario;

public class UsuarioMapper {

    public static UsuarioDTO usuarioToDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .build();
    }

    public static Usuario dtoToUsuario(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .id(usuarioDTO.getId())
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .build();
    }

}
