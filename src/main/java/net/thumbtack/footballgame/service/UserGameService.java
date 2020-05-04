package net.thumbtack.footballgame.service;

import net.thumbtack.footballgame.entities.UserGame;
import net.thumbtack.footballgame.repository.UserGameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserGameService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserGameService.class);
    private final UserGameRepository repository;

    @Autowired
    public UserGameService(UserGameRepository repository) {
        this.repository = repository;
    }

    public UserGame save(UserGame userGame) {
        LOGGER.debug("Insert userGame {}", userGame);
        return repository.save(userGame);
    }

    public UserGame findById(Integer id) {
        LOGGER.debug("Find userGame by id {}", id);
        return repository.findById(id).orElse(null);
    }


    public List<UserGame> findByUserId(Integer userId) {
        return repository.findAllByUserId(userId);
    }

    public List<UserGame> findByGameId(Integer gameId) {
        return repository.findAllByGameId(gameId);
    }

    public void deleteById(Integer id) {
        LOGGER.debug("Delete userGame by id {}", id);
        repository.deleteById(id);
    }

    public UserGame update(UserGame userGame) {
        LOGGER.debug("Update userGame {}", userGame);
        return save(userGame);
    }

    public void deleteByUserId(Integer userId) {
        LOGGER.debug("Delete UserGame by user id {}", userId);
        repository.deleteByUserId(userId);
    }
    public void deleteByGameId(Integer gameId) {
        LOGGER.debug("Delete userGame by game id {}", gameId);
        repository.deleteByGameId(gameId);
    }
    public void  clear() {
        LOGGER.debug("Delete all user games");
        repository.deleteAll();
    }
}
