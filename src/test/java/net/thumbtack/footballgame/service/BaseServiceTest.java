package net.thumbtack.footballgame.service;

import net.thumbtack.footballgame.entities.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class BaseServiceTest {
    @Autowired
    protected UserGameService userGameService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected GameService gameService;
    @Autowired
    protected GameTypesService typesService;
    @Autowired
    protected UserRolesService roleService;

    protected User saveUser(String email, String password, UserRoles role, List<UserGame> games) {
        return saveUser(new User(email, password, role, games));
    }
    protected User saveUser(User user) {
        return userService.save(user);
    }

    protected GameType saveType(GameType type) {
        return typesService.save(type);
    }
    protected GameType saveType(String type) {
        return saveType(new GameType(type));
    }

    protected Game saveGame(Game game) {
        return gameService.save(game);
    }
    protected Game saveGame(GameType type, GameStatus gameStatus, int scoreTeam1, int scoreTeam2,
                            List<UserGame> players, List<UserGame> team1, List<UserGame> team2,
                            List<UserGame> missedPlayers) {
        return saveGame(new Game(type, scoreTeam1, scoreTeam2, gameStatus,
                players, team1, team2, missedPlayers));
    }
}
