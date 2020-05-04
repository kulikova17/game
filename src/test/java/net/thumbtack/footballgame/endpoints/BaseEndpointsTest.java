package net.thumbtack.footballgame.endpoints;

import com.google.gson.Gson;
import net.thumbtack.footballgame.configure.ConfigUtils;
import net.thumbtack.footballgame.dto.requests.AddGameDtoRequest;
import net.thumbtack.footballgame.dto.requests.AddGameTypeDtoRequest;
import net.thumbtack.footballgame.dto.requests.AddUserDtoRequest;
import net.thumbtack.footballgame.dto.requests.EnterToGameDtoRequest;
import net.thumbtack.footballgame.dto.responses.AddGameDtoResponse;
import net.thumbtack.footballgame.dto.responses.AddGameTypeDtoResponse;
import net.thumbtack.footballgame.dto.responses.AddUserDtoResponse;
import net.thumbtack.footballgame.dto.responses.EnterToGameDtoResponse;
import net.thumbtack.footballgame.entities.User;
import net.thumbtack.footballgame.entities.UserRole;
import net.thumbtack.footballgame.entities.UserRoles;
import net.thumbtack.footballgame.service.UserService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

public class BaseEndpointsTest {
    protected TestRestTemplate template = new TestRestTemplate();
    protected static String urlPrefix;
    protected Gson json = new Gson();
    protected static TestRestTemplate testRestTemplateWithAuthAdmin;

    @Autowired
    private UserService userService;


    @BeforeClass
    public static void before() {
        ConfigUtils utils = new ConfigUtils("application-dev.properties");
        urlPrefix = String.format("http://localhost:%d", utils.getServerPort());
        testRestTemplateWithAuthAdmin = new TestRestTemplate(utils.getAdminEmail(), utils.getAdminPassword());
    }

    @Before
    public void deleteAll(){
        testRestTemplateWithAuthAdmin.exchange(urlPrefix + "/debug/clear", HttpMethod.POST, null, String.class);
        userService.save(new User("admin@gmail.com", "admin", new UserRoles(UserRole.ROLE_ADMIN), null));
    }


    protected AddUserDtoResponse createUser(String email, String password) {
        AddUserDtoRequest dtoUser = new AddUserDtoRequest(email, password);
        HttpEntity<AddUserDtoRequest> requestUser = new HttpEntity<>(dtoUser);
        HttpEntity<String> responseUser = template.exchange(urlPrefix + "/users/registration", HttpMethod.POST, requestUser, String.class);
        return json.fromJson(responseUser.getBody(), AddUserDtoResponse.class);
    }


    protected AddGameTypeDtoResponse createType(String type) {
        AddGameTypeDtoRequest dtoType = new AddGameTypeDtoRequest(type);
        HttpEntity<AddGameTypeDtoRequest> requestType = new HttpEntity<>(dtoType);
        HttpEntity<String> responseType = testRestTemplateWithAuthAdmin.exchange(urlPrefix + "/types/add", HttpMethod.POST, requestType, String.class);
        return json.fromJson(responseType.getBody(), AddGameTypeDtoResponse.class);
    }

    protected AddGameDtoResponse createGame(int typeId) {
        AddGameDtoRequest dtoGame = new AddGameDtoRequest(typeId);
        HttpEntity<AddGameDtoRequest> requestGame = new HttpEntity<>(dtoGame);
        HttpEntity<String> responseGame = testRestTemplateWithAuthAdmin.exchange(urlPrefix + "/games/add", HttpMethod.POST, requestGame, String.class);
        return json.fromJson(responseGame.getBody(), AddGameDtoResponse.class);
    }

    protected EnterToGameDtoResponse enterToGame(int gameId,String email, String password) {
        TestRestTemplate testRestTemplate = new TestRestTemplate(email, password);
        EnterToGameDtoRequest dtoEnter = new EnterToGameDtoRequest(gameId);
        HttpEntity<EnterToGameDtoRequest> requestEnter = new HttpEntity<>(dtoEnter);
        HttpEntity<String> responseEnter = testRestTemplate.exchange(urlPrefix + "/users/enter-to-game", HttpMethod.POST, requestEnter, String.class);
        return json.fromJson(responseEnter.getBody(), EnterToGameDtoResponse.class);
    }

}
