package com.amwebexpert.hangman.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.logging.Logger;

/**
 * https://spring.io/guides/tutorials/spring-boot-oauth2/
 * https://springframework.guru/using-the-h2-database-console-in-spring-boot-with-spring-security/
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final Logger LOG = Logger.getLogger(SecurityConfiguration.class.getSimpleName());

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

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService() {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

        return request -> {
            String oAuthProvider = request.getClientRegistration().getRegistrationId();
            String tokenType = request.getAccessToken().getTokenType().getValue();

            // Normal OAuth2 flow: call to the Authorization Server (github, facebook, ...)
            OAuth2User user = delegate.loadUser(request);

            String info = String.format("Authenticated user [%s - %s] using [%s] OAuth2 API with access token type [%s]",
                    user.getName(), user.getAttributes().get("name"), oAuthProvider, tokenType);
            LOG.info(info);

            // TODO Load user from our database using user.id + oAuthProvider, and return
            //  something that extends our custom User object and implements OAuth2User
            // @see https://spring.io/guides/tutorials/spring-boot-oauth2/
            boolean isUserActive = true;

            // We can prevent login because of our internal DB info regarding that user
            if (isUserActive) {
                return user;
            } else {
                String error = String.format("Deactivated user [%s - %s]", user.getName(), user.getAttributes().get("name"));
                throw new OAuth2AuthenticationException(new OAuth2Error("invalid_user", error, ""));
            }
        };
    }

}
