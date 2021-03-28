package com.amwebexpert.hangman.config;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    AboutInfo aboutInfo;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests(customizer -> customizer
                        .antMatchers("/", "/*.html", "/favicon.*").permitAll()
                        .antMatchers("/webjars/**", "/font-awesome/**").permitAll()
                        .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        // List of anonymous access resources
                        .antMatchers("/logout").permitAll()
                        .antMatchers("/error").permitAll()
                        .antMatchers("/api/v1/about").permitAll()
                        .antMatchers("/api/v1/categories/**").permitAll()
                        // All other resources need authentication
                        .anyRequest().authenticated()
                )
                .exceptionHandling(customizer -> customizer
                        // since we are interfacing with the backend over Ajax, we need to configure endpoints to
                        // respond with a 401 instead of the default behavior of redirecting to a login page
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .logout(customizer -> customizer
                        .logoutSuccessUrl(aboutInfo.getUrl())
                        .deleteCookies("JSESSIONID")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .permitAll())
                .csrf(customizer -> customizer.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .oauth2Login();
    }

}
