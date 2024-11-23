package com.devops.user_service_api_1.service.impl;

import com.devops.user_service_api_1.dto.request.RequestSystemUserDto;
import com.devops.user_service_api_1.entity.SystemUser;
import com.devops.user_service_api_1.exceptions.DuplicateEntryException;
import com.devops.user_service_api_1.repo.SystemUserRepo;
import com.devops.user_service_api_1.service.SystemUserService;
import com.devops.user_service_api_1.util.KeycloakSecurityUtil;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SystemUserServiceImpl implements SystemUserService {

    private final KeycloakSecurityUtil keycloakSecurityUtil;
    private final SystemUserRepo systemUserRepo;

    @Value("${keycloak.config.realm}")
    private String realm;
    @Value("${keycloak.config.client-id}")
    private String clientId;
    @Value("${keycloak.config.secret}")
    private String secret;
    @Value("${spring.security.oauth2.resourceserver.jwt.token-uri}")
    private String keyCloakApiUrl;

    @Override
    public void signup(RequestSystemUserDto requestSystemUserDto) {

        String userId;
        Keycloak keycloak = null;

        UserRepresentation existingUser = null;

        keycloak =  keycloakSecurityUtil.getKeycloakInstance();

        System.out.println(requestSystemUserDto.getEmail());
        System.out.println(requestSystemUserDto.getPassword());
        System.out.println(requestSystemUserDto.getFirstName());
        System.out.println(requestSystemUserDto.getLastName());
        existingUser = keycloak.realm(realm).users().search(requestSystemUserDto.getEmail()).stream().findFirst().orElse(null);

        if(existingUser != null){
            Optional<SystemUser> existsSystemUserData = systemUserRepo.findByEmail(existingUser.getEmail());
            if(existsSystemUserData.isEmpty()){
                keycloak.realm(realm).users().search(existingUser.getId());
            }
            else {
                throw new DuplicateEntryException("user already exist");
            }
        }
        else{
            Optional<SystemUser> existsSystemUserData = systemUserRepo.findByEmail(requestSystemUserDto.getEmail());
            if(existsSystemUserData.isPresent()){
                systemUserRepo.deleteById(existsSystemUserData.get().getUserId());
            }
        }

        UserRepresentation userRep = mapUserRepo(requestSystemUserDto);
        Response response = keycloak.realm(realm).users().create(userRep);
        if(response.getStatus() == Response.Status.CREATED.getStatusCode()){
            System.out.println("hey..");
            RoleRepresentation roleRepresentation = keycloak.realm(realm).roles().get("user").toRepresentation();
            userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
            keycloak.realm(realm).users().get(userId).roles().realmLevel().add(Arrays.asList(roleRepresentation));

            SystemUser systemUser = SystemUser.builder()
                    .userId(UUID.randomUUID().toString())
                    .email(requestSystemUserDto.getEmail())
                    .fullName(requestSystemUserDto.getFirstName() + " " + requestSystemUserDto.getLastName())
                    .status(true)
                    .isAccountNonExpired(true)
                    .isAccountNonLocked(true)
                    .isCredentialNonExpired(true)
                    .isEnabled(true)
                    .build();

            systemUserRepo.save(systemUser);
        }

    }

    private UserRepresentation mapUserRepo(RequestSystemUserDto requestSystemUserDto){
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(requestSystemUserDto.getEmail());
        userRepresentation.setFirstName(requestSystemUserDto.getFirstName());
        userRepresentation.setLastName(requestSystemUserDto.getLastName());
        userRepresentation.setEmail(requestSystemUserDto.getEmail());
        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);
        ArrayList<CredentialRepresentation> creds = new ArrayList<>();
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setValue(requestSystemUserDto.getPassword());
        creds.add(credentialRepresentation);
        userRepresentation.setCredentials(creds);
        return userRepresentation;
    }

    @Override
    public void login(String username, String password) {

    }
}
