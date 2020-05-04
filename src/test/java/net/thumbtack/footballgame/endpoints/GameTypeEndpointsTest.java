package net.thumbtack.footballgame.endpoints;

import net.thumbtack.footballgame.dto.requests.AddGameTypeDtoRequest;
import net.thumbtack.footballgame.dto.responses.AddGameTypeDtoResponse;
import net.thumbtack.footballgame.dto.responses.GetGameTypeDtoResponse;
import net.thumbtack.footballgame.dto.responses.GetGameTypesDtoResponse;
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
public class GameTypeEndpointsTest extends BaseEndpointsTest{
    @Test
    public void createTypeGetAll() {

        /*create type*/
        AddGameTypeDtoRequest dto = new AddGameTypeDtoRequest("type");
        HttpEntity<AddGameTypeDtoRequest> request = new HttpEntity<>(dto);
        HttpEntity<String> response = testRestTemplateWithAuthAdmin.exchange(urlPrefix + "/types/add", HttpMethod.POST, request, String.class);

        AddGameTypeDtoResponse dtoResponse = json.fromJson(response.getBody(), AddGameTypeDtoResponse.class);

        assertEquals(dto.getType(), dtoResponse.getType());
        assertNotEquals(0, dtoResponse.getId());


       /*get all types*/
        ResponseEntity<GetGameTypesDtoResponse> allTypesResponse =
                testRestTemplateWithAuthAdmin.getForEntity(urlPrefix + "/types/get-all", GetGameTypesDtoResponse.class);

        GetGameTypesDtoResponse allTypesDtoResponse = allTypesResponse.getBody();

        assertNotNull(allTypesDtoResponse);
        assertEquals(1, allTypesDtoResponse.getList().size());
        GetGameTypeDtoResponse type = allTypesDtoResponse.getList().get(0);

        assertEquals(dtoResponse.getId(), type.getId());
        assertEquals(dtoResponse.getType(), type.getType());
    }

}
