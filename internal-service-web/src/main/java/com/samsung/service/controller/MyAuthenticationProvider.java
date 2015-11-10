package com.samsung.service.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Controller;

import com.samsung.service.authentication.MyAuthenticationToken;
import com.samsung.service.model.UserBean;
import com.samsung.service.service.UserService;
import com.samsung.service.util.GenericsUtil;

@Controller
@Scope("request")
public class MyAuthenticationProvider implements AuthenticationProvider {

	@Autowired 
	private UserService userDetailService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException{
		// Login
		String login = (String) authentication.getPrincipal();

		// Excecao de usuario nao autenticado
		if(login == null || login.trim().length()==0){
			throw new AuthenticationServiceException("Erro na localizacao do LOGIN!!!");
		}

		//Aqui eu pego as informacao do usuario pelo proprio spring security
		UserBean usuario = userDetailService.findByName(login);

		try {

			if (!GenericsUtil.getPasswordHash(authentication.getCredentials().toString()).equals(usuario.getPassword())) {
				throw new AuthenticationServiceException("Usuario nao autenticado.");
			}

			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new GrantedAuthorityImpl(usuario.getEnabled()));

			//Se o usuario não for null, eu o autentico no sistema
			if (usuario != null) {
				return new MyAuthenticationToken(usuario.getEmail(), usuario.getPassword(), authorities);

			} else {
				throw new AuthenticationServiceException("Usuario nao autenticado.");
			}

		} catch(AuthenticationServiceException e) {
			throw e;
		} catch(Throwable e) {
			throw new AuthenticationServiceException("Ocorreu um erro no ato da autenticaï¿½ï¿½o.", e);
		}
	}


	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication)
				&& authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}