package com.example.dailyneed_backened_repo;

import com.example.dailyneed_backened_repo.version.repository.Version;
import com.example.dailyneed_backened_repo.version.repository.VersionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(VersionRepository versionRepository) {
        return args -> {
                versionRepository.save(new Version(1L,"V2"));
        };
    }
}
