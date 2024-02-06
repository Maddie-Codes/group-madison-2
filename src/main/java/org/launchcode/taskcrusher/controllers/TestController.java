package org.launchcode.taskcrusher.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/test")
public class TestController {

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('KID') or hasRole('PARENT')")
    public String userAccess() {
        return "User Content";
    }

    @GetMapping("/kid")
    @PreAuthorize("hasRold('KID')")
    public String kidAccess() {
        return "Kid Dashboard";
    }

    @GetMapping("/parent")
    @PreAuthorize("hasRole('PARENT')")
    public String parentAccess() {
        return "Parent Dashboard";
    }
}
