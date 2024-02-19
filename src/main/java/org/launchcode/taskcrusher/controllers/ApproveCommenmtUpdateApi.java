package org.launchcode.taskcrusher.controllers;

import org.launchcode.taskcrusher.enums.ChoreStatus;
import org.launchcode.taskcrusher.models.Chore;
import org.launchcode.taskcrusher.models.Kid;
import org.launchcode.taskcrusher.models.data.ChoreRepository;
import org.launchcode.taskcrusher.models.data.KidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ApproveCommenmtUpdateApi {
    @Autowired
    private ChoreRepository choreRepository;


    // Endpoint for a parent to approve a completed chore and set points/dollars
    @PostMapping("/approvecomment/{choreId}")
    public String approveComment(@PathVariable int choreId, @RequestParam String commentValue) {
        System.out.println("choreId"+choreId);
        System.out.println("commentValue"+commentValue);
        Optional<Chore> choreOptional = choreRepository.findById(choreId);

        if (choreOptional.isPresent()) {
            Chore chore = choreOptional.get();
            if (ChoreStatus.APPROVED.equals(chore.getStatus())) {

                chore.setComment(commentValue);
                choreRepository.save(chore);
                return "Updated the comment for Approved Chores";
            } else {
                return "Cannot update the comment.";
            }
        } else {
            return "Chore is not found in the Chores table.";
        }
    }
}