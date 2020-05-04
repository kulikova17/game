package net.thumbtack.footballgame.endpoints;

import net.thumbtack.footballgame.service.DebugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebugEndpoints {

    private final DebugService service;
    @Autowired
    public DebugEndpoints(DebugService service) {
        this.service = service;
    }

    @PostMapping(value = "/debug/clear")
    public void clear() {
        service.clear();
    }
}
