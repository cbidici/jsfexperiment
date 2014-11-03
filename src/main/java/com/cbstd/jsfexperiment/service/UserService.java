package com.cbstd.jsfexperiment.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.cbstd.jsfexperiment.entity.User;
import com.cbstd.jsfexperiment.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	public User createUser(User user) {

		Assert.notNull(user.getUsername());

		if (!checkAvailableUsername(user.getUsername())) {
			return null;
		}

		String plainPass = user.getPassword();
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(plainPass));
		System.out.println("Register : " + user.getPassword());
		userRepository.save(user);
		user.setPassword(plainPass);
		return user;
	}
	
	public boolean checkAvailableUsername(String username)
	{
		boolean result = false;
		
		User user = userRepository.findByUsername(username);
		if(null == user)
		{
			result = true;
		}
		
		return result;
	}

	public User findByUsername(String username)
	{
		return userRepository.findByUsername(username);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (null == user) {
			throw new UsernameNotFoundException(String.format(
					getMessageBundle().getString("userdoesnotexist"), username));
		}

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), authorities);
		
		return userDetails;
	}
	
	private ResourceBundle getMessageBundle() {
		return ResourceBundle.getBundle("message-labels");
	}
	

}
