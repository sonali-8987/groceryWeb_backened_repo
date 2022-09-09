package com.example.dailyneed_backened_repo.version;

import com.example.dailyneed_backened_repo.exceptions.VersionNotAvailableException;
import com.example.dailyneed_backened_repo.version.repository.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VersionService {
    private final VersionRepository versionRepository;

    @Autowired
    public VersionService(VersionRepository versionRepository) {
        this.versionRepository = versionRepository;
    }

    public String findCurrentVersion() throws VersionNotAvailableException {

        final String currentVersion = versionRepository.findCurrentVersion();
        if (currentVersion == null)
            throw new VersionNotAvailableException();
        return currentVersion;
    }
}
