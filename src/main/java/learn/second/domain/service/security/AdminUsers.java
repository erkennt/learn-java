package learn.second.domain.service.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import learn.second.domain.model.Admin;


public class AdminUsers  extends User {

    private static final long serialVersionUID = 1L;

    private Admin users;

    public AdminUsers(Admin users){

        super(users.getAdminId(), users.getPassword(), AuthorityUtils
                .createAuthorityList("ROLE_ADMIN"));
        this.users = users;
    }

    public Admin getUser() {
        return users;
    }

    public void reloadUser(Admin newUsers) {
    	users = newUsers;
    }

}
