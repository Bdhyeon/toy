package com.bdh.toy.login.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@RequiredArgsConstructor
public class MemberAuthenticationProvider implements AuthenticationProvider {
    private final MemberDetailService memberDetailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String userId = authentication.getName();
        UserDetails userDetails = memberDetailService.loadUserByUsername(userId);

        String rawPassword = authentication.getCredentials().toString();
        String hashPassword = userDetails.getPassword();
        try {
            checkPassword(rawPassword, hashPassword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new UsernamePasswordAuthenticationToken(userDetails, rawPassword, userDetails.getAuthorities());
    }

    private void checkPassword(String rawPassword, String hashPassword) throws Exception {
        boolean isPasswordMatch = passwordEncoder.matches(rawPassword, hashPassword);
        if (!isPasswordMatch) {
            throw new Exception("password mismatch");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
