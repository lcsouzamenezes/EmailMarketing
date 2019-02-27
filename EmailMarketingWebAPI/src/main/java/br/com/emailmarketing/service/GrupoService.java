package br.com.emailmarketing.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.emailmarketing.entity.Grupo;

@Service
public class GrupoService {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void save(Grupo grupo) {
		this.entityManager.persist(grupo);
	}

	@Transactional
	public void update(Grupo grupo) {
		this.entityManager.merge(grupo);
	}

	@Transactional
	public void delete(Long id) {
		Grupo grupo = this.findBy(id);
		this.entityManager.remove(grupo);
	}

	@Transactional(readOnly = true)
	public Grupo findBy(Long id) {
		Grupo grupo = this.entityManager.find(Grupo.class, id);
		return grupo;
	}

}
