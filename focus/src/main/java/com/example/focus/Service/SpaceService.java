package com.example.focus.Service;//package com.example.focus.Service;
//
//import com.example.focus.Model.TimeSlot;
//import com.example.focus.Repository.SpaceRepository;
//import com.example.focus.Repository.TimeSlotRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
import com.example.focus.DTO.SpaceDTO;
import com.example.focus.Model.Space;
import com.example.focus.Repository.SpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpaceService {
    private final SpaceRepository spaceRepository;
   // private final TimeSlotRepository timeSlotRepository;


    // get spaces
    public List<SpaceDTO> getAllSpaces(){
        List<Space> spaces = spaceRepository.findAll();
        List<SpaceDTO> spaceDTOS = new ArrayList<>();
        for (Space space : spaces){
            SpaceDTO spaceDTO = new SpaceDTO(space.getName(),space.getType(),space.getArea(),space.getDescription(),space.getPrice(),space.getStatus(),space.getImage());
            spaceDTOS.add(spaceDTO);
        }
        return spaceDTOS;
    }
//    public boolean isSpaceAvailable(Long spaceId, LocalDate requestedDate) {
//        // Check if any booking exists for this space on the requested date
//        List<TimeSlot> bookings = timeSlotRepository.findBySpaceIdAndBookingDate(spaceId, requestedDate);
//
//        return bookings.isEmpty();  // Return true if no bookings found, space is available
//    }
}

