package com.example.dailyneed_backened_repo.version.view;

import com.example.dailyneed_backened_repo.version.VersionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class VersionController {
    private final VersionService versionService;

    public VersionController(VersionService versionService) {
        this.versionService = versionService;
    }

    @GetMapping("/version")
    public Map<String, String> versionResponse() {
        Map<String, String> currentVersion = new HashMap<>();
        currentVersion.put("CurrentVersion", versionService.findAll());
        return currentVersion;
    }

}
