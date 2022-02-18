package ru.ivozhlyakov.exercise11;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import ru.ivozhlyakov.exercise11.domain.User;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@IntegrationComponentScan
@Configuration
@EnableIntegration
@RequiredArgsConstructor
public class Exercise11Application {

    @Qualifier("noValidaPasswordChannel")
    private final PublishSubscribeChannel noValidaPasswordChannel;

    @PostConstruct
    public void subscribeOnChannels() {
        noValidaPasswordChannel.subscribe(message ->
                System.out.printf("[NO VALID PASSWORD] Пользователь %s  не прошел проверку пароля ", ((User) message.getPayload()).getUsername()));
    }

    private static final List<User> userList = Arrays.asList(
            User.builder()
                    .username("FirstUser")
                    .password("1234q")
                    .build(),
            User.builder()
                    .username("SecondUser")
                    .password("1234q")
                    .build()
    );

    @SneakyThrows
    public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Exercise11Application.class, args);

        RegistrationGateway gateway = context.getBean(RegistrationGateway.class);

            System.out.println(
                    "Registration users: " +
                            userList.stream().map( User::getUsername )
                    .collect( Collectors.joining( "," ) )
            );

        userList.forEach(user -> {
            System.out.println("\nReady user "+user.getUsername());
            gateway.registration(user);
        });

	}

}
