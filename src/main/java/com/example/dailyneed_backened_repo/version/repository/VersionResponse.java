package com.example.dailyneed_backened_repo.version.repository;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VersionResponse {
    @JsonProperty("CurrentVersion")
    private String currentVersion;

    public VersionResponse(String currentVersion) {
        this.currentVersion = currentVersion;
    }

}

