package com.example.focus.Service;

import com.example.focus.ApiResponse.ApiException;
import com.example.focus.DTO.EditorDTO;
import com.example.focus.DTO.MediaDTO;
import com.example.focus.Model.Editor;
import com.example.focus.Model.Media;

import com.example.focus.Model.Photographer;
import com.example.focus.Model.Profile;
import com.example.focus.Repository.EditorRepository;
import com.example.focus.Repository.MediaRepository;
import com.example.focus.Repository.PhotographerRepository;
import com.example.focus.Repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaService {
    private final MediaRepository mediaRepository;
    private final PhotographerRepository photographerRepository;
    private final EditorRepository editorRepository;
    private final ProfileRepository profileRepository;

    public List<MediaDTO> getAllMedia() {
        List<Media>medias=mediaRepository.findAll();
        List<MediaDTO> mediaDTOS = new ArrayList<>();

        for (Media media : medias) {
            MediaDTO mediaDTO = new MediaDTO(
                    media.getMediaType(),
                    media.getMediaUrl(),
                    media.getUploadDate()
            );
            mediaDTOS.add(mediaDTO);
        }
        return mediaDTOS;
    }


    public void addMedia(Integer userid,Media media) {
        Photographer photographer=photographerRepository.findPhotographersById(userid);
        Editor editor=editorRepository.findEditorById(userid);

        if(photographer==null && editor==null){
            throw new ApiException("sorry user id not found");
        }
        //check profile
        Profile profile =new Profile();

        if(photographer !=null) {
        profile = photographer.getProfile();
        }else{
        profile=editor.getProfile();
         }
    media.setProfile(profile);
    mediaRepository.save(media);
    }

//    public void updateMedia(Integer id, Media media) {
//        Media existingMedia = mediaRepository.findMediaById(id);
//        if (existingMedia != null) {
//            existingMedia.setVisibility(media.getName());
//            editorRepository.save(existingEditor);
//        } else {
//            throw new RuntimeException("Editor not found");
//        }
//    }

    public void deleteEditor(Integer id) {
        Editor existingEditor = editorRepository.findEditorById(id);
        if (existingEditor != null) {
            editorRepository.delete(existingEditor);
        } else {
            throw new RuntimeException("Editor not found");
        }
    }

}
