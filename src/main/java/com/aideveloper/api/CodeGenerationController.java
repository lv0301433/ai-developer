package com.aideveloper.api;

import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aideveloper.core.CodeGenerator;
import com.google.gson.JsonObject;
import java.util.List;

@RestController
@RequestMapping("/api/codegen")
public class CodeGenerationController {
    private static final Logger logger = LoggerFactory.getLogger(CodeGenerationController.class);
    private final CodeGenerator codeGenerator = new CodeGenerator();

    @PostMapping("/generate")
    public JsonObject generateCode(@RequestParam String language, @RequestBody String requirements) {
        logger.info("Code generation request - Language: {}", language);
        JsonObject response = new JsonObject();
        try {
            String generatedCode = codeGenerator.generateCode(language, requirements);
            response.addProperty("status", "success");
            response.addProperty("language", language);
            response.addProperty("code", generatedCode);
        } catch (Exception e) {
            response.addProperty("status", "error");
            response.addProperty("message", e.getMessage());
            logger.error("Error during code generation", e);
        }
        return response;
    }

    @PostMapping("/generate-modules")
    public JsonObject generateModules(@RequestParam int count, @RequestBody String requirements) {
        logger.info("Generating {} modules", count);
        JsonObject response = new JsonObject();
        try {
            List<String> modules = codeGenerator.generateModules(requirements, count);
            response.addProperty("status", "success");
            response.addProperty("moduleCount", modules.size());
            response.add("modules", new com.google.gson.Gson().toJsonTree(modules));
        } catch (Exception e) {
            response.addProperty("status", "error");
            response.addProperty("message", e.getMessage());
        }
        return response;
    }

    @GetMapping("/supported-languages")
    public JsonObject getSupportedLanguages() {
        JsonObject response = new JsonObject();
        response.add("languages", new com.google.gson.Gson().toJsonTree(
            new String[]{"java", "javac++", "python", "javascript", "cpp", "go", "rust"}
        ));
        return response;
    }
}