package com.bdh.toy.login.service;

import com.bdh.toy.login.dto.RegisterDTO;
import com.bdh.toy.login.entity.Member;
import com.bdh.toy.login.entity.Role;
import com.bdh.toy.login.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberRegisterService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean register(RegisterDTO dto){
        Member member = Member.builder()
                .userId(dto.getRegisterId())
                .password(passwordEncoder.encode(dto.getRegisterPassword()))
                .email(dto.getRegisterEmail())
                .roleId(Role.USER)
                .role("USER")
                .build();
        memberRepository.save(member);
        return true;
    }
}
