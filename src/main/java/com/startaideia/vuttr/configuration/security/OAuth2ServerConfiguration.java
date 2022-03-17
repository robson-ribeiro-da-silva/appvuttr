package com.startaideia.vuttr.configuration.security;

import java.util.Base64.Decoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.startaideia.vuttr.domain.service.MyUserDetailsService;

@Configuration
public class OAuth2ServerConfiguration {
	
	private static final String RESOURCE_ID = "restservice";

    @Configuration
    @EnableResourceServer
    @EnableOAuth2Client
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(RESOURCE_ID);
        }
        
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .and().authorizeRequests()
                    .antMatchers("/api/**").fullyAuthenticated()
                    .antMatchers("/user-auth").fullyAuthenticated()
                    .antMatchers("/user").fullyAuthenticated()
                    .anyRequest().permitAll()
                    .and().csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                                  
                   
        }


    }
    
    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    	
    	//pra configurar o Token JWT
        //private TokenStore tokenStore = new JwtTokenStore(accessTokenConverter());
        
        //pra configurar o Token Oauth em memória
        private TokenStore tokenStore = new InMemoryTokenStore();

        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Autowired
        private MyUserDetailsService userDetailsService;

        @Autowired
        private PasswordEncoder passwordEncoder;
        
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
                throws Exception {
            endpoints
            .tokenStore(this.tokenStore)
            //.accessTokenConverter(accessTokenConverter()) //pra usar o Token JWT
            .authenticationManager(this.authenticationManager)
            .userDetailsService(userDetailsService);
        }

		@Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients
                    .inMemory()
                    .withClient("client")
                    .authorizedGrantTypes("password", "authorization_code", "refresh_token").scopes("all")
                    .refreshTokenValiditySeconds(300000)
                    .resourceIds(RESOURCE_ID)
                    .secret(passwordEncoder.encode("123"))
                    .accessTokenValiditySeconds(50000)
            ;

        }

        @Bean
        @Primary
        public DefaultTokenServices tokenServices() {
            DefaultTokenServices tokenServices = new DefaultTokenServices();
            tokenServices.setSupportRefreshToken(true);
            tokenServices.setTokenStore(this.tokenStore);
            return tokenServices;
        }
        
        //pra configurar o Token JWT
        JwtAccessTokenConverter accessTokenConverter() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setSigningKey(RESOURCE_ID);
            return converter;
        }


    }

}
