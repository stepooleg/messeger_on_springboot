package com.exemple.msg.controllers;

import com.exemple.msg.models.User;
import com.exemple.msg.models.dto.CaptchResponseDto;
import com.exemple.msg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrController {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    private final UserService userService;

    public RegistrController(UserService userServise) {
        this.userService = userServise;
    }

    @Value("${recaptcha.secret}")
    private String secret;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("passwordValid") String passwordValid,
            @RequestParam("g-recaptcha-response") String captchaResponse,
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ){
        String url = String.format(CAPTCHA_URL, secret, captchaResponse);
        CaptchResponseDto responseDto = restTemplate.postForObject(url, Collections.emptyList(), CaptchResponseDto.class);

        if(!responseDto.isSuccess()){
            model.addAttribute("captchaError", "Fill captcha");
        }

        boolean isConfirmEmpty = StringUtils.isEmpty(passwordValid);
        if (isConfirmEmpty){
            model.addAttribute("passwordValidError", "Password confirmation cannot be empty");
        }
        if(user.getPassword() != null && !user.getPassword().equals(passwordValid)){
            model.addAttribute("passwordError", "Password are different!");
        }

        if (isConfirmEmpty || bindingResult.hasErrors()||!responseDto.isSuccess()){
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
            model.addAttribute("messageType","success");
            model.addAttribute("message","User successfully activated");
        }else {
            model.addAttribute("messageType","danger");
            model.addAttribute("message","Activation code is not found!");
        }

        return "login";
    }
}
