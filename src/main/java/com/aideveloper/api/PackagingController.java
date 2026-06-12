package com.aideveloper.api;

import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aideveloper.packaging.ZIPPackager;
import com.google.gson.JsonObject;
import java.io.IOException;

@RestController
@RequestMapping("/api/packaging")
public class PackagingController {
    private static final Logger logger = LoggerFactory.getLogger(PackagingController.class);
    private final ZIPPackager packager = new ZIPPackager();

    @PostMapping("/create-package")
    public JsonObject createPackage(@RequestParam String projectPath, @RequestParam String name) {
        logger.info("Creating package: {}", name);
        JsonObject response = new JsonObject();
        try {
            String zipPath = packager.packageProject(projectPath, name);
            response.addProperty("status", "success");
            response.addProperty("zipPath", zipPath);
        } catch (IOException e) {
            response.addProperty("status", "error");
            response.addProperty("message", e.getMessage());
            logger.error("Packaging error", e);
        }
        return response;
    }

    @PostMapping("/extract-package")
    public JsonObject extractPackage(@RequestParam String zipPath, @RequestParam String outputDir) {
        logger.info("Extracting package: {}", zipPath);
        JsonObject response = new JsonObject();
        try {
            packager.unpackProject(zipPath, outputDir);
            response.addProperty("status", "success");
            response.addProperty("outputDir", outputDir);
        } catch (IOException e) {
            response.addProperty("status", "error");
            response.addProperty("message", e.getMessage());
            logger.error("Extraction error", e);
        }
        return response;
    }
}