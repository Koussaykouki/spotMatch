package com.example.spotmatch.service;

import com.example.spotmatch.entity.MusicPreference;
import com.example.spotmatch.entity.User;
import com.example.spotmatch.repository.MusicPreferenceRepository;
import com.example.spotmatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMatchingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MusicPreferenceRepository musicPreferenceRepository;

    public List<User> findBestMatches(User user) {
        MusicPreference userPreference = musicPreferenceRepository.findByUser(user);
        List<User> allUsers = userRepository.findAll();

        return allUsers.stream()
                .filter(otherUser -> !otherUser.getId().equals(user.getId())) // Exclude the same user
                .filter(otherUser -> {
                    MusicPreference otherPreference = musicPreferenceRepository.findByUser(otherUser);
                    return cosineSimilarity(userPreference, otherPreference) > 0.5; // Threshold can be adjusted
                })
                .collect(Collectors.toList());
    }
    public double cosineSimilarity(MusicPreference pref1, MusicPreference pref2) {
        double dotProduct = pref1.getAcoustic() * pref2.getAcoustic() +
                pref1.getElectronic() * pref2.getElectronic() +
                pref1.getRap() * pref2.getRap();
        double normPref1 = Math.sqrt(pref1.getAcoustic() * pref1.getAcoustic() +
                pref1.getElectronic() * pref1.getElectronic() +
                pref1.getRap() * pref1.getRap());
        double normPref2 = Math.sqrt(pref2.getAcoustic() * pref2.getAcoustic() +
                pref2.getElectronic() * pref2.getElectronic() +
                pref2.getRap() * pref2.getRap());

        return dotProduct / (normPref1 * normPref2);
    }
}
