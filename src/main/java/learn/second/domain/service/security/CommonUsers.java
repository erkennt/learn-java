package learn.second.domain.service.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import learn.second.domain.model.Users;

public class CommonUsers  extends User {

    private static final long serialVersionUID = 1L;

    private Users users;

    public CommonUsers(Users users){

        super(users.getUserId(), users.getPassword(), AuthorityUtils
                .createAuthorityList("ROLE_USER"));
        this.users = users;
    }

    public Users getUser() {
        return users;
    }

    public void reloadUser(Users newUsers) {
    	users = newUsers;
    }

}
