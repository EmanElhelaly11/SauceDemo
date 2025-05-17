package utils;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerClass {
    private static final Logger logger = LogManager.getLogger(LoggerClass.class);

    @Step("{message}")
    public static void logStep(String message) {
        logger.info(message);
        ExtentReport.info(message);
    }

    public static void logMessage(String message) {
        logger.info(message);
        ExtentReport.info(message);
    }
}
