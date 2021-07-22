package com.spring.project.root.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.spring.project.root.dataacess.User;
import com.spring.project.root.services.UserService;

@Component
public class AdminAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;

	private static final String ERROR_MESSAGE = "Invalid username or password";

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		if (StringUtils.isBlank(authentication.getName())) {
			throw new BadCredentialsException(AdminAuthenticationProvider.ERROR_MESSAGE);
		}
		final String email = authentication.getName();
		final String password = authentication.getCredentials().toString();
		final User user = this.userService.getList().stream().filter(u -> u.match(email, password)).findFirst().orElse(null);
		if (user != null) {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
			Authentication auth = new UsernamePasswordAuthenticationToken(email, password, grantedAuthorities);
			return auth;
		} else {
			throw new BadCredentialsException(AdminAuthenticationProvider.ERROR_MESSAGE);
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}

