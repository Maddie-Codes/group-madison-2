package org.launchcode.taskcrusher.controllers;

import org.launchcode.taskcrusher.enums.ChoreStatus;
import org.launchcode.taskcrusher.models.Chore;
import org.launchcode.taskcrusher.models.data.ChoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
//This is the RedoComment update and the status change from APPROVED to ASSIGNED.
public class RedoCommentUpdate {
    @Autowired
    private ChoreRepository choreRepository;



    @PostMapping("/redocomment/{choreId}")
    public String redoComment(@PathVariable int choreId, @RequestParam String commentValue) {
        System.out.println("choreId"+choreId);
        System.out.println("commentValue"+commentValue);
        Optional<Chore> choreOptional = choreRepository.findById(choreId);

        if (choreOptional.isPresent()) {
                Chore chore = choreOptional.get();
                chore.setStatus(ChoreStatus.valueOf("ASSIGNED"));
                chore.setComment(commentValue);
                choreRepository.save(chore);
                return "Updated the comment for Redo Chores and also its once again assigned.";
            }  else {
            return "Chore is not found in the Chores table.";
        }
    }
}
