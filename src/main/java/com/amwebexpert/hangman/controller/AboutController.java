package com.amwebexpert.hangman.controller;

import com.amwebexpert.hangman.config.AboutInfo;
import com.amwebexpert.hangman.model.AboutInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AboutController {

    @Autowired
    AboutInfo aboutInfo;

    @GetMapping(value = "/about", produces = MediaType.APPLICATION_JSON_VALUE)
    AboutInfoDto about() {
        return AboutInfoDto.from(aboutInfo);
    }

}
