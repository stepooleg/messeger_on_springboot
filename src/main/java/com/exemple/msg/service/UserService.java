package com.exemple.msg.service;


import com.exemple.msg.models.Role;
import com.exemple.msg.models.User;
import com.exemple.msg.repositories.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

   private final UserRepo userRepo;
    private final MailSender mailSender;

    public UserService(UserRepo userRepo, MailSender mailSender) {
        this.userRepo = userRepo;
        this.mailSender = mailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByUsername(s);
    }

    public boolean addUser(User user){
        User username = userRepo.findByUsername(user.getUsername());
        if(username != null){
            return false;
        }
        user.setActive(true);
        if(userRepo.count()>0) {
            user.setRoles(Collections.singleton(Role.USER));
        }
        else{
            user.setRoles(Collections.singleton(Role.ADMIN));
        }
        user.setActivationCode(UUID.randomUUID().toString());
        userRepo.save(user);
        if(!StringUtils.isEmpty( user.getEmail())){
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to MSG. Please, visit next link: http://localhost:8090/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()

            );
            mailSender.send(user.getEmail(),"Activation code", message);

        }
        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);
        if(user == null){
            return false;
        }
        user.setActivationCode(null);
        userRepo.save(user);
        return true;
    }
}
