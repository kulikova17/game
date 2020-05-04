package net.thumbtack.footballgame.service;

import net.thumbtack.footballgame.FootballGameServer;
import net.thumbtack.footballgame.entities.Game;
import net.thumbtack.footballgame.entities.GameStatus;
import net.thumbtack.footballgame.entities.GameType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ContextConfiguration(classes = { FootballGameServer.class })
@Transactional
public class GameServiceTest extends BaseServiceTest {
    @Test
    public void addGame() {
        GameType type = saveType("type1");

        Game game = new Game(0,0,type, GameStatus.SCHEDULED);
        Game gameFromDB = saveGame(game);

        assertEquals(game, gameFromDB);
        assertNotEquals(0, gameFromDB.getId());
    }
}
