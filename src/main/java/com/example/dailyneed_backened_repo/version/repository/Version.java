package com.example.dailyneed_backened_repo.version.repository;

import javax.persistence.*;

@Entity
@Table(name = "version")
public class Version {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String currentVersion;

    public Version() {
    }

    public Version(Long id, String currentVersion) {
        this.id = id;
        this.currentVersion = currentVersion;
    }

    public Long getId() {
        return id;
    }


}
