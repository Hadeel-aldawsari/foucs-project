package com.example.focus.Service;

import com.example.focus.Model.TimeSlot;
import com.example.focus.Repository.SpaceRepository;
import com.example.focus.Repository.TimeSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpaceService {
    private final SpaceRepository spaceRepository;
    private final TimeSlotRepository timeSlotRepository;

    public boolean isSpaceAvailable(Long spaceId, LocalDate requestedDate) {
        // Check if any booking exists for this space on the requested date
        List<TimeSlot> bookings = timeSlotRepository.findBySpaceIdAndBookingDate(spaceId, requestedDate);

        return bookings.isEmpty();  // Return true if no bookings found, space is available
    }
}

