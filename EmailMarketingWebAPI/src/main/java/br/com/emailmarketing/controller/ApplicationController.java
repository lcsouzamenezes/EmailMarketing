package br.com.emailmarketing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/public/application")
public class ApplicationController {
	
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value="/server", method = RequestMethod.POST)
	public void isServerOn() {
		
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value="/mail", method = RequestMethod.POST)
	public void isMailServerOn() {
		
	}
	
}
