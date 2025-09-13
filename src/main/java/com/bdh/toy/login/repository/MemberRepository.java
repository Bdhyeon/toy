package com.bdh.toy.login.repository;

import com.bdh.toy.login.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByUserId(String userId);
}
