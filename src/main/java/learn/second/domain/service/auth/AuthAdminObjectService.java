package learn.second.domain.service.auth;

import learn.second.domain.model.Admin;

public interface  AuthAdminObjectService {
	Admin findOne(String username);
}
