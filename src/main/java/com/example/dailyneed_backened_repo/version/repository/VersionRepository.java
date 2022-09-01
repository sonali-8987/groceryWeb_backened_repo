package com.example.dailyneed_backened_repo.version.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersionRepository extends JpaRepository<Version, Long> {
    @Query(value = "SELECT v.current_version  FROM version v order by v.id desc limit(1)", nativeQuery = true)
    String findCurrentVersion();

}
