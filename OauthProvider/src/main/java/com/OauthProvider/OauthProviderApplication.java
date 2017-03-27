package com.OauthProvider;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class OauthProviderApplication extends WebSecurityConfigurerAdapter{
protected void configure(HttpSecurity http) throws Exception 
{
http.antMatcher("/**")
.authorizeRequests()
.antMatchers("/","/login**","/webjars/**")
.permitAll()
.anyRequest()
.authenticated().and().logout().logoutSuccessUrl("/").permitAll().and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
}
	@RequestMapping("/greeting")
	public List<String> Greetings(@RequestParam String greeting1, String greeting2){
		List<String> list = new LinkedList<>();
		list.add(greeting1);
		list.add(greeting2);
		return list; 
	}
	
	@RequestMapping("/user")
	public Principal FbUser(Principal principal){
		return principal;
	}
	public static void main(String[] args) {
		SpringApplication.run(OauthProviderApplication.class, args);
	}
}
