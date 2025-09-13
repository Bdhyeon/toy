package com.bdh.toy.config;

import com.bdh.toy.entity.Role;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrfConfig) ->
                        csrfConfig.disable()
                )
                .headers((headersConfig) ->
                        headersConfig.frameOptions(frameOptionsConfig ->
                                frameOptionsConfig.disable()
                        )
                )
                .authorizeHttpRequests(authorizeHttpRequestsConfig ->
                        authorizeHttpRequestsConfig
                                .requestMatchers(PathRequest.toH2Console()).permitAll()
                                .requestMatchers("/", "/login/**").permitAll()
                                .requestMatchers("/book/**").hasRole(Role.USER.name())
                                .anyRequest().authenticated(
                                )
                );

        return http.build();
    }
}
