package org.spring.security02.config;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailSecurity userDetailSecurity;
    private final AuthenticationFailureHandler failureHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.csrf().disable(); //보안방지

        http.authorizeHttpRequests()
                        .antMatchers("/","/member/join","/member/login","/member/fail","/user/**").permitAll()
                        .antMatchers("/css/**","/js/**","/img/**").permitAll()
                        .antMatchers("/member/user/**").authenticated() //로그인시 접근 가능
                        .antMatchers("/member/user/**").hasAnyRole("MEMBER","ADMIN","MANAGER")
                        .antMatchers("/admin/**").hasAnyRole("ADMIN")
                        .antMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN");


        http.formLogin()
                .loginPage("/member/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .loginProcessingUrl("/member/loginOk")
                .failureHandler(failureHandler)
                .and()

                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/");

        http.userDetailsService(userDetailSecurity);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
