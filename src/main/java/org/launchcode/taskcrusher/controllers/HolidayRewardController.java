package org.launchcode.taskcrusher.controllers;


import org.launchcode.taskcrusher.enums.ChoreStatus;
import org.launchcode.taskcrusher.models.Chore;
import org.launchcode.taskcrusher.models.Holiday;
import org.launchcode.taskcrusher.models.Kid;
import org.launchcode.taskcrusher.models.data.ChoreRepository;
import org.launchcode.taskcrusher.models.data.HolidayRepository;
import org.launchcode.taskcrusher.models.data.KidRepository;
import org.launchcode.taskcrusher.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HolidayRewardController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KidRepository kidRepository;

    @Autowired
    private ChoreRepository choreRepository;
    @Autowired
    private HolidayRepository holidayRepository;
    @PostMapping("/holiday-reward")
    //The points will be doubled if it's a Holiday else the point will be same.
    //Passing two request parameter Date and Point.
    public String getRewardValue(
            @RequestParam int choreId,
            @RequestParam String dueDate,
            @RequestParam int points ) {

       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("dueDate"+dueDate);
        System.out.println("Choreid"+choreId);
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
            System.out.println( rewardPoints);
        }
       Optional<Chore> choreOptional = choreRepository.findById(choreId);

        if (choreOptional.isPresent()) {
            Chore chore = choreOptional.get();
            System.out.println("Choreid"+choreId);
            System.out.println("rewardPoints"+rewardPoints);
            chore.setValue(rewardPoints);
            choreRepository.save(chore);
            return "value point is updated.";
        }
        else {
            return "Chore is not found in the Chores table.";
        }
    }

}

