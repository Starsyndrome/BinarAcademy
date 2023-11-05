package org.binaracademy.challenge4.config;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.enumeration.ERole;
import org.binaracademy.challenge4.model.Roles;
import org.binaracademy.challenge4.repository.RoleRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Config {
    Config(RoleRepository roleRepository) {
        log.info("Cheking roles presented");
        for(ERole c : ERole.values()) {
            try {
                Roles roles = roleRepository.findByRoleName(c)
                        .orElseThrow(() -> new RuntimeException("Roles not found"));
                log.info("Role {} has been found!", roles.getRoleName());
            } catch(RuntimeException rte) {
                log.info("Role {} is not found, inserting to DB . . .", c.name());
                Roles roles = new Roles();
                roles.setRoleName(c);
                roleRepository.save(roles);
            }
        }
    }
}