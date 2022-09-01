package com.example.dailyneed_backened_repo.version;

import com.example.dailyneed_backened_repo.version.repository.VersionRepository;
import org.springframework.stereotype.Service;

@Service
public class VersionService {
    private final VersionRepository versionRepository;

    public VersionService(VersionRepository versionRepository) {
        this.versionRepository = versionRepository;
    }

    public String findCurrentVersion(){
        return versionRepository.findCurrentVersion();
    }
}
