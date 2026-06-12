package com.aideveloper.orchestrator;

import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aideveloper.core.CodeGenerator;
import com.aideveloper.testing.AutoTestRunner;
import com.aideveloper.ai.AIRepairEngine;
import com.aideveloper.testing.TestResult;
import java.util.List;

@Component
public class AutoDevOrchestrator {
    private static final Logger logger = LoggerFactory.getLogger(AutoDevOrchestrator.class);
    private final CodeGenerator codeGenerator = new CodeGenerator();
    private final AutoTestRunner testRunner = new AutoTestRunner();
    private final AIRepairEngine repairEngine = new AIRepairEngine();

    public void executeFullPipeline(String requirements, int moduleCount) {
        logger.info("\n========== FULL PIPELINE EXECUTION ==========");
        logger.info("Requirements: {}", requirements);
        logger.info("Module Count: {}", moduleCount);

        // Step 1: Generate Code
        logger.info("\n[Step 1] Generating {} modules...", moduleCount);
        List<String> modules = codeGenerator.generateModules(requirements, moduleCount);
        logger.info("Generated {} modules", modules.size());

        // Step 2: Run Tests
        logger.info("\n[Step 2] Running tests...");
        testRunner.runTests(modules);

        // Step 3: Identify Failures
        List<TestResult> failedTests = testRunner.getFailedTests();
        logger.info("\n[Step 3] Found {} failed tests", failedTests.size());

        // Step 4: Auto-Repair
        if (!failedTests.isEmpty()) {
            logger.info("\n[Step 4] AI Auto-repair in progress...");
            for (TestResult failedTest : failedTests) {
                String repairedCode = repairEngine.repairCode(failedTest);
                repairEngine.saveRepairLog(failedTest, repairedCode);
                logger.info("Module {} repaired", failedTest.getModuleIndex());
            }
        }

        logger.info("\n========== PIPELINE COMPLETE ==========");
    }
}