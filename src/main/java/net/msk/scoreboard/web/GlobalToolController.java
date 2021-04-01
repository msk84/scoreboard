package net.msk.scoreboard.web;

import net.msk.scoreboard.service.GlobalRevisionCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tool")
public class GlobalToolController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalToolController.class);

    @GetMapping("/getServerRevision")
    public long getServerRevision() {
        return GlobalRevisionCounter.getRevision();
    }
}
