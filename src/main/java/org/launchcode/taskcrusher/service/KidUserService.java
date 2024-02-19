package org.launchcode.taskcrusher.service;

import lombok.RequiredArgsConstructor;
import org.launchcode.taskcrusher.exceptions.AppException;
import org.launchcode.taskcrusher.mappers.KidMapper;
import org.launchcode.taskcrusher.models.Kid;
import org.launchcode.taskcrusher.models.User;
import org.launchcode.taskcrusher.models.data.KidRepository;
import org.launchcode.taskcrusher.models.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class KidUserService {

    private final KidRepository kidRepository;

    private final PasswordEncoder passwordEncoder;

    private final KidMapper kidMapper;

    public KidUserDto kidLogin(CredentialsDto credentialsDto) {
        Kid kidUser = kidRepository.findByUsername(credentialsDto.username())
                .orElseThrow(() -> new AppException("Unknown kid user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), kidUser.getPassword())) {
            return kidMapper.toKidUserDto(kidUser);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }


    public KidUserDto kidRegister(KidSignUpDto kidUserDto) {
        Optional<Kid> optionalKidUser = kidRepository.findByUsername(kidUserDto.username());
        if (optionalKidUser.isPresent()) {
            throw new AppException("Username already exists", HttpStatus.BAD_REQUEST);
        }

        Kid kidUser = kidMapper.signUpToKidUser(kidUserDto);
        kidUser.setPassword(passwordEncoder.encode(CharBuffer.wrap(kidUserDto.password())));
        Kid savedKidUser = kidRepository.save(kidUser);

        return kidMapper.toKidUserDto(savedKidUser);
    }


    public KidUserDto findByUsername(String username) {
        Kid kidUser = kidRepository.findByUsername(username)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return kidMapper.toKidUserDto(kidUser);
    }

}
