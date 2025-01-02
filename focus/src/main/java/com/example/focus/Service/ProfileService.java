package com.example.focus.Service;
import com.example.focus.ApiResponse.ApiException;
import com.example.focus.DTO.ProfileDTO;
import com.example.focus.Model.Profile;
import com.example.focus.Repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public List<ProfileDTO> getAllProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        List<ProfileDTO> profileDTOs = new ArrayList<>();

        for (Profile profile : profiles) {
            ProfileDTO profileDTO = new ProfileDTO(
                    profile.getDescription(),
                    profile.getNumberOfPosts(),
                    profile.getImage()
            );
            profileDTOs.add(profileDTO);
        }
        return profileDTOs;
    }


    public void addProfile(Profile profile) {
        profileRepository.save(profile);
    }

    public void updateProfile(Integer id, Profile profile) {
        Profile existingProfile = profileRepository.findById(id).orElse(null);
        if (existingProfile != null) {
            existingProfile.setDescription(profile.getDescription());
            existingProfile.setImage(profile.getImage());
        } else {
            throw new ApiException("Profile Not Found");
        }
        profileRepository.save(existingProfile);
    }

    public void deleteProfile(Integer id) {
        Profile existingProfile = profileRepository.findById(id).orElse(null);
        if (existingProfile != null) {
            profileRepository.delete(existingProfile);
        } else {
            throw new ApiException("Profile Not Found");
        }
    }

}