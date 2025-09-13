package com.bdh.toy.login.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(length = 200)
    private String userId;
    @Column(length = 200)
    private String password;
    @Column(length = 200)
    private String email;
    @Column
    private Integer failCnt;
    @Column
    private LocalDate registeredAt;
    @Column
    private String role;

    @Transient
    private Role roleId;

    private void setRole(String role){
        this.role = role;
        this.roleId = Role.valueOf(role);
    }
}
