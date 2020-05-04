package net.thumbtack.footballgame.endpoints;

import net.thumbtack.footballgame.dto.requests.AddGameDtoRequest;
import net.thumbtack.footballgame.dto.responses.AddGameDtoResponse;
import net.thumbtack.footballgame.dto.responses.AddGameTypeDtoResponse;
import net.thumbtack.footballgame.dto.responses.GetGameStatisticDtoResponse;
import net.thumbtack.footballgame.dto.responses.GetGameTypesDtoResponse;
import net.thumbtack.footballgame.entities.GameStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class GameEndpointsTest extends BaseEndpointsTest {
    @Test
    public void createGame() {
        AddGameTypeDtoResponse dtoResponseType = createType("type");


        /*get all types*/
        ResponseEntity<GetGameTypesDtoResponse> allTypesResponse =
                testRestTemplateWithAuthAdmin.getForEntity(urlPrefix + "/types/get-all", GetGameTypesDtoResponse.class);

        GetGameTypesDtoResponse allTypesDtoResponse = allTypesResponse.getBody();
        assertNotNull(allTypesDtoResponse);

        /*create game*/
        AddGameDtoRequest dtoGame = new AddGameDtoRequest(allTypesDtoResponse.getList().get(0).getId());
        HttpEntity<AddGameDtoRequest> requestGame = new HttpEntity<>(dtoGame);
        HttpEntity<String> responseGame = testRestTemplateWithAuthAdmin.exchange(urlPrefix + "/games/add",
                HttpMethod.POST, requestGame, String.class);
        AddGameDtoResponse dtoResponseGame = json.fromJson(responseGame.getBody(), AddGameDtoResponse.class);

        assertEquals(GameStatus.SCHEDULED.toString(), dtoResponseGame.getStatus());
        assertNotEquals(0, dtoResponseGame.getId());
        assertEquals(dtoResponseType.getType(), dtoResponseGame.getType());
    }

    @Test
    public void getGameStatistic() {
        AddGameTypeDtoResponse dtoResponseType = createType("type");
        AddGameDtoResponse dtoResponseGame = createGame(dtoResponseType.getId());

        System.out.println(dtoResponseGame.getId());
        ResponseEntity<GetGameStatisticDtoResponse> statisticResponse =
                testRestTemplateWithAuthAdmin.getForEntity(urlPrefix + "/games/statistic/" + dtoResponseGame.getId(), GetGameStatisticDtoResponse.class);
        GetGameStatisticDtoResponse statisticDtoResponse = statisticResponse.getBody();

        assertNotNull(statisticDtoResponse);
        assertEquals(GameStatus.SCHEDULED.toString(), statisticDtoResponse.getStatus());
        assertEquals(0, statisticDtoResponse.getScoreTeam1());
        assertEquals(0, statisticDtoResponse.getScoreTeam2());

        assertEquals(0, statisticDtoResponse.getTeams().size());
    }

}
