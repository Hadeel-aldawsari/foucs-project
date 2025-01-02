package com.example.focus.Controller;
import com.example.focus.ApiResponse.ApiResponse;
import com.example.focus.Model.Profile;
import com.example.focus.Service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/focus/Profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/get-all")
    public ResponseEntity getAllProfiles() {
        return ResponseEntity.status(200).body(profileService.getAllProfiles());
    }

    @PostMapping("/add-profile")
    public ResponseEntity addProfile(@RequestBody @Valid Profile profile) {
        profileService.addProfile(profile);
        return ResponseEntity.status(200).body(new ApiResponse("Profile added"));
    }


    @PutMapping("/update-profile/{id}")
    public ResponseEntity updateProfile(@PathVariable Integer id, @RequestBody @Valid Profile profile) {
        profileService.updateProfile(id, profile);
        return ResponseEntity.status(200).body(new ApiResponse("Profile updated"));
    }

    @DeleteMapping("/delete-profile/{id}")
    public ResponseEntity deleteProfile(@PathVariable Integer id) {
        profileService.deleteProfile(id);
        return ResponseEntity.status(200).body(new ApiResponse("Profile deleted"));
    }
}