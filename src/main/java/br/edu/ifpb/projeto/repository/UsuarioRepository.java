package br.edu.ifpb.projeto.repository;

import br.edu.ifpb.projeto.model.Usuario;

public interface UsuarioRepository {

    void cadastrar(Usuario usuario);
    Usuario encontrarPorId(String id);
    Usuario atualizar(Usuario usuario);
    void deletar(String id);
}
