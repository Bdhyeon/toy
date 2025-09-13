package com.bdh.toy.config;

import com.bdh.toy.login.entity.Role;
import com.bdh.toy.login.service.LoginFailHandler;
import com.bdh.toy.login.service.LoginSuccessHandler;
import com.bdh.toy.login.service.MemberDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final int ONE_MONTH = 2678400;
    private final MemberDetailService memberDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable
                )
                .headers((headersConfig) ->
                        headersConfig.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable
                        )
                )
                .authorizeHttpRequests(authorizeHttpRequestsConfig ->
                        authorizeHttpRequestsConfig
                                .requestMatchers("/", "/login/**").permitAll()
                                .requestMatchers("/book/**").hasRole(Role.USER.name())
                                .anyRequest().authenticated()
                )
                .formLogin(formLoginConfig ->
                        formLoginConfig.loginPage("/login")
                                .loginProcessingUrl("/account/login")
                                .usernameParameter("userId")
                                .passwordParameter("password")
                                .successHandler(new LoginSuccessHandler())
                                .failureHandler(new LoginFailHandler())
                                .permitAll())
                .rememberMe(customizer -> customizer
                        .rememberMeParameter("remember-me")
                        .tokenValiditySeconds(ONE_MONTH)
                        .authenticationSuccessHandler(new LoginSuccessHandler())
                        .userDetailsService(memberDetailService))
                .logout(customizer -> customizer
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/logout")
                        .deleteCookies("remember-me")
                        .permitAll()
                );

        return http.build();
    }
}
