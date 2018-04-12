package com.auth0.samples.authapi.user;

import java.util.Collections;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private ApplicationUserRepository applicationUserRepository;

	public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository) {
		this.applicationUserRepository = applicationUserRepository;
	}

	/**
	 * When a user tries to authenticate, this method receives the username.
	 *
	 * If we found a record, the properties of the returned instance are then checked against the credentials
	 * passed by the user in the login request.
	 *
	 * The last process is executed outside this class, by the Spring Security Framework
	 *
	 * @param username
	 * @return instance of {@code org.springframework.security.core.userdetails.User}
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
		if (applicationUser == null) {
			throw new UsernameNotFoundException(username);
		}

		return new User(applicationUser.getUsername(), applicationUser.getPassword(), Collections.emptyList());
	}
}
