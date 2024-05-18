package com.example.spotmatch.service;

import com.example.spotmatch.entity.Song;
import com.example.spotmatch.entity.UserSongInteraction;
import com.example.spotmatch.repository.SongRepository;
import com.example.spotmatch.repository.UserSongInteractionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongPlayCountService {

    @Autowired
    private UserSongInteractionRepository interactionRepository;


    private final MusicPreferenceService musicPreferenceService;

    public void recordSongPlay(Long userId, Long songId) {
        UserSongInteraction interaction = interactionRepository.findByUserIdAndSongId(userId, songId);
        if (interaction != null) {
            interaction.setPlayCount(interaction.getPlayCount() + 1);
        } else {
            interaction = new UserSongInteraction();
            // Set user and song based on retrieved entities (not shown here)
            interaction.setPlayCount(1);
        }
        interactionRepository.save(interaction);
        musicPreferenceService.updateMusicPreferences(); // Trigger preference update
    }
}
