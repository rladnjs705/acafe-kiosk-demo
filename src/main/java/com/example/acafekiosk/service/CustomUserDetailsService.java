package com.example.acafekiosk.service;

import com.example.acafekiosk.entity.Users;
import com.example.acafekiosk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@RequiredArgsConstructor
//@Transactional
public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	//메서드가 자동으로 불림.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Email: " + username + " not found"));
		return new org.springframework.security.core.userdetails.User(user.getEmail(),
				user.getPassword(), getAuthorities(user));

	}

	private static Collection<? extends GrantedAuthority> getAuthorities(Users user){
		String[] userRoles = user.getUserRoles()
				.stream()
				.map((role) -> role.getRoles().getRolename())
				.toArray(String[]::new);

		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
		return authorities;
	}
}
