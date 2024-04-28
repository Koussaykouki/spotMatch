package com.example.spotmatch.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = true)
    private String name;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private MusicPreference musicPreference;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MusicPreference getMusicPreference() {
        return musicPreference;
    }

    public void setMusicPreference(MusicPreference musicPreference) {
        this.musicPreference = musicPreference;
    }
}

