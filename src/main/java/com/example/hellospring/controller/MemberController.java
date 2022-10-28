package com.example.hellospring.controller;

import com.example.hellospring.domain.Member;
import com.example.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


// @Controller 가 있다면 Spring container 가 객체를 생성해서 container가 들고있는다. Spring bean 이 관리된다고 표현한다.
@Controller
public class MemberController {

    private final MemberService memberService;

    // memberService는 딱 1개만 있어도 되는 녀석이다. 굳이 MemberController를 만들때마다 여러개의 memberservice가 계속 만들어질 필요는 없다는 말 같다.
    // @Controller가 있다면 container에 객체를 생성해서 가지고 있는다고 했다. 하지만 이녀석을 만들때 memberService의 존재를 spring은 모른다. memberService는 단순한 자바코드이기 때문이다.
    // 그러므로 MemberService에 @Service 를 사용하면 스프링이 올라올때 @Service를 보고 이녀석도 container 에 등록해준다. 그러므로 @Autowired를 사용하면 자동으로 연결해주는 것이다.
    // @Repository로 마찬가지다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String findAll(Model model){
       List<Member> members = memberService.findMembers();
       model.addAttribute("members",members);
       return "members/memberList";
    }
}
