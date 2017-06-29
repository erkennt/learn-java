package learn.second.domain.service.auth;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import learn.second.domain.model.Admin;
import learn.second.domain.reposiory.security.AdminAuthRepository;
@Component
public class AuthAdminObjectServiceImpl implements AuthAdminObjectService {
    @Autowired
    AdminAuthRepository adminAuthRepository;

    @Transactional(readOnly=true)
    @Override
    public Admin findOne(String username) {
    	Admin account = adminAuthRepository.findOne(username);
        if (account == null) {
            throw new ResourceNotFoundException("The given account is not found! username="
                    + username);
        }
        return account;
    }
}
