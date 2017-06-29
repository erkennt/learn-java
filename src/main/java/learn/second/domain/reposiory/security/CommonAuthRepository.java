package learn.second.domain.reposiory.security;

import learn.second.domain.model.Users;


public interface  CommonAuthRepository {
	Users findOne(String username);
}
