package com.example.dailyneed_backened_repo;

import com.example.dailyneed_backened_repo.category.repository.Category;
import com.example.dailyneed_backened_repo.category.repository.CategoryRepository;
import com.example.dailyneed_backened_repo.version.repository.Version;
import com.example.dailyneed_backened_repo.version.repository.VersionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(VersionRepository versionRepository , CategoryRepository categoryRepository) {
        return args -> {
            if(versionRepository.findAll().isEmpty())
                versionRepository.save(new Version(1L,"V2"));

            if(categoryRepository.findAll().isEmpty()) {
                categoryRepository.save(new Category(1L, "VEGETABLES"));
                categoryRepository.save(new Category(2L,"FRUITS"));
                categoryRepository.save(new Category(3L,"GROCERY"));
            }


        };
    }
}
