package org.spring.security02.config;

import org.spring.security02.entity.MemberEntity;
import org.spring.security02.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.Optional;

@Service
public class UserDetailSecurity implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<MemberEntity> memberEntity= memberRepository.findByEmail(email);

        if(!memberEntity.isPresent()){
            throw new UsernameNotFoundException("사용자 없음");
        }
        MemberEntity memberEntity1= memberEntity.get();

        return
            User.builder()
                .username(memberEntity1.getEmail())
                .password(memberEntity1.getPassword())
                .roles(memberEntity1.getRole().toString())
                .build();

   }


}
