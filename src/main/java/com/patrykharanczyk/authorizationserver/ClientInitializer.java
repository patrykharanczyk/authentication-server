package com.patrykharanczyk.authorizationserver;

import com.patrykharanczyk.authorizationserver.repository.JpaRegisteredClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ClientInitializer implements SmartInitializingSingleton {
    private final PasswordEncoder passwordEncoder;
    private final RegisteredClientRepository clientRepository;

    public ClientInitializer(PasswordEncoder passwordEncoder, JpaRegisteredClientRepository clientRepository) {
        this.passwordEncoder = passwordEncoder;
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public void afterSingletonsInstantiated() {
        System.out.println("Initializing clients");
        RegisteredClient client = RegisteredClient
                .withId("resolutions-client")
                .clientId("resolutions-client")
                .clientSecret(passwordEncoder.encode("resolutions123"))
                .clientIdIssuedAt(Instant.now())
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scope("resolutions:read")
                .scope("resolutions:write")
                .build();


        clientRepository.save(client);
        System.out.println("Initializing clients finished");
    }
}
