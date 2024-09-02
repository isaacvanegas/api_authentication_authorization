package org.api.service.security;

import org.api.entity.security.User;
import org.api.entity.security.UserRol;

import java.util.List;

public interface UserService {
    List<UserRol> verificarUsuario(String userName, String password);
}
