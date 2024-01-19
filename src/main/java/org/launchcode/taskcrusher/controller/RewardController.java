package org.launchcode.taskcrusher.controller;

import jakarta.validation.Valid;
import org.launchcode.taskcrusher.models.Reward;
import org.launchcode.taskcrusher.models.data.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {
    @Autowired
    private RewardRepository rewardRepository;

    @GetMapping("/list")
    public List<Reward> displayAllRewards() {
        return rewardRepository.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Reward> createReward(@RequestBody Reward reward) {
        Reward createdReward = rewardRepository.save(reward);
        return new ResponseEntity<>(createdReward, HttpStatus.CREATED);
    }
}
