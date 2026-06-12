package com.aideveloper.api;

import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aideveloper.ai.AIRepairEngine;
import com.aideveloper.testing.TestResult;
import com.google.gson.JsonObject;

@RestController
@RequestMapping("/api/repair")
public class RepairController {
    private static final Logger logger = LoggerFactory.getLogger(RepairController.class);
    private final AIRepairEngine repairEngine = new AIRepairEngine();

    @PostMapping("/auto-fix")
    public JsonObject autoFixCode(@RequestBody TestResult failedTest) {
        logger.info("Auto-fix request for module {}", failedTest.getModuleIndex());
        JsonObject response = new JsonObject();
        try {
            String repairedCode = repairEngine.repairCode(failedTest);
            response.addProperty("status", "success");
            response.addProperty("moduleIndex", failedTest.getModuleIndex());
            response.addProperty("repairedCode", repairedCode);
            repairEngine.saveRepairLog(failedTest, repairedCode);
        } catch (Exception e) {
            response.addProperty("status", "error");
            response.addProperty("message", e.getMessage());
        }
        return response;
    }
}