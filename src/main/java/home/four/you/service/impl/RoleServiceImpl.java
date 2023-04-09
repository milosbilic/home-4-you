package home.four.you.service.impl;

import home.four.you.model.entity.Role;
import home.four.you.repository.RoleRepository;
import home.four.you.security.auth.authorization.AuthorityRole;
import home.four.you.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link RoleService}.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findByName(AuthorityRole name) {
        log.info("Finding role {}", name);

        return roleRepository.findByName(name);
    }
}
