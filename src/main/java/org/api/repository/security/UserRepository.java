package org.api.repository.security;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.api.entity.security.Rol;
import org.api.entity.security.User;
import org.api.entity.security.UserRol;
import org.api.serviceImpl.security.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    private static final Logger LOG = LoggerFactory.getLogger(UserRepository.class);

    public List<UserRol> verificarUsuario(String userName, String password) {


        String hql = "select ur from UserRol ur " +
                "left join fetch ur.userId u " +
                "left join fetch ur.rolId r " +
                "where u.name = :userName " +
                "and u.password = :password";


        return getEntityManager()
                .createQuery(hql, UserRol.class)
                .setParameter("userName", userName)
                .setParameter("password", password)
                .getResultList();

    }


}
