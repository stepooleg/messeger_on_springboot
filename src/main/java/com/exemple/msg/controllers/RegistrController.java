package com.exemple.msg.controllers;

import com.exemple.msg.models.User;
import com.exemple.msg.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrController {

    private final UserService userService;

    public RegistrController(UserService userServise) {
        this.userService = userServise;
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model){
        if(user.getPassword() != null && !user.getPassword().equals(user.getPasswordValid())){
            model.addAttribute("passwordError", "Password are different!");
        }

        if (bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }

        if(!userService.addUser(user)){
            model.addAttribute("usernameError","User exist!");
            return "registration";
        }


        return "redirect:/login";

    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);

        if(isActivated){
            model.addAttribute("message","User successfully activated");
        }else {
            model.addAttribute("message","Activation code is not found!");
        }

        return "login";
    }
}
