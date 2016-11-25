package com.dynamicdroides.vdc.services.security.autentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class AutenticaDatabaseProvider extends AutenticaProvider{

	@Override
	protected UserDetails retrieveUser(String arg0,
			UsernamePasswordAuthenticationToken arg1)
			throws AuthenticationException {
		return null;
		
	}
	
}
