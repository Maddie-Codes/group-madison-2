package org.launchcode.taskcrusher.repository;

import org.launchcode.taskcrusher.models.ERole;
import org.launchcode.taskcrusher.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
