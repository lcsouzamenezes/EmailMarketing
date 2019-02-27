package br.com.emailmarketing.controller;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.emailmarketing.entity.Contato;
import br.com.emailmarketing.service.ContatoService;

import java.util.List;

@RestController
@RequestMapping(value = "/private/contato")
public class ContatoController {
	
	@Autowired
	private ContatoService contatoService;

	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(value="/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void save(@Valid @RequestBody Contato contato) {
		this.contatoService.save(contato);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@PathVariable("id") Long id, @Valid @RequestBody Contato contato) {
		this.contatoService.update(contato);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		this.contatoService.delete(id);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Contato findBy(@PathVariable("id") Long id) {
		return this.contatoService.findBy(id);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Contato>> listAll() {
		return new ResponseEntity<List<Contato>>(this.contatoService.listAll(), HttpStatus.FOUND);
	}

}
