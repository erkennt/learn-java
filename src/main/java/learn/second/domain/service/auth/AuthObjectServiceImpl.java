package learn.second.domain.service.auth;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import learn.second.domain.model.Users;
import learn.second.domain.reposiory.security.CommonAuthRepository;
@Component
public class AuthObjectServiceImpl implements AuthObjectService {
    @Autowired
    CommonAuthRepository commonAuthRepository;

    @Transactional(readOnly=true)
    @Override
    public Users findOne(String username) {
    	Users account = commonAuthRepository.findOne(username);
        if (account == null) {
            throw new ResourceNotFoundException("The given account is not found! username="
                    + username);
        }
        return account;
    }
}
