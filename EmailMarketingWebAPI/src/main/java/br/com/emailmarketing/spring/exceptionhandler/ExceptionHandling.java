package br.com.emailmarketing.spring.exceptionhandler;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;

import org.hibernate.exception.ConstraintViolationException;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.validation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.emailmarketing.spring.beans.MessageSourceCustom;
import br.com.emailmarketing.exception.NegocioException;
import br.com.emailmarketing.exception.SistemaException;

@ControllerAdvice(basePackages="br.com.emailmarketing.controller")
public class ExceptionHandling {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandling.class);
	
	@Autowired 
	private MessageSourceCustom messageSourceCustom;
	
	@ExceptionHandler(value=PersistenceException.class)
	public ResponseEntity<JSONObject> handlerPersistenceException(PersistenceException e){
		JSONObject jsonRetorno = new JSONObject();
		
		if(javax.validation.ConstraintViolationException.class.equals(e.getCause().getClass())){
			javax.validation.ConstraintViolationException constraintViolationException = (javax.validation.ConstraintViolationException) e.getCause();
			Set<ConstraintViolation<?>> constraintViolations = constraintViolationException.getConstraintViolations();
			for(ConstraintViolation<?> constraintViolation : constraintViolations) {
				jsonRetorno.put(constraintViolation.getPropertyPath(), constraintViolation.getMessage());
			}
		}
		else if(org.hibernate.exception.ConstraintViolationException.class.equals(e.getCause().getClass())){
			org.hibernate.exception.ConstraintViolationException constraintViolationException = (org.hibernate.exception.ConstraintViolationException) e.getCause();
			jsonRetorno.put("erro", messageSourceCustom.getMessagem(constraintViolationException.getConstraintName()));
		}
		
		return new ResponseEntity<JSONObject>(jsonRetorno, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=BindException.class)
	public ResponseEntity<JSONObject> handlerBindException(BindException e){
		JSONObject jsonRetorno = new JSONObject();
		JSONObject jsonErros = new JSONObject();
		List<FieldError> erros = e.getFieldErrors();
		erros.forEach(erro -> { 
			jsonErros.put(erro.getField(), erro.getDefaultMessage());
		});
		jsonRetorno.put("erro", jsonErros);
		return new ResponseEntity<JSONObject>(jsonRetorno, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=IllegalArgumentException.class)
	public ResponseEntity<JSONObject> handlerIllegalArgumentException(IllegalArgumentException e){
		JSONObject jsonRetorno = new JSONObject();
		jsonRetorno.put("erros", e.getMessage());
		return new ResponseEntity<JSONObject>(jsonRetorno, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=NegocioException.class)
	public ResponseEntity<JSONObject> handlerNegocioException(NegocioException e){
		JSONObject jsonRetorno = new JSONObject();
		jsonRetorno.put("erro", messageSourceCustom.getMessagem(e.getMessage()));
		return new ResponseEntity<JSONObject>(jsonRetorno, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=SistemaException.class)
	public ResponseEntity<JSONObject> handlerSistemaException(SistemaException e){
		JSONObject jsonRetorno = new JSONObject();
		jsonRetorno.put("erro", messageSourceCustom.getMessagem(e.getMessage()));
		return new ResponseEntity<JSONObject>(jsonRetorno, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=EntityNotFoundException.class)
	public ResponseEntity<JSONObject> handlerEntityNotFoundException(EntityNotFoundException e){
		JSONObject jsonRetorno = new JSONObject();
		jsonRetorno.put("erro", e.getMessage());
		return new ResponseEntity<JSONObject>(jsonRetorno, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=JpaSystemException.class)
	public ResponseEntity<JSONObject> handlerJpaSystemException(JpaSystemException e){
		JSONObject jsonRetorno = new JSONObject();
		if(e.getCause() instanceof ConstraintViolationException){
			ConstraintViolationException constraintException = (ConstraintViolationException) e.getCause();
			constraintException.getConstraintName();
			jsonRetorno.put("erro", constraintException.getConstraintName());
		}
		return new ResponseEntity<JSONObject>(jsonRetorno, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	public ResponseEntity<JSONObject> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e){
		JSONObject jsonErros = new JSONObject();
		BindingResult bindingResult = e.getBindingResult();
		for(ObjectError error : bindingResult.getAllErrors()){
		    FieldError fieldError = (FieldError) error;
            jsonErros.put(fieldError.getField(), error.getDefaultMessage());
		}
		return new ResponseEntity<JSONObject>(jsonErros, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value=AuthenticationCredentialsNotFoundException.class)
	public ResponseEntity<JSONObject> handlerAuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException e){
		JSONObject jsonRetorno = new JSONObject();
		jsonRetorno.put("erro", e.getMessage());
		return new ResponseEntity<JSONObject>(jsonRetorno, HttpStatus.UNAUTHORIZED);
	}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value=Exception.class)
	public void handlerException(Exception e) {
		logger.error("Exception nao tratada: ".concat(e.toString()));
	}

}
