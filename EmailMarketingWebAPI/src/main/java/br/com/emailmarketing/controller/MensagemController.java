package br.com.emailmarketing.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.emailmarketing.entity.Mensagem;
import br.com.emailmarketing.service.EmailService;

@RestController
@RequestMapping(value = "/private/mensagem")
public class MensagemController {

	private final static Logger logger = LoggerFactory.getLogger(MensagemController.class);

	@Autowired
	private EmailService emailService;

	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(value="/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void save(@Valid @RequestBody Mensagem mensagem) {
		this.emailService.save(mensagem);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@PathVariable("id") Long id, @Valid @RequestBody Mensagem email) {
		this.emailService.update(email);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(Long id) {
		this.emailService.delete(id);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mensagem findBy(@PathVariable("id") Long id) {
		return this.emailService.findBy(id);
	}

}
