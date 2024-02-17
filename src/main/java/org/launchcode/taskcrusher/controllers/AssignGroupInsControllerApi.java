package org.launchcode.taskcrusher.controllers;

import org.launchcode.taskcrusher.enums.ChoreStatus;
import org.launchcode.taskcrusher.models.Chore;
import org.launchcode.taskcrusher.models.Kid;
import org.launchcode.taskcrusher.models.User;
import org.launchcode.taskcrusher.models.data.ChoreRepository;
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

    @PostMapping("/insert")
    public String statusIns(
             @RequestParam int selectedKidId, @RequestParam LocalDate dueDate, @RequestParam int setKidDropValue
    ) {
        LocalDate maxDate = LocalDate.of(2024, 2, 16);
        //To insert the kid id for the kid selected from the group assign we need to change to kid type.
        Optional<Kid> kidOptional = kidRepository.findById(setKidDropValue);
          Kid dropValueKid = kidOptional.get();
        //Changing the kidid from int to Kid type for giving input to the chores  table.
        Optional<Kid> kidOptional1 = kidRepository.findById(selectedKidId);
            Kid selectedKid = kidOptional.get();


            List<Chore> chores = choreRepository.findByKid(selectedKid);

            // Insert each chore as a new record
            for (Chore chore : chores) {
                Chore newChore = new Chore();
                newChore.setName(chore.getName());
                newChore.setDescription(chore.getDescription());
                newChore.setImage(chore.getImage());
                newChore.setValueType(chore.getValueType());
                newChore.setValue(chore.getValue());
                newChore.setStatus(ChoreStatus.valueOf("ASSIGNED"));
                newChore.setParent(chore.getParent());
                newChore.setDueDate(dueDate);
                newChore.setKid(dropValueKid);
                choreRepository.save(newChore);
            }
            System.out.println("inside");
        return "Group Chore is successfully assigned!";
    }


}