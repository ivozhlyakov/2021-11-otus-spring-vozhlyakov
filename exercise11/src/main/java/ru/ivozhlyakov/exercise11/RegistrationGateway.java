package ru.ivozhlyakov.exercise11;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.ivozhlyakov.exercise11.domain.Registration;
import ru.ivozhlyakov.exercise11.domain.User;

@MessagingGateway
public interface RegistrationGateway {

    @Gateway(requestChannel = "requestRegistrationChannel", replyChannel = "responseRegistrationChannel")
    Registration registration(User user);
}
