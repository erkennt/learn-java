package learn.second.domain.service.security;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import learn.second.domain.model.Users;
import learn.second.domain.service.auth.AuthObjectService;
@Component
public class CommonUsersService implements UserDetailsService {

    @Autowired
    AuthObjectService authObjectServie;

    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        try {
	        	Users users = authObjectServie.findOne(username);
	            return new CommonUsers(users);
	        } catch (ResourceNotFoundException e) {
	            throw new UsernameNotFoundException("user not found", e);
	        }
	    }
}