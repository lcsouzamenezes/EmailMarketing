package br.com.emailmarketing.spring.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.emailmarketing.entity.Usuario;
import br.com.emailmarketing.service.UsuarioService;
import br.com.emailmarketing.spring.beans.MessageSourceCustom;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private MessageSourceCustom messageSourceCustom;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        Usuario usuario = this.usuarioService.findBy(username, password);
        
        if(Objects.nonNull(usuario)){
        	List<GrantedAuthority> grantedAuths = new ArrayList<>();
        	grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        	UserDetails principal = new User(username, password, grantedAuths);
        	Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
        	return auth;
        } 
        else {
        	String msgErro = messageSourceCustom.getMessagem("usuarioOuSenhaInvalido");
        	throw new BadCredentialsException(msgErro);
        }
        
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
