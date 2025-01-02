package com.example.focus.Controller;

import com.example.focus.ApiResponse.ApiResponse;
import com.example.focus.Model.Photographer;
import com.example.focus.Model.RentTools;
import com.example.focus.Model.Tool;
import com.example.focus.Service.PhotographerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/focus/photographer")
@RequiredArgsConstructor
public class PhotographerController {

    private final PhotographerService photographerService;

    @GetMapping("/get-all")
    public ResponseEntity getAllPhotographer(){
        return ResponseEntity.status(200).body(photographerService.getAllPhotographers());
    }

    @PostMapping("/register")
    public ResponseEntity PhotographerRegistration(@RequestBody @Valid Photographer photographer){
        photographerService.PhotographerRegistration(photographer);
        return ResponseEntity.status(200).body(new ApiResponse("photographer added"));
    }

    @PutMapping("/update-photographer/{id}")
    public ResponseEntity updatePhotographer(@PathVariable Integer id, @RequestBody @Valid Photographer photographer){
        photographerService.updatePhotographer(id,photographer);
        return ResponseEntity.status(200).body(new ApiResponse("photographer updated"));
    }

    @DeleteMapping("/delete-photographer/{id}")
    public ResponseEntity deletePhotographer(@PathVariable Integer id){
        photographerService.deletePhotographer(id);
        return ResponseEntity.status(200).body(new ApiResponse("photographer deleted"));
    }

    @PostMapping("/rent-tool/{photographer_id}/{toold_id}")
    public ResponseEntity rentTool(@PathVariable Integer photographer_id,@PathVariable Integer tool_id,@RequestBody @Valid RentTools rentTool){
        photographerService.rentTool(photographer_id,tool_id,rentTool);
        return ResponseEntity.status(200).body(new ApiResponse("tool rented successfully"));
    }


}