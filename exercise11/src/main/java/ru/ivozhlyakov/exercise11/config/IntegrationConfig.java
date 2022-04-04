package ru.ivozhlyakov.exercise11.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import ru.ivozhlyakov.exercise11.domain.Registration;
import ru.ivozhlyakov.exercise11.domain.User;

@Configuration
public class IntegrationConfig {

    @Bean
    public QueueChannel requestRegistrationChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel responseRegistrationChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public PublishSubscribeChannel noValidaPasswordChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public IntegrationFlow checkPasswordPolicyFlow() {
        return IntegrationFlows.from("requestRegistrationChannel")
                .handle("passwordService", "check")
                .<User, Boolean>route(User::isPassValid, mapping -> mapping
                        .subFlowMapping(false, sf -> sf.channel("noValidaPasswordChannel"))
                        .subFlowMapping(true, sf -> sf.channel("registrationChannel")))
                .get();
    }

    @Bean
    public IntegrationFlow noValidaPasswordFlow() {
        return IntegrationFlows.from("noValidaPasswordChannel")
                .<User, Registration>transform(user -> Registration.builder()
                        .user(user)
                        .isRegistration(false)
                        .build())
                .channel("responseRegistrationChannel")
                .get();

    }

    @Bean
    public IntegrationFlow registrationFlow() {
        return IntegrationFlows.from("registrationChannel")
                .handle("registrationService", "registration")
                .channel("responseRegistrationChannel")
                .get();
    }
}
