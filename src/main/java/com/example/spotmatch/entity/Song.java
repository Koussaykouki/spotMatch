package com.example.spotmatch.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    // Getters and Setters


    public String getGenre() {
        return genre;
    }
}
