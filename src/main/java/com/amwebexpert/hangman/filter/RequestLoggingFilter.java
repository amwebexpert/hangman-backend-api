package com.amwebexpert.hangman.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;

/**
 * @see  * @see com.amwebexpert.hangman.config.WebFilteringConfig
 */
@Component
@Order(1)
public class RequestLoggingFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(RequestLoggingFilter.class.getSimpleName());
    private static final int RIGHT_PAD_LENGTH = 35;
    public static final List<String> PARAMETERS_CONTAINING_SENSITIVE_INFO = Arrays.asList("pwd", "password", "j_password", "credential");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logIncomingRequest((HttpServletRequest) request);
        chain.doFilter(request, response);
    }

    private boolean isSensitive(String info) {
        return PARAMETERS_CONTAINING_SENSITIVE_INFO.contains(info.toLowerCase());
    }

    /**
     * Log incoming request details
     *
     * @param req HTTP request
     */
    private void logIncomingRequest(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();

        if (req.getParameterMap().isEmpty()) {
            sb.append("\n").append("\t" + req.getMethod() + ": " + req.getRequestURL());
        } else {
            sb.append("\n").append("\t" + req.getMethod() + ": " + req.getRequestURL() + " params " + req.getParameterMap().keySet());
        }

        logParameters(req, sb);
        logHeaders(req, sb);
        logCookies(req, sb);

        LOG.info(sb.toString());
    }

    private void logCookies(HttpServletRequest req, StringBuilder sb) {
        // Cookies
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                sb.append("\n").append(StringUtils.rightPad("cookie " + cookie.getName(), RIGHT_PAD_LENGTH, '.') + cookie.getValue());
            }
        }
    }

    private void logHeaders(HttpServletRequest req, StringBuilder sb) {
        Enumeration<String> enumer;

        // Header names
        enumer = req.getHeaderNames();
        while (enumer.hasMoreElements()) {
            String name = enumer.nextElement();
            String value = "<*****>";
            if (!isSensitive(name)) {
                value = req.getHeader(name);
            }

            sb.append("\n").append(StringUtils.rightPad(name, RIGHT_PAD_LENGTH, '.') + value);
        }
    }

    private void logParameters(HttpServletRequest req, StringBuilder sb) {
        Enumeration<String> enumer = req.getParameterNames();
        while (enumer.hasMoreElements()) {
            String name = enumer.nextElement();
            String value = "<*****>";
            if (!isSensitive(name)) {
                value = truncateLongValue(req.getParameter(name));
            }

            sb.append("\n").append(StringUtils.rightPad(name, RIGHT_PAD_LENGTH, '.') + value);
        }
    }

    private String truncateLongValue(String value) {
        if (StringUtils.isBlank(value)) {
            return value;
        }

        if (value.length() < 100) {
            return value;
        }

        value = StringUtils.left(value, 100) + "...";
        value = StringUtils.replace(value, "\n", " | ");

        return value;
    }

}
