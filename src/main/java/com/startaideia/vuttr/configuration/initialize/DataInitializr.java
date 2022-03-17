package com.startaideia.vuttr.configuration.initialize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.startaideia.vuttr.domain.model.Const;
import com.startaideia.vuttr.domain.model.Role;
import com.startaideia.vuttr.domain.model.User;
import com.startaideia.vuttr.domain.repository.RoleRepository;
import com.startaideia.vuttr.domain.repository.UserRepository;

@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {

        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            createUser("Admin", "admin", passwordEncoder.encode("123456"), Const.ROLE_ADMIN);
            createUser("Cliente", "cliente", passwordEncoder.encode("123456"), Const.ROLE_CLIENT);
        }

    }

    public void createUser(String name, String email, String password, String roleName) {

        Role role = new Role(roleName);

        this.roleRepository.save(role);
        User user = new User(name, email, password, Arrays.asList(role));
        userRepository.save(user);
    }

}
