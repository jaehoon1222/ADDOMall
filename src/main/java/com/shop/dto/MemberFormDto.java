package com.shop.dto;

import com.shop.constant.Role;
import com.shop.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class MemberFormDto {
    private Long id;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    private String password;

    @NotEmpty(message = "주소는 필수 입력 값입니다.")
    private String zipCode;

    private String stAdr;


    private String dtlAdr;

    @NotEmpty(message = "전화번호는 필수 입력 값입니다.")
    private String tel;

    @NotEmpty(message = "이메일 인증은 필수입니다.")
    private String check;
    @NotEmpty(message = "비밀번호 확인은 필수입니다.")
    private String check2;

    private Role role;
    private static ModelMapper modelMapper = new ModelMapper();

    public static MemberFormDto of(Member member){
        return modelMapper.map(member, MemberFormDto.class);
    }
}
