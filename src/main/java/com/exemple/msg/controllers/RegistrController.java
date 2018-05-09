package com.exemple.msg.controllers;

import com.exemple.msg.models.Role;
import com.exemple.msg.models.User;
import com.exemple.msg.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrController {

    private final UserRepo userRepo;

    public RegistrController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String,Object> model){
        User username = userRepo.findByUsername(user.getUsername());
        if(username !=null){
            model.put("message","User exist!");
            return "registration";
        }

        user.setActive(true);
        if(userRepo.count()>0) {
            user.setRoles(Collections.singleton(Role.USER));
        }
        else{
            user.setRoles(Collections.singleton(Role.ADMIN));
        }
        userRepo.save(user);
        return "redirect:/login";

    }
}
