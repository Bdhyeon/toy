package com.bdh.toy.login.dto;

import com.bdh.toy.login.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterDTO {

    private String registerId;
    private String registerPassword;
    private String registerEmail;
    private Role role;


}
