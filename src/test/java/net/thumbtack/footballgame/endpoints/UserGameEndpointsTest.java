package net.thumbtack.footballgame.endpoints;

import net.thumbtack.footballgame.dto.requests.EnterToGameDtoRequest;
import net.thumbtack.footballgame.dto.responses.AddGameDtoResponse;
import net.thumbtack.footballgame.dto.responses.AddUserDtoResponse;
import net.thumbtack.footballgame.dto.responses.EnterToGameDtoResponse;
import net.thumbtack.footballgame.dto.responses.GetGameTypesDtoResponse;
import net.thumbtack.footballgame.entities.User;
import net.thumbtack.footballgame.entities.UserRole;
import net.thumbtack.footballgame.entities.UserRoles;
import net.thumbtack.footballgame.service.UserService;
import net.thumbtack.footballgame.dto.requests.UpdateGameStatisticDtoRequest;
import net.thumbtack.footballgame.dto.responses.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserGameEndpointsTest extends BaseEndpointsTest {
    @Autowired
    UserService userService;

    @Test
    public void enterToGame()  {
        createType("type");

        /*get all types*/
        ResponseEntity<GetGameTypesDtoResponse> allTypesResponse =
                testRestTemplateWithAuthAdmin.getForEntity(urlPrefix + "/types/get-all", GetGameTypesDtoResponse.class);

        GetGameTypesDtoResponse allTypes = allTypesResponse.getBody();
        assertNotNull(allTypes);

        AddGameDtoResponse game = createGame(allTypes.getList().get(0).getId());



        /*enter user to game*/
        EnterToGameDtoRequest dtoEnter = new EnterToGameDtoRequest(game.getId());
        HttpEntity<EnterToGameDtoRequest> requestEnter = new HttpEntity<>(dtoEnter);
        HttpEntity<String> responseEnter = testRestTemplateWithAuthAdmin.exchange(urlPrefix + "/users/enter-to-game",
                HttpMethod.POST, requestEnter, String.class);
        EnterToGameDtoResponse enter = json.fromJson(responseEnter.getBody(), EnterToGameDtoResponse.class);

        assertEquals(game.getId(), enter.getGameId());
        assertEquals(game.getStatus(), enter.getGameStatus());
        assertEquals(game.getType(), enter.getGameType());
    }

    @Test
    public void updateGameStatistic() {

        String password = "my_password";
        AddUserDtoResponse user1 = createUser("user1@lineate.com", password);
        AddUserDtoResponse user2 = createUser("user2@lineate.com", password);
        AddUserDtoResponse missed = createUser("missed@lineate.com", password);
        AddGameTypeDtoResponse type = createType("type");
        AddGameDtoResponse game = createGame(type.getId());


        enterToGame(game.getId(), user1.getEmail(), password);
        enterToGame(game.getId(), user2.getEmail(), password);
        enterToGame(game.getId(), missed.getEmail(), password);


        UpdateGameStatisticDtoRequest dtoAssign = new UpdateGameStatisticDtoRequest();

        dtoAssign.setScoreTeam1(5);
        dtoAssign.setScoreTeam2(8);

        dtoAssign.setTeams(new HashMap<>());
        dtoAssign.addPlayer(user1.getEmail(), 1);
        dtoAssign.addPlayer(user2.getEmail(), 2);
        dtoAssign.addPlayer(missed.getEmail(), 3);
        HttpEntity<UpdateGameStatisticDtoRequest> requestAssign = new HttpEntity<>(dtoAssign);
        HttpEntity<String> responseAssign = testRestTemplateWithAuthAdmin.exchange(urlPrefix + "/games/statistic/update/" + game.getId(),
                HttpMethod.POST, requestAssign, String.class);
        UpdateGameStatisticDtoResponse assign = json.fromJson(responseAssign.getBody(), UpdateGameStatisticDtoResponse.class);


        assertEquals(Integer.valueOf(1), assign.getTeams().get(user1.getEmail()));
        assertEquals(Integer.valueOf(2), assign.getTeams().get(user2.getEmail()));
        assertEquals(Integer.valueOf(3), assign.getTeams().get(missed.getEmail()));

        assertEquals(dtoAssign.getScoreTeam1(), assign.getScoreTeam1());
        assertEquals(dtoAssign.getScoreTeam2(), assign.getScoreTeam2());
    }

    @Test
    public void updateGameStatisticWithoutTeams() {
        String password = "my_password";
        AddUserDtoResponse user1 = createUser("user1@lineate.com", password);
        AddUserDtoResponse user2 = createUser("user2@lineate.com", password);
        AddUserDtoResponse missed = createUser("missed@lineate.com", password);
        AddGameTypeDtoResponse type = createType("type");
        AddGameDtoResponse game = createGame(type.getId());


        enterToGame(game.getId(), user1.getEmail(), password);
        enterToGame(game.getId(), user2.getEmail(), password);
        enterToGame(game.getId(), missed.getEmail(), password);



        UpdateGameStatisticDtoRequest dtoAssign = new UpdateGameStatisticDtoRequest();

        dtoAssign.setScoreTeam1(5);
        dtoAssign.setScoreTeam2(8);

        HttpEntity<UpdateGameStatisticDtoRequest> requestAssign = new HttpEntity<>(dtoAssign);
        HttpEntity<String> responseAssign = testRestTemplateWithAuthAdmin.exchange(urlPrefix + "/games/statistic/update/" + game.getId(),
                HttpMethod.POST, requestAssign, String.class);
        UpdateGameStatisticDtoResponse assign = json.fromJson(responseAssign.getBody(), UpdateGameStatisticDtoResponse.class);


        assertNull(assign.getTeams());

        assertEquals(dtoAssign.getScoreTeam1(), assign.getScoreTeam1());
        assertEquals(dtoAssign.getScoreTeam2(), assign.getScoreTeam2());
    }


    @Test
    public void updateGameStatisticWithoutScore() {
        String password = "my_password";
        AddUserDtoResponse user1 = createUser("user1@lineate.com", password);
        AddUserDtoResponse user2 = createUser("user2@lineate.com", password);
        AddUserDtoResponse missed = createUser("missed@lineate.com", password);
        AddGameTypeDtoResponse type = createType("type");
        AddGameDtoResponse game = createGame(type.getId());


        enterToGame(game.getId(), user1.getEmail(), password);
        enterToGame(game.getId(), user2.getEmail(), password);
        enterToGame(game.getId(), missed.getEmail(), password);



        UpdateGameStatisticDtoRequest dtoAssign = new UpdateGameStatisticDtoRequest();
        dtoAssign.setTeams(new HashMap<>());
        dtoAssign.addPlayer(user1.getEmail(), 1);
        dtoAssign.addPlayer(user2.getEmail(), 2);
        dtoAssign.addPlayer(missed.getEmail(), 3);
        HttpEntity<UpdateGameStatisticDtoRequest> requestAssign = new HttpEntity<>(dtoAssign);
        HttpEntity<String> responseAssign = testRestTemplateWithAuthAdmin.exchange(urlPrefix + "/games/statistic/update/" + game.getId(),
                HttpMethod.POST, requestAssign, String.class);
        UpdateGameStatisticDtoResponse assign = json.fromJson(responseAssign.getBody(), UpdateGameStatisticDtoResponse.class);


        assertEquals(Integer.valueOf(1), assign.getTeams().get(user1.getEmail()));
        assertEquals(Integer.valueOf(2), assign.getTeams().get(user2.getEmail()));
        assertEquals(Integer.valueOf(3), assign.getTeams().get(missed.getEmail()));

        assertEquals(Integer.valueOf(0), assign.getScoreTeam1());
        assertEquals(Integer.valueOf(0), assign.getScoreTeam2());
    }
}


