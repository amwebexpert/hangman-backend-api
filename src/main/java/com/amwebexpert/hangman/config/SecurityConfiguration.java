package com.amwebexpert.hangman.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * https://spring.io/guides/tutorials/spring-boot-oauth2/
 * https://springframework.guru/using-the-h2-database-console-in-spring-boot-with-spring-security/
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests(customizer -> customizer
                        .antMatchers("/", "/index.html", "/favicon.ico", "/webjars/**").permitAll()
                        .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        // List of anonymous access resources
                        .antMatchers("/error").permitAll()
                        .antMatchers("/h2-console/**").permitAll()
                        .antMatchers("/api/v1/about").permitAll()
                        // All other resources need authentication
                        .anyRequest().authenticated()
                )
                .exceptionHandling(customizer -> customizer
                        // since we are interfacing with the backend over Ajax, we need to configure endpoints to
                        // respond with a 401 instead of the default behavior of redirecting to a login page
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .logout(customizer -> customizer.logoutSuccessUrl("/").permitAll())
                .csrf(customizer -> customizer.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .oauth2Login();

        boolean h2Console = false;
        if (h2Console) {
            httpSecurity.csrf().disable();
            httpSecurity.headers().frameOptions().disable();
        }
    }

}
