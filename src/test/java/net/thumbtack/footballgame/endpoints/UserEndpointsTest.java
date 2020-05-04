package net.thumbtack.footballgame.endpoints;


import net.thumbtack.footballgame.dto.requests.AddAdditionalUserDtoRequest;
import net.thumbtack.footballgame.dto.requests.AddUserDtoRequest;
import net.thumbtack.footballgame.dto.responses.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserEndpointsTest extends BaseEndpointsTest {
    @Test
    public void registrationUserTest() {
        AddUserDtoRequest dto = new AddUserDtoRequest("email@lineate.com", "my_password");
        HttpEntity<AddUserDtoRequest> request = new HttpEntity<>(dto);
        HttpEntity<String> response = template.exchange(urlPrefix + "/users/registration", HttpMethod.POST, request, String.class);
        AddUserDtoResponse dtoResponse = json.fromJson(response.getBody(), AddUserDtoResponse.class);

        assertEquals(dto.getEmail(), dtoResponse.getEmail());
        assertNotEquals(0, dtoResponse.getId());
    }


    @Test
    public void additionalUsers() {
        String additEmail = "addit_user@addit_email.com";

        /*try to add not permitted user*/
        AddUserDtoResponse failUser = createUser(additEmail, "password");
        assertNull(failUser.getEmail());


        AddAdditionalUserDtoRequest dtoAdd = new AddAdditionalUserDtoRequest(additEmail);
        HttpEntity<AddAdditionalUserDtoRequest> requestAdd = new HttpEntity<>(dtoAdd);
        HttpEntity<String> responseAdd = testRestTemplateWithAuthAdmin.exchange(urlPrefix + "/users/permitted/add", HttpMethod.POST, requestAdd, String.class);
        AddAdditionalUserDtoResponse dtoResponseAdd = json.fromJson(responseAdd.getBody(), AddAdditionalUserDtoResponse.class);

        assertEquals(dtoAdd.getEmail(), dtoResponseAdd.getEmail());
        assertNotEquals(0, dtoResponseAdd.getId());

        /*add permitted user*/
        AddUserDtoResponse successUser = createUser(additEmail, "password");
        assertEquals(additEmail, successUser.getEmail());


        HttpEntity<String> responseGet1 = testRestTemplateWithAuthAdmin.getForEntity(urlPrefix + "/users/permitted/get-all", String.class);
        GetAdditionalUsersResponse dtoResponseGet1 = json.fromJson(responseGet1.getBody(), GetAdditionalUsersResponse.class);

        assertEquals(1, dtoResponseGet1.getList().size());
        assertEquals(dtoResponseAdd.getEmail(), dtoResponseGet1.getList().get(0).getEmail());
        assertEquals(dtoResponseAdd.getId(), dtoResponseGet1.getList().get(0).getId());


        testRestTemplateWithAuthAdmin.delete(urlPrefix + "/users/permitted/delete/" + dtoResponseAdd.getId());

        HttpEntity<String> responseGet2 = testRestTemplateWithAuthAdmin.getForEntity(urlPrefix + "/users/permitted/get-all", String.class);
        GetAdditionalUsersResponse dtoResponseGet2 = json.fromJson(responseGet2.getBody(), GetAdditionalUsersResponse.class);

        assertEquals(0, dtoResponseGet2.getList().size());
    }

    @Test
    public void getAllUsersSortedByNameTest() {
        AddUserDtoResponse user1 = createUser("user@lineate.com", "password");
        AddUserDtoResponse user2 = createUser("usTer@lineate.com", "password");
        AddUserDtoResponse user3 = createUser("Auser@lineate.com", "password");
        AddUserDtoResponse user4 = createUser("usRer@lineate.com", "password");
        AddUserDtoResponse user5 = createUser("FooUser1@lineate.com", "password");
        List<String> expectedUserEmails = new ArrayList<>();
        expectedUserEmails.add("admin@gmail.com");
        expectedUserEmails.add(user1.getEmail());
        expectedUserEmails.add(user2.getEmail());
        expectedUserEmails.add(user3.getEmail());
        expectedUserEmails.add(user4.getEmail());
        expectedUserEmails.add(user5.getEmail());
        expectedUserEmails.sort(String::compareToIgnoreCase);



        ResponseEntity<GetAllUsersDtoResponse> allUsersDtoResponse =
                testRestTemplateWithAuthAdmin.getForEntity(urlPrefix + "/users/get-all", GetAllUsersDtoResponse.class);
        GetAllUsersDtoResponse allUsersDtoResponseBody = allUsersDtoResponse.getBody();

        assertNotNull(allUsersDtoResponseBody);
        List<String> actualEmails = allUsersDtoResponseBody.getUsers().stream().map(GetUserDtoResponse::getEmail).collect(Collectors.toList());
        assertEquals(expectedUserEmails, actualEmails);

    }
}
