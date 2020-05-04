package net.thumbtack.footballgame.service;

import net.thumbtack.footballgame.entities.UserRoles;
import net.thumbtack.footballgame.repository.UserRolesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
@Transactional
public class UserRolesService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRolesService.class);
    private UserRolesRepository repository;
    @Autowired
    public UserRolesService(UserRolesRepository repository) {
        this.repository = repository;
    }

    public UserRoles save(UserRoles role) {
        LOGGER.debug("Insert user role");
        return repository.save(role);
    }

    public UserRoles findById(Integer  id) {
        LOGGER.debug("Find role by id {}", id);
        return repository.findById(id).orElse(null);
    }

    public UserRoles update(UserRoles userRoles) {
        LOGGER.debug("Update role {}", userRoles);
        return save(userRoles);
    }

    public void deleteById(Integer id) {
        LOGGER.debug("Delete role by id {}", id);
        repository.deleteById(id);
    }
    public void clear() {
        LOGGER.debug("Delete all roles");
        repository.deleteAll();
    }
}
