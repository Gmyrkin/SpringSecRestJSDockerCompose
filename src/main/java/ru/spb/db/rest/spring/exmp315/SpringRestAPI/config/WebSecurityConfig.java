package ru.spb.db.rest.spring.exmp315.SpringRestAPI.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.handler.LoginSuccessHandler;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;

    public WebSecurityConfig(UserDetailsService userDetailsService, UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                // .csrf()
             //   .disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/login/**").permitAll()
                        .requestMatchers("/users/**").hasAnyRole( "ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("USER")
                        .anyRequest()
                        .authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .successHandler(new LoginSuccessHandler())
                        .loginProcessingUrl("/login")
                        .permitAll()
                );
//                .logout((logout) -> logout.permitAll())
//                .exceptionHandling()
//                .accessDeniedPage("/");
        return http.build();

    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


}
