package net.thumbtack.footballgame.service;

import net.thumbtack.footballgame.entities.AdditionalUser;
import net.thumbtack.footballgame.repository.AdditionalUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdditionalUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdditionalUserService.class);
    private AdditionalUserRepository repository;

    @Autowired
    public AdditionalUserService(AdditionalUserRepository repository) {
        this.repository = repository;
    }

    public AdditionalUser save(AdditionalUser user) {
        LOGGER.debug("Insert additional user {}", user);
        return repository.save(user);
    }

    public void deleteById(int id) {
        LOGGER.debug("Delete additiona user by id {}", id);
        repository.deleteById(id);
    }

    public AdditionalUser findByEmail(String email) {
        LOGGER.debug("Find additional user by email {}", email);
        return repository.findByEmail(email);
    }

    public List<AdditionalUser> findAll() {
        LOGGER.debug("Find all additional users");
        Iterable<AdditionalUser> iterable = repository.findAll();
        List<AdditionalUser> additionalUsers = new ArrayList<>();
        iterable.forEach(additionalUsers::add);
        return additionalUsers;
    }
}
