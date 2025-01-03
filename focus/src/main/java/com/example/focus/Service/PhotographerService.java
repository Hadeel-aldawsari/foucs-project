package com.example.focus.Service;

import com.example.focus.ApiResponse.ApiException;
import com.example.focus.DTO.PhotographerDTO;
import com.example.focus.DTO.PhotographerDTOin;
import com.example.focus.Model.MyUser;
import com.example.focus.Model.Photographer;
import com.example.focus.Repository.MyUserRepository;
import com.example.focus.Repository.PhotographerRepository;
import com.example.focus.Repository.MyOrderRepository;
import com.example.focus.Repository.ToolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotographerService {
    private final MyUserRepository myUserRepository;
    private final PhotographerRepository photographerRepository;
    private final MyOrderRepository myOrderRepository;
    private final ToolRepository toolRepository;


    public List<PhotographerDTO> getAllPhotographers() {
        List<Photographer> photographers = photographerRepository.findAll();
        List<PhotographerDTO> photographerDTOS = new ArrayList<>();

        for (Photographer photographer : photographers) {
            PhotographerDTO photographerDTO = new PhotographerDTO(
                    photographer.getName(),
                    photographer.getMyUser().getUsername(),
                    photographer.getMyUser().getEmail(),
                    photographer.getCity(),
                    photographer.getPhoneNumber()
            );
            photographerDTOS.add(photographerDTO);
        }
        return photographerDTOS;
    }


    public void PhotographerRegistration(PhotographerDTOin photographerDTOin) {

       // String hashPass=new BCryptPasswordEncoder().encode(photographer.getPassword());


        MyUser user = new MyUser();
        user.setUsername(photographerDTOin.getUsername());
        user.setEmail(photographerDTOin.getEmail());
        user.setPassword(photographerDTOin.getPassword());
        user.setRole("PHOTOGRAPHER");
        myUserRepository.save(user);

        Photographer photographer=new Photographer();
        photographer.setMyUser(user);
        photographer.setName(photographerDTOin.getName());
        photographer.setCity(photographerDTOin.getCity());
        photographer.setPhoneNumber(photographerDTOin.getPhoneNumber());
        photographerRepository.save(photographer);
    }



    public void updatePhotographer(Integer id, PhotographerDTOin photographerDTOin) {
        Photographer existingPhotographer = photographerRepository.findPhotographersById(id);
        if (existingPhotographer != null) {
            existingPhotographer.setName(photographerDTOin.getName());
            existingPhotographer.setCity(photographerDTOin.getCity());
            existingPhotographer.getMyUser().setUsername(photographerDTOin.getUsername());
            existingPhotographer.getMyUser().setEmail(photographerDTOin.getEmail());
            existingPhotographer.setPhoneNumber(photographerDTOin.getPhoneNumber());
        }else {
            throw new ApiException("Photographer Not Found");
        }
        photographerRepository.save(existingPhotographer);

    }


    public void deletePhotographer(Integer id) {
  MyUser myUser=myUserRepository.findMyUserById(id);
    if(myUser!=null) {
    myUserRepository.delete(myUser);
    }else{
    throw new ApiException("Photographer Not Found");
     }
        }

//    public void rentTool(Integer photographer_id, Integer tool_id, RentTools rentTool) {
//        Photographer photographer = photographerRepository.findPhotographersById(photographer_id);
//        Tool tool = toolRepository.findToolById(tool_id);
//
//        if(photographer == null) {
//            throw new ApiException("photographer not found");
//        }
//
//        if(tool == null) {
//            throw new ApiException("tool not found");
//        }
//
//        if(rentTool.getToolId().equals(tool_id)) {
//            for (int i = 0; i < myOrderRepository.findAll().size(); i++) {
//                if (myOrderRepository.findAll().get(i).getStartDate().equals(rentTool.getStartDate()) && myOrderRepository.findAll().get(i).getEndDate().equals(rentTool.getEndDate())) {
//                    throw new ApiException("Can't rent the tool now");
//                }
//            }
//
//            long daysBetween = ChronoUnit.DAYS.between(rentTool.getStartDate(), rentTool.getEndDate());
//            Double totalPrice = tool.getRentalPrice()*daysBetween;
//            rentTool.setRentPrice(totalPrice);
//            myOrderRepository.save(rentTool);
//
//        }

    }
