package com.example.focus.Controller;

import com.example.focus.DTO.SpaceDTO;
import com.example.focus.Service.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/focus/space")
public class SpaceController {
    private final SpaceService spaceService;


    @GetMapping("/get-all-spaces")
    public ResponseEntity getAllSpaces(){
        List<SpaceDTO> spaces = spaceService.getAllSpaces();
        return ResponseEntity.status(200).body(spaces);
    }

}
