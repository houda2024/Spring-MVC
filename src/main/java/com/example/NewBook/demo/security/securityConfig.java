package com.example.NewBook.demo.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor

public class securityConfig {

    PasswordEncoder passwordEncoder;
    UserDetailsService userDetailsService;



    //@Bean
     InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .password(passwordEncoder.encode("1234"))
                        .roles("USER")
                        .build(),
                User.withUsername("admin").
                        password(passwordEncoder.encode("1234"))
                        .roles("ADMIN", "USER")
                        .build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
       httpSecurity.formLogin(form->form.permitAll());
       httpSecurity.httpBasic(Customizer.withDefaults());
       httpSecurity.authorizeHttpRequests(authorize->authorize.requestMatchers("/admin/**").hasAuthority("ADMIN"));
       httpSecurity.authorizeHttpRequests(authorize->authorize.requestMatchers("/user/**").hasAuthority("USER"));
       httpSecurity.exceptionHandling(exception->exception.accessDeniedPage("/errorPage"));
       httpSecurity.authorizeHttpRequests(authorize->authorize.anyRequest().authenticated());
       httpSecurity.userDetailsService(userDetailsService);
        httpSecurity.csrf(c->c.disable());
       return  httpSecurity.build();
    }
}
