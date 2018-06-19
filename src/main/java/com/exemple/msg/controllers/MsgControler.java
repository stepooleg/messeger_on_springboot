package com.exemple.msg.controllers;

import com.exemple.msg.models.MsgModel;
import com.exemple.msg.models.User;
import com.exemple.msg.repositories.MsgRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class MsgControler {

    private final MsgRepo msgRepo;

    public MsgControler(MsgRepo msgRepo) {
        this.msgRepo = msgRepo;
    }

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String home(Map<String, Object> model) {
        return "index";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<MsgModel> msg = msgRepo.findAll();
        if (filter != null && !filter.isEmpty()) {
            msg = msgRepo.findByTag(filter);
        } else {
            msg = msgRepo.findAll();
        }
        model.addAttribute("messages", msg);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid MsgModel msgModel,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file)
            throws IOException {
        msgModel.setAuthor(user);

        if (bindingResult.hasErrors()){
            Map<String, String> errorMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorMap);
            model.addAttribute("message", msgModel);


        }else {

            saveFile(msgModel, file);

            model.addAttribute("message",null);
            msgRepo.save(msgModel);
        }
        List<MsgModel> msg = msgRepo.findAll();
        model.addAttribute("messages", msg);
        return "main";
    }

    private void saveFile(@Valid MsgModel msgModel, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploaFolder = new File(uploadPath);
            if (!uploaFolder.exists()) {
                uploaFolder.mkdirs();
            }
            String uuitFile = UUID.randomUUID().toString();
            String resultFileName = uuitFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            msgModel.setFilename(resultFileName);
        }
    }

    @GetMapping("user-messages/{user}")
    public String userMessages(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam (required = false)MsgModel message
    ) {
        Set<MsgModel> messages = user.getMessages();
        model.addAttribute("messages", messages);
        model.addAttribute("message", message);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        return "userMessages";
    }
    @PostMapping("user-messages/{user}")
    public String updateMessage(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam ("id") MsgModel message,
            @RequestParam ("text") String text,
            @RequestParam ("tag") String tag,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (message.getAuthor().equals(currentUser)){
            if(!StringUtils.isEmpty(text)){
                message.setText(text);
            }

            if(!StringUtils.isEmpty(tag)){
                message.setTag(tag);
            }
            saveFile(message,file);
            msgRepo.save(message);

        }
        return "redirect:/user-messages/" +user;
    }



}
