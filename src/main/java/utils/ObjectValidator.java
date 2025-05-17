package utils;

public class ObjectValidator {
    private Object actual;
    private String message;

    public ObjectValidator object(Object actual) {
        this.actual = actual;
        return this;
    }

    public ObjectValidator contains(String expected) {
        if (!actual.toString().contains(expected)) {
            throw new AssertionError("[VALIDATION FAIL] Expected to contain: " + expected + ", but was: " + actual);
        }
        return this;
    }

    public ObjectValidator withCustomReportMessage(String message) {
        this.message = message;
        LoggerClass.logMessage("[VALIDATION INFO] " + message);
        return this;
    }

    public void perform() {
        LoggerClass.logMessage("[VALIDATION PASS] " + message);
        // You can add optional final checks or hooks here
    }
}
