package org.api.repository.security;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.api.entity.security.User;
import org.api.entity.security.UserRol;

import java.util.ArrayList;
import java.util.List;


public class UserRepository implements PanacheRepository<User> {


    public List<User> verificarUsuario(String userName, String password) {

        // Crear consulta
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);

        // Realizar join con la tabla UserRol
        Join<User, UserRol> userRolesJoin = root.join("userRoles", JoinType.LEFT);

        // Lista de predicados para la cláusula WHERE
        List<Predicate> predicates = new ArrayList<>();

        // Comparar nombre de usuario
        predicates.add(builder.equal(root.get("name"), userName));

        // Comparar contraseña (se asume que está encriptada)
        predicates.add(builder.equal(root.get("password"), password));

        // Crear la consulta con las condiciones
        query.select(root).where(predicates.toArray(new Predicate[0])).distinct(true);

        // Ejecutar la consulta
        TypedQuery<User> typedQuery = getEntityManager().createQuery(query);

        // Retornar la lista de usuarios con roles
        return typedQuery.getResultList();
    }


}
