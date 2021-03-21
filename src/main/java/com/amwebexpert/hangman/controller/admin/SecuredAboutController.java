package com.amwebexpert.hangman.controller.admin;

import com.amwebexpert.hangman.config.AboutInfo;
import com.amwebexpert.hangman.dto.AboutInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/api/v1/admin/about")
public class SecuredAboutController {

    @Autowired
    AboutInfo aboutInfo;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    AboutInfoDto about(HttpServletRequest req) {
        AboutInfoDto dto = AboutInfoDto.from(aboutInfo);

        dto.setOthers(new TreeMap(System.getProperties()));

        // Add servlet API version to the list of system properties
        ServletContext sc = req.getSession().getServletContext();
        String version = sc.getMajorVersion() + "." + sc.getMinorVersion();
        dto.getOthers().put("Servlet API version", version);

        return dto;
    }

}
