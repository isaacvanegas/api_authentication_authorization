package org.api.service.security;

import org.api.entity.security.User;

import java.util.List;

public interface UserService {
    List<User> verificarUsuario(String userName, String password);
}
