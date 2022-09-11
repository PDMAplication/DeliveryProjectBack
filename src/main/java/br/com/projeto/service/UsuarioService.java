package br.com.projeto.service;

import br.com.projeto.dto.UsuarioDTO;
import br.com.projeto.exception.NotFoundException;
import br.com.projeto.mapper.UsuarioMapper;
import br.com.projeto.model.Usuario;
import br.com.projeto.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.requireNonNullElse;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDTO findUsuarioById(Long id) {
        Optional<UsuarioDTO> usuarioToFind = usuarioRepository.findById(id).map(UsuarioMapper::usuarioToDTO);
        if (usuarioToFind.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado.");
        }
        return usuarioToFind.get();
    }

    public UsuarioDTO saveUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioMapper.dtoToUsuario(usuarioDTO);
        usuario.setId(null);
        return UsuarioMapper.usuarioToDTO(usuarioRepository.save(usuario));
    }

    public UsuarioDTO updateUsuario(UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioToFind = usuarioRepository.findById(usuarioDTO.getId());
        if (usuarioToFind.isEmpty()) {
            throw new NotFoundException(
                    "Não foi possível atualizar usuário com o ID: " + usuarioDTO.getId() + ", pois ele não existe.");
        }
        Usuario usuarioToUpdate = usuarioToFind.get();
        usuarioToUpdate.setNome(requireNonNullElse(usuarioDTO.getNome(), usuarioToUpdate.getNome()));
        usuarioToUpdate.setEmail(requireNonNullElse(usuarioDTO.getEmail(), usuarioToUpdate.getEmail()));
        usuarioToUpdate.setSenha(requireNonNullElse(usuarioDTO.getSenha(), usuarioToUpdate.getSenha()));
        return UsuarioMapper.usuarioToDTO(usuarioRepository.save(usuarioToUpdate));
    }

    public void deleteUsuario(Long usuarioId) {
        Optional<Usuario> usuario = this.usuarioRepository.findById(usuarioId);
        if (usuario.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado.");
        }
        usuarioRepository.delete(usuario.get());
    }
}
