package com.example.spotmatch.repository;

import com.example.spotmatch.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    // Custom query methods can be defined here
}
