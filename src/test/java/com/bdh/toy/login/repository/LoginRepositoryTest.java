package com.bdh.toy.login.repository;

import com.bdh.toy.login.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LoginRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("유저 저장 성공")
    @Test
    public void createMemberTest(){
        // given
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Member member = Member.builder()
                .userId("admin")
                .password(passwordEncoder.encode("admin1234"))
                .build();

        System.out.println(passwordEncoder.encode("admin1234"));
        // when
        Member member1 = memberRepository.save(member);

        // then
        assertThat(member1).isNotNull();
        assertThat(member1.getUserId()).isEqualTo("admin");
    }

    @DisplayName("유저 가져오기")
    @Test
    public void getMemberTest(){
        // given
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Member member = Member.builder()
                .userId("admin")
                .password(passwordEncoder.encode("admin1234"))
                .build();

        // when
        Member member1 = memberRepository.findByUserId("admin");

        // then
        assertThat(member1).isNotNull();
        assertThat(member1.getUserId()).isEqualTo(member.getUserId());
        assertThat(passwordEncoder.matches("admin1234", member1.getPassword())).isTrue();
    }
}
