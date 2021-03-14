package com.amwebexpert.hangman.config;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.h2.server.web.WebServlet;

/**
 * https://springframework.guru/using-the-h2-database-console-in-spring-boot-with-spring-security/
 */
public class PersistenceH2ConsoleExposer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ServletRegistration.Dynamic servlet = servletContext.addServlet("h2-console", new WebServlet());

        servlet.setLoadOnStartup(2);
        servlet.setInitParameter("-webAllowOthers", "true");
        servlet.addMapping("/h2-console/*");
    }
}
