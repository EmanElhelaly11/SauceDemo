package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.awt.Desktop;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReport {
    private static ExtentReports report;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private static final String REPORTS_FOLDER = System.getProperty("user.dir") + "/reports";
    private static final String TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
    private static final String REPORTS_PATH = REPORTS_FOLDER + "/ExtentReport_" + TIMESTAMP + ".html";

    public static synchronized void initReports() {
        try {
            if (report == null) {
                Path reportDir = Paths.get(REPORTS_FOLDER);
                if (!Files.exists(reportDir)) {
                    Files.createDirectories(reportDir);
                }

                ExtentSparkReporter spark = new ExtentSparkReporter(REPORTS_PATH);
                spark.config().setTheme(Theme.STANDARD);
                spark.config().setDocumentTitle("Test Execution Report");
                spark.config().setReportName("Eman Elhelaly | Test Results");

                report = new ExtentReports();
                report.attachReporter(spark);
            }
        } catch (Exception e) {
            System.err.println("Error initializing ExtentReports: " + e.getMessage());
        }
    }

    public static synchronized void createTest(String testcasename) {
        if (report == null) {
            throw new IllegalStateException("ExtentReports is not initialized. Call initReports() first.");
        }
        test.set(report.createTest(testcasename));
    }

    public static void info(String message) {
        if (test.get() != null) test.get().info(message);
    }

    public static void info(Markup m) {
        if (test.get() != null) test.get().info(m);
    }

    public static void pass(String message) {
        if (test.get() != null) test.get().pass(message);
    }

    public static void pass(Markup m) {
        if (test.get() != null) test.get().pass(m);
    }

    public static void fail(String message) {
        if (test.get() != null) test.get().fail(message);
    }

    public static void fail(Markup m) {
        if (test.get() != null) test.get().fail(m);
    }

    public static void fail(Throwable t) {
        if (test.get() != null) test.get().fail(t);
    }

    public static void skip(String message) {
        if (test.get() != null) test.get().skip(message);
    }

    public static void skip(Markup m) {
        if (test.get() != null) test.get().skip(m);
    }

    public static void skip(Throwable t) {
        if (test.get() != null) test.get().skip(t);
    }

    public static synchronized void flushReports() {
        if (report != null) {
            report.flush();
            try {
                Path path = Paths.get(REPORTS_PATH);
                if (Files.exists(path)) {
                    System.out.println("✅ Report generated at: " + path.toAbsolutePath());
                    Desktop.getDesktop().browse(path.toAbsolutePath().toUri());
                } else {
                    System.err.println("❌ Report file not found at: " + path.toAbsolutePath());
                }
            } catch (Exception e) {
                System.err.println("❌ Could not open report: " + e.getMessage());
            }
        }
    }
}
