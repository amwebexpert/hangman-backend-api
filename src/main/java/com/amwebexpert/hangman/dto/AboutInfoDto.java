package com.amwebexpert.hangman.dto;

import com.amwebexpert.hangman.config.AboutInfo;

import java.util.HashMap;
import java.util.Map;

public class AboutInfoDto {
    String name;
    String versionDate;
    Map<String, String> others = new HashMap<>();

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

    public Map<String, String> getOthers() {
        return others;
    }

    public void setOthers(Map<String, String> others) {
        this.others = others;
    }

}
