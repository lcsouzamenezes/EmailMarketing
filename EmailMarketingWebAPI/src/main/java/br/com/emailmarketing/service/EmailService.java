package br.com.emailmarketing.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.emailmarketing.entity.Mensagem;

@Service
public class EmailService {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void save(Mensagem email) {
		this.entityManager.persist(email);
	}

	@Transactional
	public void update(Mensagem email) {
		this.entityManager.merge(email);
	}

	@Transactional
	public void delete(Long id) {
		Mensagem email = this.findBy(id);
		this.entityManager.remove(email);
	}

	@Transactional(readOnly = true)
	public Mensagem findBy(Long id) {
		Mensagem email = this.entityManager.find(Mensagem.class, id);
		return email;
	}

}
