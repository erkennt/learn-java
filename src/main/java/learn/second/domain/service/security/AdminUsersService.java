package learn.second.domain.service.security;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import learn.second.domain.model.Admin;
import learn.second.domain.service.auth.AuthAdminObjectService;
@Component
public class AdminUsersService implements UserDetailsService {

    @Autowired
    AuthAdminObjectService authAdminObjectServie;
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        try {
	        	Admin users = authAdminObjectServie.findOne(username);
	            return new AdminUsers(users);
	        } catch (ResourceNotFoundException e) {
	            throw new UsernameNotFoundException("user not found", e);
	        }
	    }
}
