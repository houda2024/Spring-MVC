package com.example.NewBook.demo.security.service;

import com.example.NewBook.demo.security.entities.AppRole;
import com.example.NewBook.demo.security.entities.AppUser;
import com.example.NewBook.demo.security.repository.RoleRepository;
import com.example.NewBook.demo.security.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AccountService implements  IAccountService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);


    @Override
    public void addRole(String role) {
        //roleRepository.save(new AppRole(role));
        roleRepository.save(AppRole.builder().role(role).build());
    }

    @Override
    public void addUser(String username, String password, String mail) {
        AppUser existeingUser=userRepository.findAppByUsername(username);
        if (existeingUser!=null) {
            logger.error("User already exists. Username: {}", username);

            throw new RuntimeException("user already exist" + username);
        }

        AppUser newUser= AppUser.builder()
                .id(UUID.randomUUID().toString())
                .mail(mail)
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();

        try {
            userRepository.save(newUser);

        }catch (Exception e){
            logger.error("Error saving user. Username: {}", username, e);

            throw  new RuntimeException("Error saving user. username :"+ username ,e);

        }


    }



    @Override
    public void addRoleToUser(String username, Long role) {
        AppUser user=loadUserByUserName(username);
        AppRole appRole=roleRepository.findById(role).orElse(null);
        if (appRole!=null){
            user.getRoles().add(appRole);
        }

    }

    @Override
    public void deleteRoleToUser(String username, Long role) {

    }

    @Override
    public AppUser loadUserByUserName(String username) {
        return userRepository.findAppByUsername(username);
    }
}
