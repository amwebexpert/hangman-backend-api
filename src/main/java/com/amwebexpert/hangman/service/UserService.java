package com.amwebexpert.hangman.service;

import com.amwebexpert.hangman.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Transactional(readOnly = true)
    public User getUserFromPrincipal(@AuthenticationPrincipal OAuth2User principal) {
        User user = new User();

        user.setEmail(principal.getAttribute("email"));
        user.setName(principal.getAttribute("name"));

        return user;
    }

}
