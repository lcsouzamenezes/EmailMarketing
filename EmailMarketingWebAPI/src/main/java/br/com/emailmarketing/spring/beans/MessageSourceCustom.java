package br.com.emailmarketing.spring.beans;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageSourceCustom {
	
	@Autowired
	private MessageSource messageSource;
	
	private Locale locale = new Locale("pt", "BR");
	
	public String getMensagem(String code, String...args){
		return messageSource.getMessage(code, args, locale);
	}
	
	public String getMessagem(String code){
		return messageSource.getMessage(code, null, locale);
	}
	
}
