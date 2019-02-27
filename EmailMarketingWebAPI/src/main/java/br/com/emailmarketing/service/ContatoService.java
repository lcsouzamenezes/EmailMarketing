package br.com.emailmarketing.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.emailmarketing.entity.Usuario;
import br.com.emailmarketing.exception.NegocioException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.emailmarketing.entity.Contato;

import java.util.List;

@Service
public class ContatoService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private UsuarioService usuarioService;

	@Transactional
	public void save(Contato contato) {
		Usuario usuario = this.usuarioService.recuperarUsuarioLogado();
		if(usuario.getContatos().contains(contato)){
		    throw new NegocioException("contatoRepetido");
        }
        contato.setUsuario(usuario);
		usuario.getContatos().add(contato);
		this.entityManager.merge(usuario);
	}

	@Transactional
	public void update(Contato contato) {
		this.entityManager.merge(contato);
	}

	@Transactional
	public void delete(Long id) {
		Contato contato = this.findBy(id);
		this.entityManager.remove(contato);
	}

	@Transactional(readOnly = true)
	public Contato findBy(Long id) {
		Contato contato = this.entityManager.find(Contato.class, id);
		return contato;
	}

    @Transactional(readOnly = true)
    public List<Contato> listAll(){
	    return this.usuarioService.recuperarUsuarioLogado().getContatos();
    }

}
