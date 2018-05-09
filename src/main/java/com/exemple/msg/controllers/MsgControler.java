package com.exemple.msg.controllers;

import com.exemple.msg.models.MsgModel;
import com.exemple.msg.models.User;
import com.exemple.msg.repositories.MsgRepo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MsgControler {

    private final MsgRepo msgRepo;

    public MsgControler(MsgRepo msgRepo) {
        this.msgRepo = msgRepo;
    }

    @GetMapping("/")
    public String home(Map<String,Object> model){
        return "index";
    }
    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "")String filter, Model model){
        Iterable<MsgModel> msg= msgRepo.findAll();
        if(filter!=null&&!filter.isEmpty()){
            msg = msgRepo.findByTag(filter);
        }
        else {
            msg= msgRepo.findAll();
        }
        model.addAttribute("messages",msg);
        model.addAttribute("filter",filter);
        return "main";
    }
    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String,Object> model){
        MsgModel msgModel = new MsgModel(text, tag, user);
        msgRepo.save(msgModel);
        List<MsgModel> msg= msgRepo.findAll();
        model.put("messages",msg);
        return "main";
    }

}
