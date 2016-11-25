package com.dynamicdroides.vdc.services.security.autentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.dynamicdroides.services.security.devices.SecurityUtils;

/**
 * Esta clase es invocada desde el archivo applicationContext-security.xml
 * Gestiona la seguridad para Spring. Resolviendo el formulario enviado, y devolviendo un objeto UserDetails para el acceso a la aplicacion, o una excepcion si no cumple los requisitos de entrada
 * @author Pipe
 *
 */
public class AutenticaProvider extends
		AbstractUserDetailsAuthenticationProvider {
	
private String userName,password;

	/**
	 * Este es el metodo que se ejecuta cuando submitimos el formulario de login.
	 * Recibe dos datos: username y password
	 */
	@Override
	protected UserDetails retrieveUser(String arg0,
			UsernamePasswordAuthenticationToken arg1)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		String userName = arg0;
		String credentials=arg1.getCredentials().toString();
		if(checkUser(userName, credentials)){
			List<GrantedAuthority> yoS = new ArrayList<GrantedAuthority>(); 
			SimpleGrantedAuthority yo = new SimpleGrantedAuthority("ROLE_ES");
			yoS.add(yo);
			userName="admin";
			UserDetails newContext= new User(userName,credentials,true,true,true,true,yoS);
			return newContext;	
		}else{
			throw new AuthenticationServiceException("Error en el usuario/contraseña");
		}
								
								
	}
	/**
	 * En este metodo tenemos "hard coding" de la verificacion del nombre de usuario y pwd(en formato MD5)
	 * @param userName
	 * @param password
	 * @return
	 */
	private boolean checkUser(String user,String pwd){
		//TODO leer de archivo y comprobar md5 y user
		String pass=SecurityUtils.getMd5Data(pwd);
		if(userName.equals(getUserName())){
			if(pass.equals(getPassword())){
				return true;
			}
		}
		return false;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	protected void additionalAuthenticationChecks(UserDetails arg0,
			UsernamePasswordAuthenticationToken arg1)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		
	}

}
