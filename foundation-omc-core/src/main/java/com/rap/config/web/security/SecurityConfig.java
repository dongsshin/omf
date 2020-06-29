package com.rap.config.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.rap.config.web.security.handler.LoginFailHandler;
import com.rap.config.web.security.handler.LoginSuccessHandler;
import com.rap.omc.foundation.user.service.FoundationUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	FoundationUserDetailsService foundationUserDetailsService;
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(foundationUserDetailsService).passwordEncoder(passwordEncoder());
    	//PasswordEncoder ddd =  new BCryptPasswordEncoder();
        //auth.inMemoryAuthentication().withUser("XP3866").password("XP3866").roles("USER");
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
    	//http.httpBasic();
        //http.csrf().disable().authorizeRequests().anyRequest().authenticated();
        http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().formLogin().successHandler(successHandler()).and().formLogin().failureHandler(failHandler());
    }
    
//    
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http
//          .csrf().disable()
//          .authorizeRequests()
//          .antMatchers("/admin/**").hasRole("ADMIN")
//          .antMatchers("/anonymous*").anonymous()
//          .antMatchers("/login*").permitAll()
//          .anyRequest().authenticated()
//          .and()
//          .formLogin()
//          .loginPage("/login.html")
//          .loginProcessingUrl("/perform_login")
//          .defaultSuccessUrl("/homepage.html", true)
//          //.failureUrl("/login.html?error=true")
//          .failureHandler(failHandler())
//          .and()
//          .logout()
//          .logoutUrl("/perform_logout")
//          .deleteCookies("JSESSIONID")
//          .logoutSuccessHandler(logoutSuccessHandler());
//    }
//     

    @Override
    public void configure(WebSecurity web) throws Exception {
    	web.ignoring().antMatchers("/resources/**");
    }
    @Bean
    public AuthenticationSuccessHandler successHandler() {
      return new LoginSuccessHandler("/");
    }
    @Bean
    public AuthenticationFailureHandler failHandler() {
      return new LoginFailHandler();
    }
}
