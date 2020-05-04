package net.thumbtack.footballgame.service;

import net.thumbtack.footballgame.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DebugService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DebugService.class);
    private GameTypeRepository typeRepository;
    private GameRepository gameRepository;
    private UserRepository userRepository;
    private UserGameRepository userGameRepository;
    private AdditionalUserRepository additionalUserRepository;

    @Autowired
    public DebugService(GameTypeRepository typeRepository, GameRepository gameRepository,
                        UserRepository userRepository, UserGameRepository userGameRepository,
                        AdditionalUserRepository additionalUserRepository) {
        this.typeRepository = typeRepository;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.userGameRepository = userGameRepository;
        this.additionalUserRepository = additionalUserRepository;
    }

    public void clear() {
        LOGGER.debug("Clear database");

        userGameRepository.deleteAll();
        gameRepository.deleteAll();
        typeRepository.deleteAll();
        userRepository.deleteAll();
        additionalUserRepository.deleteAll();
    }
}
