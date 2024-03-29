package com.shop.entity;

import com.shop.constant.Role;
import com.shop.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member extends BaseEntity{
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String zipCode;
    private String stAdr;
    private String dtlAdr;

    private String tel;
    @Enumerated(EnumType.STRING)
    private Role role;
    public static Member createMember(MemberFormDto memberFormDto,
                                      PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setZipCode(memberFormDto.getZipCode());
        member.setStAdr(memberFormDto.getStAdr());
        member.setDtlAdr(memberFormDto.getDtlAdr());
        member.setTel(memberFormDto.getTel());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(memberFormDto.getRole());
        return member;
    }

    public void updateMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        this.id = memberFormDto.getId();
        this.name = memberFormDto.getName();
        this.email = memberFormDto.getEmail();
        this.password = passwordEncoder.encode(memberFormDto.getPassword());
        this.zipCode = memberFormDto.getZipCode();
        this.stAdr = memberFormDto.getStAdr();
        this.dtlAdr = memberFormDto.getDtlAdr();
        this.tel = memberFormDto.getTel();
        setRole(Role.USER);
    }

}
