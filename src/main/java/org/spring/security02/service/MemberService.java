package org.spring.security02.service;

import lombok.RequiredArgsConstructor;
import org.spring.security02.constant.Role;
import org.spring.security02.dto.MemberDto;
import org.spring.security02.entity.MemberEntity;
import org.spring.security02.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    public void insert(MemberDto memberDto) {

        MemberEntity memberEntity = MemberEntity.toMemberGet(memberDto, passwordEncoder);

          memberRepository.save(memberEntity);

    }

    //all list
    public List<MemberDto> memberList() {

        List<MemberDto> dtoList = new ArrayList<>();

        List<MemberEntity> entityList = memberRepository.findAll();

        for (MemberEntity memberEntity : entityList) {
            dtoList.add(MemberDto.toGetDto(memberEntity));
        }
        return dtoList;
    }


    public List<MemberDto> memberSearch(String search) {
        List<MemberDto> dtoList = new ArrayList<>();

        List<MemberEntity> entityList = memberRepository.findByEmailContaining(search);

        for (MemberEntity memberEntity : entityList) {
            dtoList.add(MemberDto.toGetDto(memberEntity));
        }
        return dtoList;
    }

    //page
    public Page<MemberDto> memberPage(Pageable pageable) {
        Page<MemberEntity> memberEntities = memberRepository.findAll(pageable);

        Page<MemberDto> memberDtos = memberEntities.map(MemberDto::toGetDto);

        return memberDtos;

    }

    public MemberDto memberDetail(Long id) {
        Optional<MemberEntity> memberEntity = memberRepository.findById(id);

        if (!memberEntity.isPresent()) {
            return null;
        }
        return MemberDto.toGetDto(memberEntity.get());
    }


//    public MemberDto updateSearch(Long id) {
//        Optional<MemberEntity> memberEntity = memberRepository.findById(id);
//
//        if (!memberEntity.isPresent()) {
//            return null;
//        }
//        return MemberDto.toGetDto(memberEntity.get());
//    }
//
//
//    @Transactional
//    public void update(MemberDto memberDto) {
//
//        MemberEntity memberEntity = MemberEntity.toIdMemberGet(memberDto, passwordEncoder);
//
//        memberRepository.save(memberEntity);
//
//    }

}
