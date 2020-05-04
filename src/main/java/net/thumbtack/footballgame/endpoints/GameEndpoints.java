package net.thumbtack.footballgame.endpoints;

import net.thumbtack.footballgame.dto.requests.AddGameDtoRequest;
import net.thumbtack.footballgame.dto.requests.UpdateGameStatisticDtoRequest;
import net.thumbtack.footballgame.dto.responses.AddGameDtoResponse;
import net.thumbtack.footballgame.dto.responses.GetGameStatisticDtoResponse;
import net.thumbtack.footballgame.dto.responses.UpdateGameStatisticDtoResponse;
import net.thumbtack.footballgame.entities.Game;
import net.thumbtack.footballgame.entities.GameType;
import net.thumbtack.footballgame.entities.UserGame;
import net.thumbtack.footballgame.exceptions.ErrorCode;
import net.thumbtack.footballgame.exceptions.SimpleException;
import net.thumbtack.footballgame.service.GameService;
import net.thumbtack.footballgame.service.GameTypesService;
import net.thumbtack.footballgame.service.UserGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.function.Function;

@RestController
@RequestMapping("games")
@CrossOrigin(origins = "http://localhost:4200")
public class GameEndpoints {
    private final GameService gameService;
    private final GameTypesService typesService;
    private final UserGameService userGameService;

    @Autowired
    public GameEndpoints(GameService gameService, GameTypesService typesService, UserGameService userGameService) {
        this.gameService = gameService;
        this.typesService = typesService;
        this.userGameService = userGameService;
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public AddGameDtoResponse createGame(@RequestBody @Valid AddGameDtoRequest request) throws SimpleException {
        GameType type = typesService.findById(request.getTypeId());
        if (type == null) {
            throw new SimpleException(ErrorCode.TYPE_NOT_EXISTS);
        }
        Game game = gameService.save(new Game(type));
        game.setType(typesService.findById(request.getTypeId()));
        return new AddGameDtoResponse(game.getId(), game.getType().getType(), game.getStatus().toString());
    }


    @GetMapping(value = "/statistic/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetGameStatisticDtoResponse getGameStatistic(@PathVariable("gameId") int gameId) {
        Game game = gameService.findById(gameId);

        Function<UserGame, String> getEmail = player -> player.getUser().getEmail();
        GetGameStatisticDtoResponse response = new GetGameStatisticDtoResponse(game.getScoreTeam1(), game.getScoreTeam2(),
                game.getStatus().toString());

        game.getTeam1().stream().map(getEmail).forEach(email -> response.addPlayer(email, 1));
        game.getTeam2().stream().map(getEmail).forEach(email -> response.addPlayer(email, 2));
        game.getMissedPlayers().stream().map(getEmail).forEach(email -> response.addPlayer(email, 3));

        return response;
    }

    @PostMapping(value = "/statistic/update/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public UpdateGameStatisticDtoResponse updateGameStatistic(@PathVariable("gameId") int gameId,
                                                              @RequestBody UpdateGameStatisticDtoRequest dtoRequest) {
        Game game = gameService.findById(gameId);

        if (dtoRequest.getTeams() != null) {
            for (UserGame userGame : game.getPlayers()) {
                userGame.setTeam(dtoRequest.getTeams().get(userGame.getUser().getEmail()));
                userGameService.update(userGame);
            }
        }

        if (dtoRequest.getScoreTeam1() != null) {
            game.setScoreTeam1(dtoRequest.getScoreTeam1());
        }

        if (dtoRequest.getScoreTeam2() != null) {
            game.setScoreTeam2(dtoRequest.getScoreTeam2());
        }

        gameService.update(game);
        return new UpdateGameStatisticDtoResponse(gameId, game.getScoreTeam1(),
                game.getScoreTeam2(), game.getStatus().toString(), dtoRequest.getTeams());
    }

}
