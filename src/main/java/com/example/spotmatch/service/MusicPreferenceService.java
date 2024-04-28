import com.example.spotmatch.entity.MusicPreference;
import com.example.spotmatch.entity.User;
import com.example.spotmatch.entity.UserSongInteraction;
import com.example.spotmatch.repository.MusicPreferenceRepository;
import com.example.spotmatch.repository.UserRepository;
import com.example.spotmatch.repository.UserSongInteractionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MusicPreferenceService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSongInteractionRepository userSongInteractionRepository;

    @Autowired
    private MusicPreferenceRepository musicPreferenceRepository;

    @Transactional
    public void updateMusicPreferences() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            List<Object[]> results = userSongInteractionRepository.sumPlayCountsByGenreForUser(user.getId());
            double totalPlays = results.stream().mapToDouble(r -> (long) r[1]).sum();

            MusicPreference preference = user.getMusicPreference();
            if (preference == null) {
                preference = new MusicPreference();
                preference.setUser(user);
            }

            // Reset preferences to zero
            preference.setAcoustic(0);
            preference.setElectronic(0);
            preference.setRap(0);

            // Update preferences based on play counts
            for (Object[] result : results) {
                String genre = (String) result[0];
                long playCount = (long) result[1];
                double genrePreference = totalPlays == 0 ? 0 : (double) playCount / totalPlays;

                switch (genre.toLowerCase()) {
                    case "acoustic":
                        preference.setAcoustic(genrePreference);
                        break;
                    case "electronic":
                        preference.setElectronic(genrePreference);
                        break;
                    case "rap":
                        preference.setRap(genrePreference);
                        break;
                }
            }

            musicPreferenceRepository.save(preference);
        }
    }
}
