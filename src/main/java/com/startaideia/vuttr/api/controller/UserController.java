package com.startaideia.vuttr.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.startaideia.vuttr.domain.model.Const;
import com.startaideia.vuttr.domain.model.User;
import com.startaideia.vuttr.domain.repository.RoleRepository;
import com.startaideia.vuttr.domain.repository.UserRepository;

/**
 * Classe reponsável pela disponibilização dos endpoints da API de Usuario
 * @author robso
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Método que adiciona uma novo Usuario
     * @param user - Parâmetro do tipo Usuario
     * @return
     */
    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<User> save(@RequestBody User user){
        user = this.userRepository.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    /**
     * Método que atualiza um Usuario existente
     * @param user - - Parâmetro do tipo Usuario
     * @return
     */
    @Secured({Const.ROLE_ADMIN})
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<User> edit(@RequestBody User user){
        user = this.userRepository.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    /**
     * Método que lista um Usuario existente apartir de dois parâmetros
     * @param page - idenficador
     * @param size - tamanho
     * @return
     */
    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Page<User>> list(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        return new ResponseEntity<Page<User>>(userRepository.findAll(pageable), HttpStatus.OK);
    }

	

}
