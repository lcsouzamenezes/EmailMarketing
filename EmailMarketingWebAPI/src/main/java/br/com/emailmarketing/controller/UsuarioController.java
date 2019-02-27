package br.com.emailmarketing.controller;

import javax.validation.Valid;

import br.com.emailmarketing.dto.UsuarioDto;
import br.com.emailmarketing.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import br.com.emailmarketing.service.UsuarioService;


@RestController
@RequestMapping(value = "/public/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private AutenticacaoService autenticacaoService;

	@Transactional
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> save(@Valid @RequestBody UsuarioDto usuarioDto) {
		if(!usuarioDto.getPassword().equals(usuarioDto.getConfirmPassword())){
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
		usuarioService.save(usuarioDto.buildUsuario());
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	@RequestMapping(value = "/autenticar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> autenticar(String usuario, String senha){
		String token = autenticacaoService.autenticar(usuario, senha);
		return new ResponseEntity<String>(token, HttpStatus.FOUND);
	}

}
