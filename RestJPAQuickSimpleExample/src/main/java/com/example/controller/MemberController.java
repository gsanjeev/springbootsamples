package com.example.controller;

import com.example.model.Member;
import com.example.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping(value= "/member")
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @PostMapping(value="/member")
    public Member save(@RequestBody final Member member) {
        memberRepository.save(member);
        return memberRepository.findByName(member.getName());
    }

    @GetMapping(value="/member/search/{name}")
    public Member search(@PathVariable final String name) {
        return memberRepository.findByName(name);
    }
}


