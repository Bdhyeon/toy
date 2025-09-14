package com.bdh.toy.login.service;

import com.bdh.toy.login.dto.MemberDTO;
import com.bdh.toy.login.entity.Member;
import com.bdh.toy.login.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserId(userId);

        if (member == null) {
            throw  new UsernameNotFoundException(userId);
        }

        member.setRoleId();
        MemberDTO dto =  new MemberDTO();
        dto.setUsername(member.getUserId());
        dto.setPassword(member.getPassword());
        dto.setRole(member.getRoleId());

        return dto;
    }
}
