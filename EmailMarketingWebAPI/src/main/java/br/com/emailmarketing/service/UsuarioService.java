package br.com.emailmarketing.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.emailmarketing.exception.NegocioException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.emailmarketing.entity.Usuario;

import java.util.Objects;

@Service
public class UsuarioService {
	
	private final static Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	//@Transactional
	public void save(Usuario usuario) {
	    if(Objects.nonNull(this.findByUsername(usuario.getUsername()))){
            throw new NegocioException("usernameEmUso");
        }
        if(Objects.nonNull(this.findByEmail(usuario.getEmail()))){
            throw new NegocioException("emailEmUso");
        }
		this.entityManager.persist(usuario);
		logger.info("Usuario salvo: ".concat(usuario.toString()));
	}

    @Transactional(readOnly = true)
    public Usuario findByEmail(String email) {
        TypedQuery<Usuario> query = this.entityManager.createNamedQuery("findByEmail", Usuario.class);
        query.setParameter("email", email);

        Usuario usuario = null;

        try {
            usuario = query.getSingleResult();
        } catch(NoResultException e){
            return null;
        }

        return usuario;
    }

	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		TypedQuery<Usuario> query = this.entityManager.createNamedQuery("findByUsername", Usuario.class);
		query.setParameter("username", username);

		Usuario usuario = null;

		try {
			usuario = query.getSingleResult();
		} catch(NoResultException e){
			return null;
		}

		return usuario;
	}

	@Transactional(readOnly = true)
	public Usuario findBy(String username, String password) {
		TypedQuery<Usuario> query = this.entityManager.createNamedQuery("findByUsernameAndPassword", Usuario.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		
		Usuario usuario = null; 
				
		try {
			usuario = query.getSingleResult();
		} catch(NoResultException e){
			return null;
		}
		
		return usuario;
	}

	@Transactional(readOnly = true)
	public Usuario findByUsername(Long id) {
		Usuario usuario = this.entityManager.find(Usuario.class, id);
		return usuario;
	}

	public Usuario recuperarUsuarioLogado(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null){
        	throw new AuthenticationCredentialsNotFoundException("Usuário não está autenticado.");
		}
        return this.findByUsername(auth.getName());
    }

}
