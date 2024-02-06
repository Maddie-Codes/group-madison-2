//package org.launchcode.taskcrusher.models;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotNull;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Entity
//public class ParentUser {
//
//    @Id
//    @GeneratedValue
//    private int parentId;
//
//    @NotNull
//    private String firstName;
//
//    @NotNull
//    private String lastName;
//
//    @NotNull
//    @Email
//    private String email;
//
//    @NotNull
//    private String username;
//    @NotNull
//    private String pwHash;
//
//    public ParentUser() {}
//
//    public ParentUser(String firstName, String lastName, String email, String username, String password) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.username = username;
//        this.pwHash = encoder.encode(password);
//    }
//
//    public ParentUser(String username, String password) {
//    }
//
//    public int getParentId() {
//        return parentId;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getUsername() {return username;}
//
//    // Static method to use the bcrypt dependency for encoding
//    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//    // Instance method to use the bcrypt multi-step matcher (.equals is not enough)
//    public boolean isMatchingPassword(String password) {
//        return encoder.matches(password, pwHash);
//    }
//}
