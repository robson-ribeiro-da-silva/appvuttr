package com.startaideia.vuttr.api.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.startaideia.vuttr.domain.model.Const;
import com.startaideia.vuttr.domain.model.User;

/**
 * Classe reponsável pela disponibilização dos endpoints da API de Usuario e Segurança
 * @author robso
 *
 */
@Controller
public class SecurityController {
	
	/**
	 * Método que lista o Usuario que esta autenticado na aplicação
	 * @return
	 */
	@RequestMapping(value = "/user-auth", method = RequestMethod.GET )
    @ResponseBody
    @Secured({Const.ROLE_CLIENT, Const.ROLE_ADMIN})
    public User user() {
        return (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
