package com.example.dailyneed_backened_repo.version.view;

import com.example.dailyneed_backened_repo.exceptions.VersionNotAvailableException;
import com.example.dailyneed_backened_repo.version.VersionService;
import com.example.dailyneed_backened_repo.version.repository.VersionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
    private final VersionService versionService;

    public VersionController(VersionService versionService) {
        this.versionService = versionService;
    }

    @GetMapping("/version")
    public ResponseEntity versionResponse() {

        String currentVersion;
        try {
            currentVersion = versionService.findCurrentVersion();
        } catch (VersionNotAvailableException e) {
            return new ResponseEntity<>("version not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new VersionResponse(currentVersion), HttpStatus.OK);
    }

}
