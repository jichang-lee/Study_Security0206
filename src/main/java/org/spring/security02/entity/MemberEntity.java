package org.spring.security02.entity;

import lombok.*;
import org.spring.security02.constant.Role;
import org.spring.security02.dto.MemberDto;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "member_sec2")
public class MemberEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    public static MemberEntity toMemberGet(MemberDto memberDto, PasswordEncoder passwordEncoder){
         return MemberEntity.builder()
                .email(memberDto.getEmail())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .role(Role.MEMBER)
                 .build();
    }
//    public static MemberEntity toIdMemberGet(MemberDto memberDto){
//        return MemberEntity.builder()
//                .id(memberDto.getId())
//                .email(memberDto.getEmail())
//                .password(memberDto.getPassword())
//                .role(memberDto.getRole())
//                .build();
//    }

    public static MemberEntity toAdminGet(MemberDto memberDto, PasswordEncoder passwordEncoder){
         return MemberEntity.builder()
                .email(memberDto.getEmail())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .role(Role.ADMIN)
                 .build();
    }

    public static MemberEntity toManagerGet(MemberDto memberDto, PasswordEncoder passwordEncoder){
         return MemberEntity.builder()
                .email(memberDto.getEmail())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .role(Role.MANAGER)
                 .build();
    }


}
