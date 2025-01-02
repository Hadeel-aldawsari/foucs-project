package com.example.focus.Repository;

import com.example.focus.Model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Integer> {
    TimeSlot findDateBookingById(Integer id);
    List<TimeSlot> findBySpaceIdAndIsBooked(Integer workspaceId, Boolean isBooked);
    List<TimeSlot> findTimeSlotBySpaceIdAndIsBooked(Integer spaceId, Boolean isBooked);
}
