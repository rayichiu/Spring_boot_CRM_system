package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.management.MXBean;
import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {
  // add support for JDBC ... no more hardcoded users

  @Bean
  UserDetailsManager userDetailsManager(DataSource dataSource){
    return new JdbcUserDetailsManager(dataSource);
  }


/* comment out the hard coded users
  @Bean
  public InMemoryUserDetailsManager userDetailsManager() {
    UserDetails john = User.builder() .username("john")
        .password("{noop}test123")
        .roles("EMPLOYEE")
        .build();
    UserDetails mary = User.builder() .username("mary")
        .password("{noop}test123")
        .roles("EMPLOYEE", "MANAGER")
        .build();
    UserDetails susan = User.builder() .username("susan")
        .password("{noop}test123")
        .roles("EMPLOYEE", "MANAGER", "ADMIN")
        .build();
    return new InMemoryUserDetailsManager(john, mary, susan);
  }

 */

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(configurer ->
        configurer
            .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
            .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
            .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
            .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
            .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        )
        .formLogin(form ->
            form
                .loginPage("/api/employees/showMyLoginPage")
                .loginProcessingUrl("/api/employees/authenticateTheUser")
                .permitAll()
        );
    // use HTTP Basic authentication
    http.httpBasic();

    // disable Cross Site Request Forgery (CSRF)
    http.csrf().disable();
    return http.build();
  }

}
