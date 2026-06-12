package com.aideveloper.api;

import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aideveloper.testing.AutoTestRunner;
import com.google.gson.JsonObject;
import java.util.List;

@RestController
@RequestMapping("/api/testing")
public class TestingController {
    private static final Logger logger = LoggerFactory.getLogger(TestingController.class);
    private final AutoTestRunner testRunner = new AutoTestRunner();

    @PostMapping("/run-tests")
    public JsonObject runTests(@RequestBody List<String> modules) {
        logger.info("Running tests on {} modules", modules.size());
        JsonObject response = new JsonObject();
        try {
            testRunner.runTests(modules);
            response.addProperty("status", "success");
            response.addProperty("totalModules", modules.size());
            response.addProperty("failedModules", testRunner.getFailedTests().size());
        } catch (Exception e) {
            response.addProperty("status", "error");
            response.addProperty("message", e.getMessage());
        }
        return response;
    }

    @GetMapping("/results")
    public JsonObject getTestResults() {
        JsonObject response = new JsonObject();
        response.addProperty("status", "success");
        response.add("failedTests", new com.google.gson.Gson().toJsonTree(testRunner.getFailedTests()));
        return response;
    }
}