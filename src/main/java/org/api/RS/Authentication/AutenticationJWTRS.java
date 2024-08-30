package org.api.RS.Authentication;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.api.RS.Authorization.JWTService;
import org.api.entity.ContextData;
import org.api.request.RQ.AuthenticationRequestRQ;
import org.api.request.authentication.AuthenticationRequestDto;
import org.api.response.authentication.AuthenticationResponse;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/jwt")
@ApplicationScoped
public class AutenticationJWTRS {

    @HeaderParam("Token")
    private String token;

    @Inject
    JWTService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(
            operationId = "getJwt",
            summary = "autenticación y autorización",
            description = "Valida credenciales de usuario"
    )
    @APIResponse(
            responseCode = "200",
            description = "Verifica credenciales de usuario y retorna token de autorizacion."
    )
    public Response getJwt(
            @RequestBody(
                    required = true,
                    description = "Request de autenticacion.",
                    content = @Content(schema = @Schema(implementation = AuthenticationRequestDto.class))
            )
            AuthenticationRequestDto request) {
        AuthenticationResponse jwt =   service.generateJwt(AuthenticationRequestRQ.of(buildContext(), request));
        return Response.ok(jwt).build();
    }

    private ContextData buildContext() {
        return ContextData.builder()
                .setToken(token)
                .build();
    }



}
