package com.devarchi.web.controller;

import com.devarchi.web.dao.MemberRepository;
import com.devarchi.web.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by donghoon on 2016. 2. 25..
 */
@Controller
public class HelloController {

    private final String APP_DIR = "home/";

    @Autowired
    private MemberRepository memberRepository;

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Hello, springboot";
    }

    @RequestMapping("home")
    public String sayHello(Model model) {
        model.addAttribute("greeting", "Hello");
        return APP_DIR + "index";
    }

    @RequestMapping("manmonth")
    public String manMonth(Model model) {
        model.addAttribute("greeting", "Hello");
        return APP_DIR + "manmonth";
    }

    @RequestMapping("list")
    @ResponseBody
    public String memberList() {
        List<Member> memberList = new ArrayList<Member>();

        for (Member member : memberRepository.findAll()) {
            memberList.add(member);
        }

        return memberList.toString();
    }

    @RequestMapping("listByLastName")
    @ResponseBody
    public String onePerson() {
        List<Member> memberList = new ArrayList<Member>();

        for (Member member : memberRepository.findByLastName("이")) {
            memberList.add(member);
        }

        return memberList.toString();
    }

    @RequestMapping("add")
    public void memberAdd() {
        memberRepository.save(new Member("face", "new"));
        memberRepository.save(new Member("face", "old"));
    }
}
