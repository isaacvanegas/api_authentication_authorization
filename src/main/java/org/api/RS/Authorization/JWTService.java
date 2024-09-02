package org.api.RS.Authorization;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.api.entity.security.User;
import org.api.entity.security.UserRol;
import org.api.request.ObjectRQ;
import org.api.request.authentication.AuthenticationRequestDto;
import org.api.response.ErrorType;
import org.api.response.authentication.AuthenticationResponse;
import org.api.service.security.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.api.response.ErrorType.unauthorizedGenerateJWT;

@Singleton
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

        List<UserRol> userRols = userService.verificarUsuario(rq.data().getUserName(),rq.data().getPassword());

        if (userRols != null) {

            r.setGenerateJwt(true);
            r.setJwt(Jwt.issuer("igroup")
                    .subject(userRols.get(0).getUserId().getName())
                    .groups(new HashSet<>(userRols.stream()
                            .map(ur -> ur.getRolId().getDescription())
                            .collect(Collectors.toList())))
                    .expiresAt(System.currentTimeMillis() + 3600).sign());

        } else {
            LOG.warn("generate jwt denied token: " +rq.data().getUserName());
            r.setGenerateJwt(false);
            r.setError((List<ErrorType>) unauthorizedGenerateJWT("Sin autorización para generar token"));
        }
        return r;
    }
}
