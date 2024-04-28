
package com.example.spotmatch.repository;

        import com.example.spotmatch.entity.MusicPreference;
        import com.example.spotmatch.entity.User;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface MusicPreferenceRepository extends JpaRepository <MusicPreference, Long> {
        MusicPreference findByUser(User user);
        }