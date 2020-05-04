package net.thumbtack.footballgame.endpoints;

import net.thumbtack.footballgame.dto.requests.AddGameTypeDtoRequest;
import net.thumbtack.footballgame.dto.responses.AddGameTypeDtoResponse;
import net.thumbtack.footballgame.dto.responses.GetGameTypeDtoResponse;
import net.thumbtack.footballgame.dto.responses.GetGameTypesDtoResponse;
import net.thumbtack.footballgame.entities.GameType;
import net.thumbtack.footballgame.service.GameTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("types")
@CrossOrigin(origins = "*")
public class GameTypeEndpoints {
    private GameTypesService typesService;
    @Autowired
    public GameTypeEndpoints(GameTypesService gameTypesService) {
        this.typesService = gameTypesService;
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public AddGameTypeDtoResponse createType(@RequestBody @Valid AddGameTypeDtoRequest request) {
        GameType type = typesService.save(new GameType(request.getType()));
        return new AddGameTypeDtoResponse(type.getId(), type.getType());
    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetGameTypesDtoResponse getAllTypes() {
        List<GameType> types = typesService.findAll();
        GetGameTypesDtoResponse response = new GetGameTypesDtoResponse();
        for (GameType type:types) {
            response.addType(new GetGameTypeDtoResponse(type.getId(), type.getType()));
        }
        return response;
    }
}
