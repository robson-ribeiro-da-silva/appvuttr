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

/**
 * Classe resonsável pela inicialização e inserção de Usuarios na base de dados.
 * @author robso
 * Implementa a classe ApplicationListener
 */
@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Método de inicialização quando a aplicação for executada
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {

        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            createUser("Admin", "admin", passwordEncoder.encode("123456"), Const.ROLE_ADMIN);
            createUser("Cliente", "cliente", passwordEncoder.encode("123456"), Const.ROLE_CLIENT);
        } //verifica se ja existe Usuarios cadastrados, se não existir cria dois novos Usuarios.

    }
    
    /**
     * Método para criar um novo Usuario através dos parâmetros informados
     * @param name - Parâmetro referente ao nome do Usuario
     * @param email - Parâmetro referente ao email do Usuario
     * @param password - Parâmetro referente a senha de acesso do Usuario
     * @param roleName - Parâmetro referente a permissão que o Usuario tem no sistema 
     */
    public void createUser(String name, String email, String password, String roleName) {

        Role role = new Role(roleName);

        this.roleRepository.save(role);
        User user = new User(name, email, password, Arrays.asList(role));
        userRepository.save(user);
    }

}
