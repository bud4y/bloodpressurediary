package edu.progmatic.bpdiary.service;

import edu.progmatic.bpdiary.model.registration.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByName(String name);
}
