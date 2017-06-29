package learn.second.domain.reposiory.security;

import learn.second.domain.model.Admin;


public interface  AdminAuthRepository {
	Admin findOne(String username);
}
