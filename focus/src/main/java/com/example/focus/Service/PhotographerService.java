package com.example.focus.Service;

import com.example.focus.ApiResponse.ApiException;
import com.example.focus.DTO.PhotographerDTO;
import com.example.focus.Model.Photographer;
import com.example.focus.Model.Profile;
import com.example.focus.Model.RentTools;
import com.example.focus.Model.Tool;
import com.example.focus.Repository.PhotographerRepository;
import com.example.focus.Repository.MyOrderRepository;
import com.example.focus.Repository.ToolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotographerService {
    private final PhotographerRepository photographerRepository;
    private final MyOrderRepository myOrderRepository;
    private final ToolRepository toolRepository;


    public List<PhotographerDTO> getAllPhotographers() {
        List<Photographer> photographers = photographerRepository.findAll();
        List<PhotographerDTO> photographerDTOS = new ArrayList<>();

        for (Photographer photographer : photographers) {
            PhotographerDTO photographerDTO = new PhotographerDTO(
                    photographer.getUsername(),
                    photographer.getName(),
                    photographer.getCity(),
                    photographer.getEmail(),
                    photographer.getPhoneNumber()
            );
            photographerDTOS.add(photographerDTO);
        }
        return photographerDTOS;
    }


    public void PhotographerRegistration(Photographer photographer) {

        photographerRepository.save(photographer);
        Profile emptyProfile = new Profile();
        emptyProfile.setDescription("");
        emptyProfile.setNumberOfPosts(0);
        emptyProfile.setImage("");
        emptyProfile.setId(photographer.getId());

        editor.setProfile(emptyProfile);
    }


    public void updatePhotographer(Integer id, Photographer photographer) {
        Photographer existingPhotographer = photographerRepository.findById(id).orElse(null);
        if (existingPhotographer != null) {
            existingPhotographer.setName(photographer.getName());
            existingPhotographer.setCity(photographer.getCity());
            existingPhotographer.setEmail(photographer.getEmail());
            existingPhotographer.setUsername(photographer.getUsername());
        }else {
            throw new ApiException("Photographer Not Found");
        }
        photographerRepository.save(existingPhotographer);

    }


    public void deletePhotographer(Integer id) {
        Photographer existingPhotographer = photographerRepository.findById(id).orElse(null);
        if (existingPhotographer != null) {
            photographerRepository.delete(existingPhotographer);
        } else {
            throw new ApiException("Photographer Not Found");
        }
    }

    public void rentTool(Integer photographer_id, Integer tool_id, RentTools rentTool) {
        Photographer photographer = photographerRepository.findPhotographersById(photographer_id);
        Tool tool = toolRepository.findToolById(tool_id);

        if(photographer == null) {
            throw new ApiException("photographer not found");
        }

        if(tool == null) {
            throw new ApiException("tool not found");
        }

        if(rentTool.getToolId().equals(tool_id)) {
            for (int i = 0; i < myOrderRepository.findAll().size(); i++) {
                if (myOrderRepository.findAll().get(i).getStartDate().equals(rentTool.getStartDate()) && myOrderRepository.findAll().get(i).getEndDate().equals(rentTool.getEndDate())) {
                    throw new ApiException("Can't rent the tool now");
                }
            }

            long daysBetween = ChronoUnit.DAYS.between(rentTool.getStartDate(), rentTool.getEndDate());
            Double totalPrice = tool.getRentalPrice()*daysBetween;
            rentTool.setRentPrice(totalPrice);
            myOrderRepository.save(rentTool);

        }

    }
}