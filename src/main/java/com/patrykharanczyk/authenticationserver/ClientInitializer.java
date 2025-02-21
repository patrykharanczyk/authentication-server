package com.patrykharanczyk.authenticationserver;

import com.patrykharanczyk.authenticationserver.repository.JpaRegisteredClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ClientInitializer implements SmartInitializingSingleton {
    RegisteredClientRepository clientRepository;

    public ClientInitializer(JpaRegisteredClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public void afterSingletonsInstantiated() {
        System.out.println("Initializing clients");
        RegisteredClient client = RegisteredClient
                .withId("resolutions-client")
                .clientId("resolutions-client")
                .clientSecret("{noop}secret")
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
