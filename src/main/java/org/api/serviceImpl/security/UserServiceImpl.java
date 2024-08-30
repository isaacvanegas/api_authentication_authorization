package org.api.serviceImpl.security;

import jakarta.enterprise.context.Dependent;
import jakarta.transaction.Transactional;
import org.api.entity.security.User;
import org.api.repository.security.UserRepository;
import org.api.service.security.UserService;

import java.util.List;

@Dependent
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }



    public List<User> verificarUsuario(String userName, String password){
        return userRepository.verificarUsuario(userName,password);
    }
}
