package com.bdh.toy.login.dto;

import com.bdh.toy.login.entity.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterDTO {

    @NotBlank(message = "id를 입력해주세요!")
    private String registerId;
    @NotBlank(message = "비밀번호를 입력해주세요!")
    private String registerPassword;
    @NotBlank(message = "이메일을 입력해주세요!")
    private String registerEmail;
    private Role role;


}
