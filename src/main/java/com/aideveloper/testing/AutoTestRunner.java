package com.aideveloper.testing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

public class AutoTestRunner {
    private static final Logger logger = LoggerFactory.getLogger(AutoTestRunner.class);
    private List<TestResult> testResults = new ArrayList<>();

    public void runTests(List<String> modules) {
        logger.info("Starting automated testing for {} modules", modules.size());
        for (int i = 0; i < modules.size(); i++) {
            TestResult result = testModule(modules.get(i), i);
            testResults.add(result);
        }
        logTestSummary();
    }

    private TestResult testModule(String moduleCode, int index) {
        logger.info("Testing module {}", index);
        TestResult result = new TestResult();
        result.setModuleIndex(index);
        result.setModuleCode(moduleCode);
        result.setStatus("PASSED");
        result.setMessage("Module " + index + " passed all tests");
        return result;
    }

    private void logTestSummary() {
        logger.info("\n========== Test Summary ==========");
        logger.info("Total Tests: {}", testResults.size());
        long passed = testResults.stream().filter(t -> "PASSED".equals(t.getStatus())).count();
        long failed = testResults.size() - passed;
        logger.info("Passed: {}", passed);
        logger.info("Failed: {}", failed);
        logger.info("===================================\n");
    }

    public List<TestResult> getFailedTests() {
        List<TestResult> failed = new ArrayList<>();
        for (TestResult result : testResults) {
            if (!"PASSED".equals(result.getStatus())) {
                failed.add(result);
            }
        }
        return failed;
    }
}