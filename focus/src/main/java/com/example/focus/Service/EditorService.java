package com.example.focus.Service;


import com.example.focus.DTO.EditorDTO;
import com.example.focus.Model.Editor;
import com.example.focus.Model.Profile;
import com.example.focus.Repository.EditorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EditorService {
    private final EditorRepository editorRepository;

    public List<EditorDTO> getAllEditors() {
        List<Editor> editors = editorRepository.findAll();
        List<EditorDTO> editorDTOS = new ArrayList<>();

        for (Editor editor : editors) {
            EditorDTO editorDTO = new EditorDTO(
                    editor.getName(),
                    editor.getCity(),
                    editor.getPhoneNumber(),
                    editor.getEmail(),
                    editor.getUsername(),
                    editor.getPassword()
            );
            editorDTOS.add(editorDTO);
        }
        return editorDTOS;
    }


    public void EditorRegistration(Editor editor) {
        editorRepository.save(editor);
        Profile emptyProfile = new Profile();
        emptyProfile.setDescription("");
        emptyProfile.setNumberOfPosts(0);
        emptyProfile.setImage("");
        emptyProfile.setId(editor.getId());

        editor.setProfile(emptyProfile);

    }

    public void updateEditor(Integer id, Editor editor) {
        Editor existingEditor = editorRepository.findEditorById(id);
        if (existingEditor != null) {
            existingEditor.setName(editor.getName());
            existingEditor.setCity(editor.getCity());
            existingEditor.setPhoneNumber(editor.getPhoneNumber());
            existingEditor.setEmail(editor.getEmail());
            existingEditor.setUsername(editor.getUsername());
            existingEditor.setPassword(editor.getPassword());
            editorRepository.save(existingEditor);
        } else {
            throw new RuntimeException("Editor not found");
        }
    }

    public void deleteEditor(Integer id) {
        Editor existingEditor = editorRepository.findEditorById(id);
        if (existingEditor != null) {
            editorRepository.delete(existingEditor);
        } else {
            throw new RuntimeException("Editor not found");
        }
    }
}