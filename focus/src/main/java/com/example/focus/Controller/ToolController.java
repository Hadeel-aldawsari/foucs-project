package com.example.focus.Controller;

import com.example.focus.ApiResponse.ApiResponse;
import com.example.focus.Model.Tool;
import com.example.focus.Service.ToolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/focus/tool")
@RequiredArgsConstructor
public class ToolController {
    private final ToolService toolService;

    @GetMapping("/get-all")
    public ResponseEntity getAllTools() {
        return ResponseEntity.status(200).body(toolService.getAllTools());
    }

    @PostMapping("/add-tool/{photographerid}")
    public ResponseEntity addTool(@RequestBody @Valid Tool tool, @PathVariable Integer photographerid) {
        toolService.addTool(tool, photographerid);
        return ResponseEntity.status(200).body(new ApiResponse("Tool added successfully"));
    }

    @PutMapping("/update-tool/{id}")
    public ResponseEntity updateTool(@PathVariable Integer id, @RequestBody @Valid Tool tool) {
        toolService.updateTool(id, tool);
        return ResponseEntity.status(200).body(new ApiResponse("Tool updated successfully"));
    }

    @DeleteMapping("/delete-tool/photographerid{photographerid}/toolid/{toolid}")
    public ResponseEntity<ApiResponse> deleteTool(@PathVariable Integer photographerid,@PathVariable Integer toolid) {
        toolService.deleteTool(photographerid,toolid);
        return ResponseEntity.status(200).body(new ApiResponse("Tool deleted successfully"));
    }
}