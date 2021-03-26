package com.amwebexpert.hangman.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configurations for the campaign-creator slack notifications
 */
@Configuration
@ConfigurationProperties(prefix = "app.build-info")
public class AboutInfo {

    String name;
    String versionDate;
    String url;

    public String getVersionDate() {
        return versionDate;
    }

    public void setVersionDate(String versionDate) {
        this.versionDate = versionDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
