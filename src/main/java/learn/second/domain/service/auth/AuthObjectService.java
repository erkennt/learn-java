package learn.second.domain.service.auth;

import learn.second.domain.model.Users;

public interface  AuthObjectService {
    Users findOne(String username);
}
