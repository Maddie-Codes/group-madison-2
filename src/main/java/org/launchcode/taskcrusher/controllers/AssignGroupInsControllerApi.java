package org.launchcode.taskcrusher.controllers;

import org.launchcode.taskcrusher.enums.ChoreStatus;
import org.launchcode.taskcrusher.models.Chore;
import org.launchcode.taskcrusher.models.Holiday;
import org.launchcode.taskcrusher.models.Kid;
import org.launchcode.taskcrusher.models.User;
import org.launchcode.taskcrusher.models.data.ChoreRepository;
import org.launchcode.taskcrusher.models.data.HolidayRepository;
import org.launchcode.taskcrusher.models.data.KidRepository;
import org.launchcode.taskcrusher.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AssignGroupInsControllerApi {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KidRepository kidRepository;

    @Autowired
    private ChoreRepository choreRepository;
    @Autowired
    private HolidayRepository holidayRepository;
    @PostMapping("/insert")
    //This is the group Insert of the chores.
    //Also the reward points for the public holiday is also done.
    public String statusIns(
            @RequestParam int selectedKidId, @RequestParam LocalDate dueDate, @RequestParam int setKidDropValue,@RequestParam LocalDate maxDueDate
    ) {
        System.out.println("selectedKid"+selectedKidId);
        System.out.println("setKidDropValue"+setKidDropValue);

        //To insert the kid id for the kid selected from the group assign we need to change to kid type.
        Optional<Kid> kidOptional = kidRepository.findById(setKidDropValue);
        Kid dropValueKid = kidOptional.get();
        //Changing the kidid from int to Kid type for giving input to the chores  table.
        Optional<Kid> kidOptional1 = kidRepository.findById(selectedKidId);
        Kid selectedKid = kidOptional1.get();
        //////////////////////Reward for the group assign
        double rewardPointsMax=0;
        int  rewardPoints;
        ///This is to check if the maxDate is a public holiday.
        //If so then take off the 2X.
        Optional<Holiday> existingHolidayMax = holidayRepository.findByHolidayDate(maxDueDate);
        if (existingHolidayMax.isEmpty() ) {
            rewardPointsMax=1;
            System.out.println("If its not an Public Holiday!");
            System.out.println(rewardPointsMax);
        }
        else {
            rewardPointsMax = 0.5;
            System.out.println("It is Public Holiday!");
            System.out.println(rewardPointsMax);
       }
        Optional<Holiday> existingHoliday = holidayRepository.findByHolidayDate(dueDate);
        if (existingHoliday.isEmpty()) {
            rewardPoints=1;
            System.out.println("If its not an Public Holiday!");
        }
        else {
            rewardPoints = 2;
            System.out.println("It is Public Holiday!");
            System.out.println(rewardPoints);
        }
        List<Chore> chores = choreRepository.findByKid(selectedKid);
        // Insert each chore as a new record
        for (Chore chore : chores) {
            Chore newChore = new Chore();
            newChore.setName(chore.getName());
            newChore.setDescription(chore.getDescription());
            newChore.setImage(chore.getImage());
            newChore.setValueType(chore.getValueType());
            newChore.setValue((int) (chore.getValue()*rewardPointsMax*rewardPoints));
            newChore.setStatus(ChoreStatus.valueOf("ASSIGNED"));
            newChore.setParent(chore.getParent());
            newChore.setDueDate(dueDate);
            newChore.setKid(dropValueKid);
            choreRepository.save(newChore);

        }
        return "Group Chore is successfully assigned!";
    }


}