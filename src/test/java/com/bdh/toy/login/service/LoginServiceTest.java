package com.bdh.toy.login.service;

import com.bdh.toy.login.dto.RegisterDTO;
import com.bdh.toy.login.entity.Member;
import com.bdh.toy.login.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MemberRegisterService memberRegisterService;

    @Test
    void registerTest() {
        RegisterDTO dto = new RegisterDTO();
        dto.setRegisterId("testuser");
        dto.setRegisterPassword("password123");
        dto.setRegisterEmail("test@test.com");

        when(passwordEncoder.encode(dto.getRegisterPassword())).thenReturn("encodedPassword");

        boolean result = memberRegisterService.register(dto);

        verify(memberRepository, times(1)).save(any(Member.class));
        System.out.println("회원가입 성공? " + result);
    }
}
