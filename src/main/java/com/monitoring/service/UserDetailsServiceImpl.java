package com.monitoring.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.monitoring.dto.User;
import com.monitoring.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userRecord = userRepository.findByUsername(username);

		if (userRecord.isPresent()) {
			User user = userRecord.get();
			Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
//			for (Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
//			}
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					grantedAuthorities);
		} else {
			logger.error("User not found corresponding to username {} !!!", username);
			throw new UsernameNotFoundException("User not found !!!");
		}


	}

}
