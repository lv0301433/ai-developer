package com.aideveloper.ai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import com.aideveloper.testing.TestResult;

public class AIRepairEngine {
    private static final Logger logger = LoggerFactory.getLogger(AIRepairEngine.class);

    public String repairCode(TestResult failedTest) {
        logger.info("AI Repair Engine analyzing failed test {}", failedTest.getModuleIndex());
        String originalCode = failedTest.getModuleCode();
        String errorLog = failedTest.getErrorLog();
        
        logger.info("Error detected: {}", errorLog);
        
        String repairedCode = performRepair(originalCode, errorLog);
        
        logger.info("Code repaired successfully");
        return repairedCode;
    }

    private String performRepair(String code, String errorLog) {
        String repaired = code;
        
        if (errorLog != null && errorLog.contains("NullPointerException")) {
            repaired = addNullCheckGuards(code);
        } else if (errorLog != null && errorLog.contains("ArrayIndexOutOfBoundsException")) {
            repaired = addBoundsChecks(code);
        } else if (errorLog != null && errorLog.contains("TypeError")) {
            repaired = addTypeChecks(code);
        }
        
        return repaired;
    }

    private String addNullCheckGuards(String code) {
        logger.info("Adding null check guards");
        return code.replace("if (", "if (obj != null && ");
    }

    private String addBoundsChecks(String code) {
        logger.info("Adding bounds checks");
        return code.replace("arr[", "arr[Math.min(");
    }

    private String addTypeChecks(String code) {
        logger.info("Adding type checks");
        return code.replace("= ", "= (Object) ");
    }

    public void saveRepairLog(TestResult result, String repairedCode) {
        logger.info("Saving repair log for module {}", result.getModuleIndex());
        logger.debug("Original code:\n{}", result.getModuleCode());
        logger.debug("Repaired code:\n{}", repairedCode);
    }
}