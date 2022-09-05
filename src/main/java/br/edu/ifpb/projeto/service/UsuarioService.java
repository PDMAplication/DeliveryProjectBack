package br.edu.ifpb.projeto.service;

import br.edu.ifpb.projeto.model.Usuario;
import br.edu.ifpb.projeto.repository.UsuarioRepository;

import javax.persistence.EntityManager;

public class UsuarioService implements UsuarioRepository {
    private EntityManager em;

    public UsuarioService(EntityManager em) {
        this.em = em;
    }

    @Override
    public void cadastrar(Usuario usuario) {
        this.em.persist(usuario);
    }

    @Override
    public Usuario encontrarPorId(String id) {
        return null;
    }

    @Override
    public Usuario atualizar(Usuario usuario) {
        return null;
    }

    @Override
    public void deletar(String id) {

    }
}
