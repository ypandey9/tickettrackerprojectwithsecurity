////package com.springbootdemo.tickettrackerproj.config;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.security.web.SecurityFilterChain;
////import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
////
////@Configuration
////@EnableWebSecurity
////public class SpringSecurity {
////
////	@Autowired
////	private UserDetailsService userDetailsService;
////	
////	
////	
////	@Bean
////	public static PasswordEncoder passwordEncoder() {
////		return new BCryptPasswordEncoder();
////	}
////	
////	@Bean
////	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
////		http.csrf().disable()
////		.authorizeHttpRequests((authorize)->
////		authorize.requestMatchers("/user/**").permitAll()
////		//.requestMatchers("/user/index").permitAll()
////		//.requestMatchers("/user/users").hasRole("ADMIN")
////		.requestMatchers("/ticket/**").hasRole("ADMIN")
////		
////		).formLogin(
////				form->form
////				.loginPage("/user/login")
////				.loginProcessingUrl("/user/login")
////				.defaultSuccessUrl("/user/users")
////				.permitAll()
////				).logout(
////						logout->logout
////						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
////						.permitAll()
////						);
////		return http.build();
////	}
////	
////	@Autowired
////	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
////		auth
////		.userDetailsService(userDetailsService)
////		.passwordEncoder(passwordEncoder());	
////	}
////
////}
//
package com.springbootdemo.tickettrackerproj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/user/**").permitAll()
                    .requestMatchers("/ticket/**").hasRole("ADMIN")
            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/user/login")
                    .loginProcessingUrl("/user/login")
                    .defaultSuccessUrl("/user/users")
                    .permitAll()
            )
            .logout(logout ->
                logout
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .permitAll()
            )
            .httpBasic(); // Added for testing with basic auth

        return http.build();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }
}
