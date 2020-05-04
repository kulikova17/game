package net.thumbtack.footballgame.service;

import net.thumbtack.footballgame.entities.Game;
import net.thumbtack.footballgame.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GameService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);
    private GameRepository repository;

    @Autowired
    public GameService(GameRepository repository) {
        this.repository = repository;
    }


    public Game save(Game game) {
        LOGGER.debug("Insert game {}", game);
        return repository.save(game);
    }

    public Game update(Game game) {
        LOGGER.debug("Update game {}", game);
        return save(game);
    }

    public Game findById(int id) {
        LOGGER.debug("Find game by id {}", id);
        return repository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        LOGGER.debug("Delete game by id {}", id);
        repository.deleteById(id);
    }

    public void clear() {
        LOGGER.debug("Delete all games");
        repository.deleteAll();
    }

}
