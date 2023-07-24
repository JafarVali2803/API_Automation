package CommonUtilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.util.Arrays;

public class Listener implements ITestListener {


        BaseUtils baseUtils =new BaseUtils();
        private static ExtentReports extentReports;

        public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public void onStart(ITestContext context) {
        String fileName = ExtentReportManager.getReportNameWithTimeStamp();

        String fullReportPath = System.getProperty("user.dir") +File.separator+
                "target"+ File.separator+"OnlineApiAutomation_Reports"+File.separator + fileName;

        extentReports = ExtentReportManager.createInstance(fullReportPath,
                "Test API Automation Report: ", "Test ExecutionReport");
    }

        public void onFinish(ITestContext context) {
            if (extentReports != null)
                extentReports.flush();
        }

        public void onTestStart(ITestResult result) {
            ExtentTest test = extentReports.createTest(result.getMethod().getRealClass().getSimpleName()
                    + " - " + result.getMethod().getMethodName() + " - "
                    +result.getMethod().getDescription());
            extentTest.set(test);
        }

        @Override
        public void onTestSuccess(ITestResult iTestResult) {

        }

        public void onTestFailure(ITestResult result) {
            try {
                ExtentReportManager.logFailureDetails(result.getThrowable().getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
            stackTrace = stackTrace.replaceAll(",", "<br>");
            String formmatedTrace = "<details>\n" +
                    "    <summary>Click Here To See Exception Logs</summary>\n" +
                    "    " + stackTrace + "\n" +
                    "</details>\n";
            try {
                ExtentReportManager.logExceptionDetails(formmatedTrace);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        @Override
        public void onTestSkipped(ITestResult iTestResult) {
            extentTest.get().log(Status.SKIP,"Test Skipped Due to Validation Error and Will be Retried!");
        }
        @Override
        public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

        }
    }
