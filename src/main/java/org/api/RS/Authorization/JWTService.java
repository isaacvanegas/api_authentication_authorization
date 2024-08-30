package org.api.RS.Authorization;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.api.entity.security.User;
import org.api.request.ObjectRQ;
import org.api.request.authentication.AuthenticationRequestDto;
import org.api.response.authentication.AuthenticationResponse;
import org.api.service.security.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Dependent
public class JWTService {
    private static final Logger LOG = LoggerFactory.getLogger(JWTService.class);

    private final UserService userService;

    @Inject
    public JWTService(UserService userService) {
        this.userService = userService;
    }

    public AuthenticationResponse generateJwt(ObjectRQ<AuthenticationRequestDto> rq) {
        LOG.info("generate jwt: " + rq.context().getToken());

        AuthenticationResponse r = new AuthenticationResponse();

        List<User> verific = userService.verificarUsuario(rq.data().getUserName(),rq.data().getPassword());

//        if (c != null) {
//            List<UserRol> userRols = userTransacational.getRolesByIdUsuario(c.getUserId().getId());
//            r.setGenerateJwt(true);
//            r.setJwt(Jwt.issuer("igroup")
//                    .subject(c.getUserId().getUserName())
//                    .groups(new HashSet<>(userRols.stream()
//                            .map(ur -> ur.getRolId().getName())
//                            .collect(Collectors.toList())))
//                    .expiresAt(System.currentTimeMillis() + 3600).sign());
//
//        } else {
//            LOG.warn("generate jwt denied token: " +rq.context().getToken());
//            r.setGenerateJwt(false);
//            r.setError((List<ErrorType>) ErrorType.unauthorizedGenerateJWT("Sin autorización para generar token"));
//        }
        return r;
    }
}
