package com.devops.user_service_api_1.util;

import jakarta.ws.rs.core.Form;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakSecurityUtil {

    private Keycloak keycloak;

    @Value("${keycloak.config.server-url}")
    private String serverUrl;
    @Value("${keycloak.config.client-id}")
    private String clientId;
    @Value("${keycloak.config.grant-type}")
    private String grantType;
    @Value("${keycloak.config.password}")
    private String password;
    @Value("${keycloak.config.name}")
    private String username;
    @Value("${keycloak.config.secret}")
    private String secret;
    @Value("${keycloak.config.realm}")
    private String realm;

    public Keycloak getKeycloakInstance(){

        if(keycloak == null){
            keycloak = KeycloakBuilder.builder()
                    .clientId(clientId)
                    .grantType(grantType)
                    .username(username)
                    .password(password)
                    .serverUrl(serverUrl)
                    .clientSecret(secret)
                    .realm(realm)
                    .build();
        }

        return keycloak;
    }

}
