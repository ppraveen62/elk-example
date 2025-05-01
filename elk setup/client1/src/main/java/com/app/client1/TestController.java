package com.app.client1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/one")
    public String testLog() {
        logger.info("Info log from micro1");
        logger.error("Error log from micro1");
        return "Logs Sent!";
    }

    @GetMapping("/two")
    public String testLog2() {
        logger.info("Info log from micro2");
        logger.error("Error log from micro2");
        return "Logs Sent!";
    }
}
