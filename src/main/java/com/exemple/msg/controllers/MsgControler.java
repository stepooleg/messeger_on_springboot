package com.exemple.msg.controllers;

import com.exemple.msg.models.MsgModel;
import com.exemple.msg.models.User;
import com.exemple.msg.repositories.MsgRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class MsgControler {

    private final MsgRepo msgRepo;

    public MsgControler(MsgRepo msgRepo) {
        this.msgRepo = msgRepo;
    }

    @Value("${upload.path}")
    private String uploadPath;

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
            @RequestParam String tag, Map<String,Object> model,
            @RequestParam("file") MultipartFile file) throws IOException {
        MsgModel msgModel = new MsgModel(text, tag, user);
        if(file !=null && !file.getOriginalFilename().isEmpty()){
            File uploafFolder = new File(uploadPath);
            if(!uploafFolder.exists()){
                uploafFolder.mkdirs();
            }
            String uuitFile = UUID.randomUUID().toString();
            String resultFileName = uuitFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            msgModel.setFilename(resultFileName);
        }
        msgRepo.save(msgModel);
        List<MsgModel> msg= msgRepo.findAll();
        model.put("messages",msg);
        return "main";
    }

}
