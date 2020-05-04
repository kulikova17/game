package net.thumbtack.footballgame.repository;

import net.thumbtack.footballgame.entities.UserGame;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGameRepository extends CrudRepository<UserGame, Integer> {
    List<UserGame> findAllByUserId(Integer userId);
    List<UserGame> findAllByGameId(Integer gameId);
    void deleteByUserId(Integer userId);
    void deleteByGameId(Integer id);
}
