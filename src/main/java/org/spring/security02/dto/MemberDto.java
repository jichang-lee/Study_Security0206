package org.spring.security02.dto;

import lombok.*;
import org.spring.security02.constant.Role;
import org.spring.security02.entity.MemberEntity;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {


    private Long id;

    @NotBlank(message = "email 입력필수")
    private String email;

    @NotBlank(message = "password 입력필수")
    @Size(min = 3,max = 20)
    private String password;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private Role role;

    public static MemberDto toGetDto(MemberEntity memberEntity){
       return MemberDto.builder()
                .id(memberEntity.getId())
                .email(memberEntity.getEmail())
                .password(memberEntity.getPassword())
                .createTime(memberEntity.getCreateTime())
                .updateTime(memberEntity.getUpdateTime())
                .role(memberEntity.getRole())
                .build();

    }


}
