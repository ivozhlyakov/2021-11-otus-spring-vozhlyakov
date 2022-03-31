package ru.ivozhlyakov.exercise11.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Registration {

    @Builder.Default
    private UUID id = UUID.randomUUID();
    private User user;
    private boolean isRegistration;


}
