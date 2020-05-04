package net.thumbtack.footballgame.service;

import net.thumbtack.footballgame.entities.GameType;
import net.thumbtack.footballgame.repository.GameTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GameTypesService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameTypesService.class);
    private GameTypeRepository repository;

    @Autowired
    public GameTypesService(GameTypeRepository repository) {
        this.repository = repository;
    }

    public void clear() {
        LOGGER.debug("Delete all game types");
        repository.deleteAll();
    }

    public void deleteById(Integer id) {
        LOGGER.debug("Delete type by id {}", id);
        repository.deleteById(id);
    }

    public void deleteByType(String type) {
        LOGGER.debug("Delete game type by type {}", type);
        repository.deleteByType(type);
    }

    public GameType save(GameType gameType) {
        LOGGER.debug("Insert game type {}", gameType);
        return repository.save(gameType);
    }

    public GameType findByType(String type) {
        LOGGER.debug("Find type by type {}", type);
        return repository.findByType(type);
    }

    public GameType findById(Integer id) {
        LOGGER.debug("Find type by id {}", id);
        return repository.findById(id).orElse(null);
    }


    public List<GameType> findAll() {
        LOGGER.debug("Find all types");
        Iterable<GameType> iterable = repository.findAll();
        List<GameType> types = new ArrayList<>();
        iterable.forEach(types::add);
        return types;
    }
}
