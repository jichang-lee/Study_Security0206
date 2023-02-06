package org.spring.security02.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.spring.security02.constant.Role;
import org.spring.security02.entity.MemberEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

@Getter
@Setter
@ToString
public class UserSecurity extends User {

    private MemberEntity memberEntity;

    public UserSecurity(MemberEntity memberEntity){
        super(memberEntity.getEmail(),
              memberEntity.getPassword(),
                AuthorityUtils.createAuthorityList(memberEntity.getRole().toString()));

        this.memberEntity = memberEntity;
    }



}
