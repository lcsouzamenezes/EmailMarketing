package br.com.emailmarketing.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.emailmarketing.entity.Grupo;
import br.com.emailmarketing.service.GrupoService;

@RestController
@RequestMapping(value = "/private/grupo")
public class GrupoController {

	@Autowired
	private GrupoService grupoService;

	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void save(@Valid @RequestBody Grupo grupo) {
		this.grupoService.save(grupo);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@PathVariable("id") Long id, @Valid @RequestBody Grupo grupo) {
		this.grupoService.update(grupo);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		this.grupoService.delete(id);
	}

	@ResponseStatus(code = HttpStatus.FOUND)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Grupo findBy(@PathVariable("id") Long id) {
		return this.grupoService.findBy(id);
	}

}
