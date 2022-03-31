package ru.ivozhlyakov.exercise10.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.ivozhlyakov.exercise10.config.jwt.JwtCustomAuthenticationFilter;
import ru.ivozhlyakov.exercise10.config.jwt.JwtCustomAuthorizationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtCustomAuthenticationFilter jwtFilter = new JwtCustomAuthenticationFilter(authenticationManagerBean());
        jwtFilter.setFilterProcessesUrl("/login");

        http.csrf().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

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
                .antMatchers(HttpMethod.PATCH,
                        "/books/**"
                        , "/authors/**"
                        , "/comments/**"
                        , "/genres/**"
                ).hasRole("ADMIN")
                .and()
                .logout()
                .and()
                .addFilter(jwtFilter)
                .addFilterBefore(new JwtCustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
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
