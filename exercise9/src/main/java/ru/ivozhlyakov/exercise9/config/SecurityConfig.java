package ru.ivozhlyakov.exercise9.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,
                        "/books"
                        , "/authors"
                        , "/comments"
                        , "/genres"
                ).hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,
                        "/books"
                        , "/authors"
                        , "/comments"
                        , "/genres"
                ).hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,
                        "/books/*"
                        , "/authors/*"
                        , "/comments/*"
                        , "/genres/*"
                ).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,
                        "/books/*"
                        , "/authors/*"
                        , "/comments/*"
                        , "/genres/*"
                ).hasRole("ADMIN")
                .and()
                .formLogin()
//                .failureForwardUrl("/error")
                .and()
                .logout()
                .and()
                .rememberMe()
                .key("key_secret")
                .tokenValiditySeconds(60 * 60 * 24);
    }

    @Override
    public void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(13);
    }
}
