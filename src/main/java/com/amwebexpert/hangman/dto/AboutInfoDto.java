package com.amwebexpert.hangman.dto;

import com.amwebexpert.hangman.config.AboutInfo;

public class AboutInfoDto {
    String name;
    String versionDate;

    public static AboutInfoDto from(AboutInfo aboutInfo) {
        AboutInfoDto dto = new AboutInfoDto();

        dto.name = aboutInfo.getName();
        dto.versionDate = aboutInfo.getVersionDate();

        return dto;
    }

    public String getName() {
        return name;
    }

    public String getVersionDate() {
        return versionDate;
    }
}
