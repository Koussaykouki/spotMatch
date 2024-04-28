package com.example.spotmatch.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "music_preferences")
public class MusicPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "acoustic")
    private double acoustic;

    @Column(name = "electronic")
    private double electronic;

    @Column(name = "rap")
    private double rap;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAcoustic() {
        return acoustic;
    }

    public void setAcoustic(double acoustic) {
        this.acoustic = acoustic;
    }

    public double getElectronic() {
        return electronic;
    }

    public void setElectronic(double electronic) {
        this.electronic = electronic;
    }

    public double getRap() {
        return rap;
    }

    public void setRap(double rap) {
        this.rap = rap;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
