package org.launchcode.taskcrusher.controllers;

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
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/groupassign")
public class AssignGroupControllerApi {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KidRepository kidRepository;

    @Autowired
    private ChoreRepository choreRepository;


    @GetMapping("/allkids")
    public Iterable<Kid> getAllKidsForThisParent(@RequestParam String  username) {
      //  Optional<User> parent = userRepository.findByUsername(username);
        Optional<User> parent = userRepository.findByUsername(username);
        System.out.println(userRepository.findByUsername(username));
        System.out.println(kidRepository.findById(parent.get().getId()));
        return kidRepository.findById(parent.get().getId());
    }
    @GetMapping("/allchores")
    public List<Chore> getAllChoresThisParent(@RequestParam Kid  kidId) {
      //  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      //  LocalDate formatDate = LocalDate.parse(duedate, formatter);
        return choreRepository.findByKid(kidId);
    }
}
