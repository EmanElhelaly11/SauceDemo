package utils;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.*;

public class TestngListener extends AllureTestNg implements ISuiteListener, ITestListener, IInvokedMethodListener {

    //////////////////////////////////////////////////////////
    ////////////////// ISuiteListener ////////////////////////
    //////////////////////////////////////////////////////////
    @Override
    public void onStart(ISuite suite) {
        LoggerClass.logMessage("🚀 Starting Test Suite: " + suite.getName());
        PropertiesReader.loadProperties();
        ExtentReport.initReports();
    }

    @Override
    public void onFinish(ISuite suite) {
        LoggerClass.logMessage("🧾 Finishing Test Suite: " + suite.getName());
        ExtentReport.flushReports();
        System.out.println("✅ Extent Report generated at: " + System.getProperty("user.dir") + "/reports/ExtentReport.html");
    }

    ////////////////////////////////////////////////////////
    ////////////////// ITestListener ///////////////////////
    ////////////////////////////////////////////////////////
    @Override
    public void onStart(ITestContext context) {
        LoggerClass.logMessage("********************** Test Context Started: [" + context.getName() + "] **********************");
    }

    @Override
    public void onFinish(ITestContext context) {
        LoggerClass.logMessage("********************** Test Context Finished: [" + context.getName() + "] **********************");
    }

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReport.pass(MarkupHelper.createLabel(result.getMethod().getMethodName() + " Passed ✅", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentReport.fail(MarkupHelper.createLabel(result.getMethod().getMethodName() + " Failed ❌", ExtentColor.RED));
        if (result.getThrowable() != null) {
            ExtentReport.fail(result.getThrowable());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReport.skip(MarkupHelper.createLabel(result.getMethod().getMethodName() + " Skipped ⚠️", ExtentColor.YELLOW));
        if (result.getThrowable() != null) {
            ExtentReport.skip(result.getThrowable());
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not used
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }

    ///////////////////////////////////////////////////////////
    ////////////////// IInvokedMethodListener //////////////////
    ///////////////////////////////////////////////////////////
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        ITestNGMethod testMethod = method.getTestMethod();
        String testName = (testMethod.getDescription() != null && !testMethod.getDescription().isEmpty())
                ? testMethod.getDescription()
                : testResult.getName();

        if (!method.isConfigurationMethod()) {
            ExtentReport.createTest(testName);
            LoggerClass.logMessage("🟢 Starting Test Case: [" + testName + "]");
        } else {
            LoggerClass.logMessage("🔧 Executing Configuration Method: [" + testName + "]");
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        String testName = testResult.getName();
        if (method.isConfigurationMethod()) {
            LoggerClass.logMessage("🔚 Finished Configuration Method: [" + testName + "]");
        } else {
            LoggerClass.logMessage("🔵 Finished Test Case: [" + testName + "]");
        }
    }
}
