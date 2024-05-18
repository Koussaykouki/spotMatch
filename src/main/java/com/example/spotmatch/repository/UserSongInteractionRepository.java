package com.example.spotmatch.repository;

import com.example.spotmatch.entity.User;
import com.example.spotmatch.entity.UserSongInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSongInteractionRepository extends JpaRepository<UserSongInteraction, Long> {
    @Query("SELECT s.genre, SUM(usi.playCount) FROM UserSongInteraction usi JOIN usi.song s WHERE usi.user.id = :userId GROUP BY s.genre")
    List<Object[]> sumPlayCountsByGenreForUser(Long userId);

    UserSongInteraction findByUserIdAndSongId(Long userId, Long songId);
}