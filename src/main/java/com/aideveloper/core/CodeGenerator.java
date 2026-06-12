package com.aideveloper.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class CodeGenerator {
    private static final Logger logger = LoggerFactory.getLogger(CodeGenerator.class);
    private final Gson gson = new Gson();
    private final Map<String, LanguageTemplate> languageTemplates = new HashMap<>();

    public CodeGenerator() {
        initializeLanguageTemplates();
    }

    private void initializeLanguageTemplates() {
        languageTemplates.put("java", new JavaTemplate());
        languageTemplates.put("javac++", new JavaCppTemplate());
        languageTemplates.put("python", new PythonTemplate());
        languageTemplates.put("javascript", new JavaScriptTemplate());
        languageTemplates.put("cpp", new CppTemplate());
        languageTemplates.put("go", new GoTemplate());
        languageTemplates.put("rust", new RustTemplate());
    }

    public String generateCode(String language, String requirements) {
        logger.info("Generating code for language: {}", language);
        LanguageTemplate template = languageTemplates.getOrDefault(language.toLowerCase(), new JavaTemplate());
        return template.generate(requirements);
    }

    public List<String> generateModules(String requirements, int moduleCount) {
        logger.info("Breaking down project into {} modules", moduleCount);
        List<String> modules = new ArrayList<>();
        String[] parts = requirements.split(";");
        
        for (int i = 0; i < Math.min(parts.length, moduleCount); i++) {
            String module = generateCode("java", parts[i].trim());
            modules.add(module);
        }
        return modules;
    }

    public JsonObject generateProjectStructure(String projectName) {
        JsonObject structure = new JsonObject();
        structure.addProperty("name", projectName);
        structure.addProperty("version", "1.0.0");
        structure.addProperty("description", "AI Generated Project");
        structure.add("modules", gson.toJsonTree(new ArrayList<>()));
        return structure;
    }
}