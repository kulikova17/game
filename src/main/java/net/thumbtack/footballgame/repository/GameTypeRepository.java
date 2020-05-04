package net.thumbtack.footballgame.repository;

import net.thumbtack.footballgame.entities.GameType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameTypeRepository extends CrudRepository<GameType, Integer> {
    GameType findByType(String gameType);

    void deleteByType(String type);
}
