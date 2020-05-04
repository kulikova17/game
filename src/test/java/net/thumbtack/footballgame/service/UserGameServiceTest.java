package net.thumbtack.footballgame.service;

import net.thumbtack.footballgame.FootballGameServer;
import net.thumbtack.footballgame.entities.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = { FootballGameServer.class })
public class UserGameServiceTest extends BaseServiceTest {
    @Test
    @Transactional
    public void add() {
        User user1 = saveUser("user1", "passwd1", null, null);
        User user2 = saveUser("user2", "passwd2", null, null);
        User user3 = saveUser("user3", "passwd3", null, null);

        GameType type = saveType("type1");

        Game game = saveGame(type,GameStatus.SCHEDULED,0,0, null, null, null, null);

        UserGame userGame1 = new UserGame(user1, game, 1);
        UserGame userGame2 = new UserGame(user2, game, 2);
        UserGame userGame3 = new UserGame(user3, game, 0);

        user1.addGame(userGame1);
        user2.addGame(userGame2);
        user3.addGame(userGame3);

        game.addPlayer(userGame1);
        game.addPlayer(userGame2);
        game.addPlayer(userGame3);

        game.addPlayerTeam1(userGame1);
        game.addPlayerTeam2(userGame2);
        game.addMissedPlayer(userGame3);

        User userFromDB = userService.findById(user1.getId());
        Game gameFromDB = gameService.findById(game.getId());

        assertEquals(userGame1, userFromDB.getGames().get(0));
        assertEquals(userGame1, gameFromDB.getPlayers().get(0));

        assertEquals(userGame1, gameFromDB.getTeam1().get(0));
        assertEquals(userGame2, gameFromDB.getTeam2().get(0));
        assertEquals(userGame3, gameFromDB.getMissedPlayers().get(0));

    }
}
