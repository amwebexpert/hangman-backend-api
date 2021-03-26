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
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/admin/about")
public class SecuredAboutController {

    private static final List<String> PROPERTIES = Arrays.asList(
            "java.runtime.version", "user.timezone", "user.country", "os.version", "os.arch", "java.version", "java.runtime.name"
    );

    @Autowired
    AboutInfo aboutInfo;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    AboutInfoDto about(HttpServletRequest req) {
        // System properties
        TreeMap<String, String> others = new TreeMap<>();
        Properties systemProperties = System.getProperties();
        for (String key : PROPERTIES) {
            others.put(key, systemProperties.getProperty(key));
        }

        AboutInfoDto dto = AboutInfoDto.from(aboutInfo);
        dto.setOthers(others);

        // Add servlet context api version to the list of system properties
        ServletContext sc = req.getServletContext();
        String version = sc.getMajorVersion() + "." + sc.getMinorVersion();
        dto.getOthers().put("Servlet API version", version);

        return dto;
    }

}