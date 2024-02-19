package org.launchcode.taskcrusher.mappers;

import org.launchcode.taskcrusher.models.Kid;
import org.launchcode.taskcrusher.models.dto.KidSignUpDto;
import org.launchcode.taskcrusher.models.dto.KidUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface KidMapper {

    KidUserDto toKidUserDto(Kid kidUser);

    @Mapping(target = "password", ignore = true)
    Kid signUpToKidUser(KidSignUpDto kidSignUpDto);

}
