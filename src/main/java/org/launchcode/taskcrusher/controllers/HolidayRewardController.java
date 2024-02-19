package org.launchcode.taskcrusher.controllers;


import org.launchcode.taskcrusher.models.Holiday;
import org.launchcode.taskcrusher.models.data.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HolidayRewardController {
    @Autowired
    private HolidayRepository holidayRepository;
    @PostMapping("/holiday-reward")
    //The points will be doubled if it's a Holiday else the point will be same.
    //Passing two request parameter Date and Point.
    public int getRewardValue(
            @RequestParam String dueDate,
            @RequestParam int points ) {

        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("dueDate"+dueDate);
        LocalDate dueDateVal = LocalDate.parse(dueDate);
        int rewardPoints=0;

        Optional<Holiday> existingHoliday = holidayRepository.findByHolidayDate(dueDateVal);
        if (existingHoliday.isEmpty()) {
            rewardPoints=points;
            System.out.println("If its not an Public Holiday!");
        }
        else {
            rewardPoints=(2*points);
            System.out.println("It is Public Holiday!");
        }
        return rewardPoints;


    }

}