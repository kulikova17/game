package net.thumbtack.footballgame.endpoints;


import net.thumbtack.footballgame.configure.AuthProvider;
import net.thumbtack.footballgame.dto.requests.AddAdditionalUserDtoRequest;
import net.thumbtack.footballgame.dto.requests.AddUserDtoRequest;
import net.thumbtack.footballgame.dto.requests.EnterToGameDtoRequest;
import net.thumbtack.footballgame.dto.responses.*;
import net.thumbtack.footballgame.entities.*;
import net.thumbtack.footballgame.exceptions.ErrorCode;
import net.thumbtack.footballgame.exceptions.SimpleException;
import net.thumbtack.footballgame.service.AdditionalUserService;
import net.thumbtack.footballgame.service.GameService;
import net.thumbtack.footballgame.service.UserGameService;
import net.thumbtack.footballgame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;


@RestController
@RequestMapping("users")
@CrossOrigin(origins = "*")
public class UserEndpoints {
    private final UserGameService userGameService;
    private final UserService userService;
    private final GameService gameService;
    private final AdditionalUserService additionalUserService;
    private final String lineatePostfix = "@lineate.com";
    @Autowired
    private AuthProvider authManager;


    @Autowired
    public UserEndpoints(UserGameService userGameService, UserService userService,
                         GameService gameService, AdditionalUserService additionalUserService) {
        this.userGameService = userGameService;
        this.userService = userService;
        this.gameService = gameService;
        this.additionalUserService = additionalUserService;
    }
    
    @PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public AddUserDtoResponse registration(@RequestBody @Valid AddUserDtoRequest dtoRequest, HttpServletRequest request) throws SimpleException {
        if (!dtoRequest.getEmail().endsWith(lineatePostfix)) {
            if (additionalUserService.findByEmail(dtoRequest.getEmail()) == null) {
                throw new SimpleException(ErrorCode.NOT_IN_PERMITTED_LIST);
            }
        }
        User user = new User(dtoRequest.getEmail(), dtoRequest.getPassword());
        user.setHasCar(dtoRequest.isHasCar());
        user.setRole(new UserRoles(UserRole.ROLE_USER));
        User savedUser = userService.save(user);

        authenticate(user.getEmail(), user.getPassword(), request);
        return new AddUserDtoResponse(savedUser.getId(), savedUser.getEmail());
    }


    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public AddUserDtoResponse login(@RequestBody @Valid AddUserDtoRequest dtoRequest, HttpServletRequest request) {
        User user = userService.findByEmail(dtoRequest.getEmail());
        authenticate(dtoRequest.getEmail(), dtoRequest.getPassword(), request);
        return new AddUserDtoResponse(user.getId(), user.getEmail());
    }

    private void authenticate(String email, String password, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(email, password);
        Authentication auth = authManager.authenticate(authReq);

        SecurityContext sc = SecurityContextHolder.getContext();

        sc.setAuthentication(auth);
        HttpSession session = request.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetAllUsersDtoResponse getAllUsersSortedByName() {
        List<User> users = userService.findAllSortedByEmail();
        GetAllUsersDtoResponse response = new GetAllUsersDtoResponse();

        for (User user : users) {
            response.addUser(new GetUserDtoResponse(user.getId(), user.getEmail()));
        }
        return response;
    }

    @PostMapping(value = "/enter-to-game", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public EnterToGameDtoResponse enterToGame(@RequestBody EnterToGameDtoRequest request, HttpServletRequest httpRequest) throws SimpleException {
        User user = userService.findByEmail(httpRequest.getUserPrincipal().getName());
        Game game = gameService.findById(request.getGameId());

        if (game == null) {
            throw new SimpleException(ErrorCode.GAME_NOT_EXISTS);
        }
        UserGame userGame = userGameService.save(new UserGame(user, game));
        return new EnterToGameDtoResponse(userGame.getGame().getId(), userGame.getGame().getType().getType(),
                userGame.getGame().getStatus().toString());
    }


    @PostMapping(value = "/permitted/add", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public AddAdditionalUserDtoResponse addAdditionalUser(@RequestBody @Valid AddAdditionalUserDtoRequest dtoRequest) {
        AdditionalUser user = new AdditionalUser(dtoRequest.getEmail());
        AdditionalUser savedUser = additionalUserService.save(user);
        return new AddAdditionalUserDtoResponse(savedUser.getId(), savedUser.getEmail());
    }

    @DeleteMapping(value = "/permitted/delete/{id}")
    public void addAdditionalUser(@PathVariable("id") Integer id) {
        additionalUserService.deleteById(id);
    }

    @GetMapping(value = "/permitted/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetAdditionalUsersResponse getAdditionalUsers() {
        List<AdditionalUser> list = additionalUserService.findAll();
        GetAdditionalUsersResponse response = new GetAdditionalUsersResponse();
        list.forEach(user -> response.addUser(new GetAdditionalUserResponse(user.getId(), user.getEmail())));
        return response;
    }

}
