package br.edu.ifpb.projeto.repository;

import br.edu.ifpb.projeto.model.Usuario;

public interface UsuarioRepository {
    void novoUsuario(Usuario usuario);

    String logar(String email, String senha);

    Usuario buscar(String key);

    boolean temPedido(String key, String codigo);
}
