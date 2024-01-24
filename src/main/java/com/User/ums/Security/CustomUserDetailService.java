package com.User.ums.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.User.ums.Entity.User;
import com.User.ums.Repository.UserRepository;
@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(userName).orElseThrow(()-> new UsernameNotFoundException("user is no present"));
		return new CustomerUserDetails(user);
		//return userRepo.findByUserName(userName).map(user ->new CustomUserDetails(user)).orElseThrow{
		//() -> UserNameNotFoundException("Failed to authenticate the user"));
	}

}
