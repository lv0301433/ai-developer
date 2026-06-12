package com.aideveloper.testing;

public class TestResult {
    private int moduleIndex;
    private String moduleCode;
    private String status;
    private String message;
    private String errorLog;

    public int getModuleIndex() {
        return moduleIndex;
    }

    public void setModuleIndex(int moduleIndex) {
        this.moduleIndex = moduleIndex;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorLog() {
        return errorLog;
    }

    public void setErrorLog(String errorLog) {
        this.errorLog = errorLog;
    }
}